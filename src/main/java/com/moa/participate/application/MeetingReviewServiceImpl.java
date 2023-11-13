package com.moa.participate.application;


import com.moa.participate.domain.MeetingReview;
import com.moa.participate.dto.MeetingReviewGetDto;
import com.moa.participate.infrastructure.MeetingReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class MeetingReviewServiceImpl implements MeetingReviewService {

	private final MeetingReviewRepository meetingReviewRepository;


	@Override
	public Slice<MeetingReviewGetDto> getMeetingReviewListByHostUuid(UUID uuid, Pageable pageable) {
		Slice<MeetingReview> meetingReviewSlice = meetingReviewRepository.findByMeetingHostUuid(uuid, pageable);
		return MeetingReviewGetDto.fromEntitySlice(meetingReviewSlice);
	}

}
