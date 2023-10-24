package com.moa.participate.domain;


import com.moa.participate.common.BaseCreateDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "participant_review")
public class ParticipantReview extends BaseCreateDateTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "reviewer_user_uuid")
	private String reviewerUserUuid;

	@ManyToOne
	@JoinColumn(name = "participant_application_id")
	private ParticipantApplication participantApplication;

}
