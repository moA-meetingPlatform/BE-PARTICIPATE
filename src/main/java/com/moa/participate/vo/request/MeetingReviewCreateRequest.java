package com.moa.participate.vo.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.UUID;


@Getter
public class MeetingReviewCreateRequest {

	@Schema(description = "리뷰 작성자 UUID", nullable = false, requiredMode = Schema.RequiredMode.REQUIRED, example = "123e4567-e89b-12d3-a456-426614174000")
	private UUID reviewerUserUuid;

	@Schema(description = "모임 호스트 UUID", nullable = false, requiredMode = Schema.RequiredMode.REQUIRED, example = "a642406c-6e20-11ee-b962-0242ac120002")
	private UUID meetingHostUuid;

	@Schema(description = "모임 ID", nullable = false, requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	private Long meetingId;

	@Schema(description = "모임 리뷰 평점", nullable = false, requiredMode = Schema.RequiredMode.REQUIRED, example = "5")
	private Integer rating;

	@Schema(description = "모임 리뷰 내용", nullable = false, requiredMode = Schema.RequiredMode.REQUIRED, example = "재밌었어요:D")
	private String meetingReviewContent;

}
