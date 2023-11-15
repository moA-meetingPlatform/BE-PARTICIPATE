package com.moa.participate.application;


import com.moa.global.common.exception.CustomException;
import com.moa.global.common.exception.ErrorCode;
import com.moa.participate.domain.MeetingReview;
import com.moa.participate.dto.MeetingReviewCreatDto;
import com.moa.participate.dto.MeetingReviewGetDto;
import com.moa.participate.infrastructure.MeetingReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class MeetingReviewServiceImpl implements MeetingReviewService {

	private final MeetingReviewRepository meetingReviewRepository;


	/**
	 * 모임 리뷰 생성
	 *
	 * @param meetingReviewCreatDto
	 */
	@Override
	public void createMeetingReview(MeetingReviewCreatDto meetingReviewCreatDto) {
		if (meetingReviewCreatDto.getMeetingHostUuid().equals(meetingReviewCreatDto.getReviewerUserUuid())) {
			// 리뷰어와 리뷰 대상자가 같으면 예외 발생
			throw new CustomException(ErrorCode.BAD_REQUEST);
		}

		if (existsByMeetingIdAndReviewerUserUuid(meetingReviewCreatDto.getMeetingId(), meetingReviewCreatDto.getReviewerUserUuid())) {
			// 이미 리뷰를 작성한 경우 예외 발생
			throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
		}

		MeetingReview meetingReview = meetingReviewCreatDto.toEntity(); // dto -> entity
		meetingReviewRepository.save(meetingReview);
	}


	/**
	 * 모임 리뷰 조회
	 * slice로 페이징 처리
	 *
	 * @param uuid
	 * @param pageable
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Slice<MeetingReviewGetDto> getMeetingReviewListByHostUuid(UUID uuid, Pageable pageable) {
		Slice<MeetingReview> meetingReviewSlice = meetingReviewRepository.findByMeetingHostUuid(uuid, pageable);
		return MeetingReviewGetDto.fromEntitySlice(meetingReviewSlice);
	}


	@Override
	@Transactional(readOnly = true)
	public boolean existsByMeetingIdAndReviewerUserUuid(Long meetingId, UUID reviewerUserUuid) {
		return meetingReviewRepository.existsByMeetingIdAndReviewerUserUuid(meetingId, reviewerUserUuid);
	}

}
