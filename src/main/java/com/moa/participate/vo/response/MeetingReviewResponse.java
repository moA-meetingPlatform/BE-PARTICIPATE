package com.moa.participate.vo.response;


import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
public class MeetingReviewResponse {

	private Long id;

	private UUID reviewerUserUuid;

	private UUID meetingHostUuid;

	private Long meetingId;

	private Integer rating;

	private String meetingReviewContent;

	private LocalDateTime createDatetime;

}
