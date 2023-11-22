package com.moa.participate.application;


import com.moa.participate.dto.ParticipantReviewCreateDto;

import java.util.UUID;


public interface ParticipantReviewService {

	void createParticipantReview(ParticipantReviewCreateDto dto);
	boolean isParticipantReviewExist(Long meetingId, UUID targetUuid);

}
