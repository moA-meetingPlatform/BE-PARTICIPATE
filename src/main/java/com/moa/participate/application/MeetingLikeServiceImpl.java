package com.moa.participate.application;


import com.moa.participate.domain.MeetingLike;
import com.moa.participate.dto.MeetingLikeCreateDeleteDto;
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
	public void createOrDeleteMeetingLike(MeetingLikeCreateDeleteDto meetingLikeCreateDeleteDto) {
		// 이미 좋아요한 모임이면 삭제, 아니면 생성
		if (meetingLikeRepository.existsByMeetingIdAndUserUuid(meetingLikeCreateDeleteDto.getMeetingId(), meetingLikeCreateDeleteDto.getUserUuid())) {
			meetingLikeQueryDslRepository.deleteByMeetingIdAndUserUuid(meetingLikeCreateDeleteDto.getMeetingId(), meetingLikeCreateDeleteDto.getUserUuid());
		} else {
			meetingLikeRepository.save(MeetingLike.builder()
				.meetingId(meetingLikeCreateDeleteDto.getMeetingId())
				.userUuid(meetingLikeCreateDeleteDto.getUserUuid())
				.build());
		}
	}


	@Override
	public Slice<Long> getMeetingLikeSlice(UUID userUuid, Pageable pageable) {
		Slice<MeetingLike> meetingLikeSlice = meetingLikeRepository.findByUserUuid(userUuid, pageable);
		// MeetingLike Slice -> Meeting ID Slice 변환
		return meetingLikeSlice.map(MeetingLike::getMeetingId);
	}

}
