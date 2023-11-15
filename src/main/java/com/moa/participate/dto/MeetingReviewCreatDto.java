package com.moa.participate.dto;


import com.moa.participate.domain.MeetingReview;
import lombok.Getter;

import java.util.UUID;


@Getter
public class MeetingReviewCreatDto {

	private UUID reviewerUserUuid;

	private UUID meetingHostUuid;

	private Long meetingId;

	private Integer rating;

	private String meetingReviewContent;


	public MeetingReview toEntity() {
		return MeetingReview.builder()
			.meetingId(meetingId)
			.meetingReviewContent(meetingReviewContent)
			.meetingHostUuid(meetingHostUuid)
			.reviewerUserUuid(reviewerUserUuid)
			.rating(rating)
			.build();
	}

}
