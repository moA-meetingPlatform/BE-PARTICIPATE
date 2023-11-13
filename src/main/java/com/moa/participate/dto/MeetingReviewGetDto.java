package com.moa.participate.dto;


import com.moa.participate.domain.MeetingReview;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MeetingReviewGetDto {

	private Long id;

	private UUID reviewerUserUuid;

	private UUID meetingHostUuid;

	private Long meetingId;

	private Integer rating;

	private String meetingReviewContent;

	private LocalDateTime createDatetime;


	public MeetingReviewGetDto(Long id, UUID reviewerUserUuid, UUID meetingHostUuid, Long meetingId, Integer rating, String meetingReviewContent, LocalDateTime createDatetime) {
		this.id = id;
		this.reviewerUserUuid = reviewerUserUuid;
		this.meetingHostUuid = meetingHostUuid;
		this.meetingId = meetingId;
		this.rating = rating;
		this.meetingReviewContent = meetingReviewContent;
		this.createDatetime = createDatetime;
	}


	/**
	 * MeetingReview Entity를 MeetingReviewGetDto로 변환
	 *
	 * @param entity MeetingReview
	 * @return MeetingReviewGetDto
	 */
	public static MeetingReviewGetDto fromEntity(MeetingReview entity) {
		return new MeetingReviewGetDto(entity.getId(), entity.getReviewerUserUuid(), entity.getMeetingHostUuid(), entity.getMeetingId(), entity.getRating(), entity.getMeetingReviewContent(),
			entity.getCreateDatetime());
	}


	/**
	 * Slice Entity를 Slice dto로 변환
	 *
	 * @param meetingReviewSlice Slice<MeetingReview>
	 * @return Slice<MeetingReviewGetDto>
	 */
	public static Slice<MeetingReviewGetDto> fromEntitySlice(Slice<MeetingReview> meetingReviewSlice) {
		return meetingReviewSlice.map(MeetingReviewGetDto::fromEntity);
	}

}
