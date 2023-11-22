package com.moa.participate.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParticipantReviewCreateDto {

	private Long participantApplicationId;
	private UUID reviewrUserUuid;
	private Integer rating;


	public void setParticipantApplicationId(Long participantApplicationId) {
		this.participantApplicationId = participantApplicationId;
	}


	public void setReviewrUserUuid(UUID reviewrUserUuid) {
		this.reviewrUserUuid = reviewrUserUuid;
	}

}
