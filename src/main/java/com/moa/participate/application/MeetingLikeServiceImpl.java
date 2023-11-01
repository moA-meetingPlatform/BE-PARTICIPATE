package com.moa.participate.application;


import com.moa.participate.common.exception.CustomException;
import com.moa.participate.common.exception.ErrorCode;
import com.moa.participate.domain.MeetingLike;
import com.moa.participate.dto.MeetingLikeCreateDto;
import com.moa.participate.dto.MeetingLikeDeleteDto;
import com.moa.participate.infrastructure.MeetingLikeQueryDslRepository;
import com.moa.participate.infrastructure.MeetingLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class MeetingLikeServiceImpl implements MeetingLikeService {

	private final MeetingLikeRepository meetingLikeRepository;
	private final MeetingLikeQueryDslRepository meetingLikeQueryDslRepository;


	@Override
	public void createMeetingLike(MeetingLikeCreateDto meetingLikeCreateDto) {
		if (meetingLikeRepository.existsByMeetingIdAndUserUuid(meetingLikeCreateDto.getMeetingId(), meetingLikeCreateDto.getUserUuid())) {
			throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
		}
		meetingLikeRepository.save(MeetingLike.builder()
			.meetingId(meetingLikeCreateDto.getMeetingId())
			.userUuid(meetingLikeCreateDto.getUserUuid())
			.build());
	}


	@Override
	public void deleteMeetingLike(MeetingLikeDeleteDto meetingLikeDeleteDto) {
		meetingLikeQueryDslRepository.deleteByMeetingIdAndUserUuid(meetingLikeDeleteDto.getMeetingId(), meetingLikeDeleteDto.getUserUuid());
	}


	@Override
	public Slice<Long> getMeetingLikeSlice(UUID userUuid, Pageable pageable) {
		Slice<MeetingLike> meetingLikeSlice = meetingLikeRepository.findByUserUuid(userUuid, pageable);
		// MeetingLike Slice -> Meeting ID Slice 변환
		return meetingLikeSlice.map(MeetingLike::getMeetingId);
	}

}
