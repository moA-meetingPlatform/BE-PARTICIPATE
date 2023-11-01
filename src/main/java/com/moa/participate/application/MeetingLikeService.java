package com.moa.participate.application;


import com.moa.participate.dto.MeetingLikeCreateDeleteDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.UUID;


public interface MeetingLikeService {

	/**
	 * 모임 좋아요 생성 또는 삭제
	 *
	 * @param meetingLikeCreateDeleteDto 모임 좋아요 생성 또는 삭제 DTO
	 */
	void createOrDeleteMeetingLike(MeetingLikeCreateDeleteDto meetingLikeCreateDeleteDto);

	/**
	 * 사용자가 좋아요한 모임 목록 조회
	 *
	 * @param userUuid 사용자 UUID
	 * @param pageable 페이징 정보
	 * @return 모임 ID 목록 Slice
	 */
	Slice<Long> getMeetingLikeSlice(UUID userUuid, Pageable pageable);

}
