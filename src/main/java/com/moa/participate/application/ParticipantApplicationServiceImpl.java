package com.moa.participate.application;


import com.moa.global.common.exception.CustomException;
import com.moa.global.common.exception.ErrorCode;
import com.moa.participate.domain.ApplicationStatus;
import com.moa.participate.domain.ParticipantApplication;
import com.moa.participate.dto.ParticipantApplicationGetDto;
import com.moa.participate.dto.ParticipantCreateDto;
import com.moa.participate.dto.kafka.MeetingRefundNeedDto;
import com.moa.participate.dto.kafka.ParticipantApplicationUpdateEventDto;
import com.moa.participate.infrastructure.ParticipantApplicationRepository;
import com.moa.participate.infrastructure.ParticipantReviewRepository;
import com.moa.participate.infrastructure.kafka.producer.ParticipateStatusUpdateEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class ParticipantApplicationServiceImpl implements ParticipantApplicationService {

	private final ModelMapper modelMapper;

	private final ParticipantApplicationRepository participantApplicationRepository;
	private final ParticipantReviewRepository participantReviewRepository;

	private final ParticipateStatusUpdateEventProducer participateStatusUpdateEventProducer;


	/**
	 * 모임 참여 신청 생성
	 *
	 * @param participantCreateDto
	 */
	@Override
	public void createParticipantApplication(ParticipantCreateDto participantCreateDto) {
		// 모임 db에서 확인할 정보 (선착순일 경우)
		// TODO: 모임 참여 인원이 꽉 찼는지 확인, 동시성 제어 필요
		ApplicationStatus applicationStatus = ApplicationStatus.WAIT;

		// participantApplication 테이블에서 확인할 정보
		// 모임 참여 신청이 중복되었는지 확인
		// 중복되지 않았다면 저장하기
		participantApplicationRepository.findByMeetingIdAndParticipantUuid(participantCreateDto.getMeetingId(), participantCreateDto.getParticipantUuid())
			.ifPresentOrElse(
				(participantApplication -> {    // 중복되었다면 에러 발생
					throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
				}),
				() -> { // 중복되지 않았다면 저장
					participantApplicationRepository.save(
						new ParticipantApplication(participantCreateDto.getMeetingId(), participantCreateDto.getParticipantUuid(), participantCreateDto.getMeetingParticipationAnswer(),
							applicationStatus));
				}
			);
	}


	@Override
	public List<ParticipantApplicationGetDto> getParticipantApplicationListByApplicationStatus(UUID uuid, ApplicationStatus applicationStatus) {
		List<ParticipantApplication> entityList = participantApplicationRepository.findByParticipantUuidAndApplicationStatusOrderByIdDesc(uuid, applicationStatus);
		return entityList.stream()
			.map(o -> modelMapper.map(o, ParticipantApplicationGetDto.class))
			.toList();
	}


	@Override
	public List<ParticipantApplicationGetDto> getWaitParticipantApplicationListByMeetingId(Long meetingId) {
		List<ParticipantApplication> entityList = participantApplicationRepository.findByMeetingIdAndApplicationStatus(meetingId, ApplicationStatus.WAIT);
		return entityList.stream()
			.map(o -> modelMapper.map(o, ParticipantApplicationGetDto.class))
			.toList();
	}


	@Override
	@Transactional
	public void updateParticipantApplicationByHost(Long id, ApplicationStatus applicationStatus) {
		ParticipantApplication participantApplication = participantApplicationRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE));
		ApplicationStatus prevApplicationStatus = participantApplication.getApplicationStatus();
		participantApplication.setApplicationStatus(applicationStatus);

		// 모임 참여 신청을 승인 또는 거절할 경우 update 이벤트 발행
		participateStatusUpdateEventProducer.sendParticipateStatusUpdateEvent(
			ParticipantApplicationUpdateEventDto.fromEntityAndPrevApplicationStatus(participantApplication, prevApplicationStatus, true)
		);
	}


	@Override
	@Transactional
	public void cancelParticipantApplication(Long id, UUID userUuid) {
		ParticipantApplication participantApplication = participantApplicationRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE));
		if (!participantApplication.getParticipantUuid().equals(userUuid)) throw new CustomException(ErrorCode.BAD_REQUEST);

		ApplicationStatus prevApplicationStatus = participantApplication.getApplicationStatus();
		participantApplication.setApplicationStatus(ApplicationStatus.CANCEL);

		// 참가자가 모임 참여 신청을 취소할 경우 update 이벤트 발행
		participateStatusUpdateEventProducer.sendParticipateStatusUpdateEvent(
			ParticipantApplicationUpdateEventDto.fromEntityAndPrevApplicationStatus(participantApplication, prevApplicationStatus, false)
		);

	}


	@Override
	public void updateParticipationStatusByReview(Long participateId, UUID targetUuid, boolean participationStatus) {
		// 기존에 참여 신청한 내역이 있는지 확인
		ParticipantApplication participantApplication = participantApplicationRepository.findById(participateId)
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE));

		// 기존에 참여 여부가 null이거나 false일 경우에만 업데이트
		if (participantApplication.getParticipationStatus() == null
			|| (participationStatus && !participantApplication.getParticipationStatus())) {
			participantApplication.setParticipationStatus(participationStatus);
		}
	}


	@Override
	@Transactional
	public void updateParticipantRefundInfoByKafka(MeetingRefundNeedDto dto) {
		try {
			ParticipantApplication participantApplication = participantApplicationRepository.findById(dto.getParticipateId())
				.orElseThrow(() -> new IllegalArgumentException("참여 신청 정보가 없습니다."));

			participantApplication.setRefundData(dto.getRefundPercentage(), dto.getRefundAmount());
		} catch (Exception e) {
			log.error("updateParticipantRefundInfoByKafka error : {}", e.getMessage());
		}
	}

}
