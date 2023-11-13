package com.moa.participate.application;


import com.moa.participate.dto.MeetingReviewGetDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.UUID;


/**
 * 모임에 대한 리뷰 서비스
 */
public interface MeetingReviewService {

	Slice<MeetingReviewGetDto> getMeetingReviewListByHostUuid(UUID uuid, Pageable pageable);

}
