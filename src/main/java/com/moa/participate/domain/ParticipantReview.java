package com.moa.participate.domain;


import com.moa.global.common.BaseCreateDateTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "participant_review")
public class ParticipantReview extends BaseCreateDateTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "reviewer_user_uuid")
	private UUID reviewerUserUuid;

	@Column(name = "review_target_user_uuid")
	private UUID reviewTargetUserUuid;

	@Column(name = "rating")
	private Integer rating;

	@ManyToOne
	@JoinColumn(name = "participant_application_id")
	private ParticipantApplication participantApplication;


	public ParticipantReview(UUID reviewerUserUuid, UUID reviewTargetUserUuid, Integer rating, ParticipantApplication participantApplication) {
		this.reviewerUserUuid = reviewerUserUuid;
		this.reviewTargetUserUuid = reviewTargetUserUuid;
		this.rating = rating;
		this.participantApplication = participantApplication;
	}

}
