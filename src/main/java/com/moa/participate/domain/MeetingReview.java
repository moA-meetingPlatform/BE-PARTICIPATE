package com.moa.participate.domain;


import com.moa.participate.common.BaseDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "meeting_review")
public class MeetingReview extends BaseDateTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "reviewer_user_uuid", nullable = false)
	private UUID reviewerUserUuid;

	@Column(name = "meeting_host_uuid", nullable = false)
	private UUID meetingHostUuid;

	@Column(name = "meeting_id", nullable = false)
	private Long meetingId;

	@Column(name = "rating", nullable = false)
	private Integer rating;

	@Column(name = "meeting_review_content", length = 255)
	private String meeting_review_content;

}
