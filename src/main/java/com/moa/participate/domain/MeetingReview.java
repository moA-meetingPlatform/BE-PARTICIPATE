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

	@Column(name = "host_user_uuid", nullable = false)
	private UUID hostUserUuid;

	@Column(name = "meeting_id", nullable = false)
	private Long meetingId;

	@Column(name = "rating", nullable = false)
	private Integer rating;

	@Column(name = "content", length = 255)
	private String content;
}
