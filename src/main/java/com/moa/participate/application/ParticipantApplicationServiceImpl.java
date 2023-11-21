package com.moa.participate.application;


import com.moa.global.common.exception.CustomException;
import com.moa.global.common.exception.ErrorCode;
import com.moa.participate.domain.ApplicationStatus;
import com.moa.participate.domain.ParticipantApplication;
import com.moa.participate.dto.ParticipantApplicationListItemGetDto;
import com.moa.participate.dto.ParticipantCreateDto;
import com.moa.participate.infrastructure.ParticipantApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ParticipantApplicationServiceImpl implements ParticipantApplicationService {

	private final ModelMapper modelMapper;

	private final ParticipantApplicationRepository participantApplicationRepository;


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
	public List<ParticipantApplicationListItemGetDto> getParticipantApplicationListByApplicationStatus(UUID uuid, ApplicationStatus applicationStatus) {
		List<ParticipantApplication> entityList = participantApplicationRepository.findByParticipantUuidAndApplicationStatusOrderByIdDesc(uuid, applicationStatus);
		return entityList.stream()
			.map(o -> modelMapper.map(o, ParticipantApplicationListItemGetDto.class))
			.toList();
	}

}
