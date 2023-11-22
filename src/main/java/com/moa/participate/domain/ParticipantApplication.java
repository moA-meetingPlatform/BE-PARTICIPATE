package com.moa.participate.domain;


import com.moa.global.common.BaseDateTime;
import com.moa.participate.infrastructure.converter.ApplicationStatusConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "participant_application")
public class ParticipantApplication extends BaseDateTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "meeting_id", nullable = false)
	private Long meetingId;

	@Column(name = "participant_uuid")
	private UUID participantUuid;

	@Convert(converter = ApplicationStatusConverter.class)
	@Column(name = "application_status", nullable = false)
	private ApplicationStatus applicationStatus;

	@Column(name = "participation_status", columnDefinition = "tinyint")
	private Boolean participationStatus;

	@Column(name = "meeting_participation_answer")
	private String meetingParticipationAnswer;  // 모임 참여 질문에 대한 답변

	@Column(name = "refund_required_status", columnDefinition = "tinyint default 0", nullable = false)
	private Boolean refundRequiredStatus;

	@Column(name = "refund_percentage", columnDefinition = "float(3, 1)")
	private Float refundPercentage;

	@Column(name = "refund_amount")
	private Integer refundAmount;


	public ParticipantApplication(Long meetingId, UUID participantUuid, String meetingParticipationAnswer, ApplicationStatus applicationStatus) {
		this.meetingId = meetingId;
		this.participantUuid = participantUuid;
		this.meetingParticipationAnswer = meetingParticipationAnswer;
		this.applicationStatus = applicationStatus;
		this.refundRequiredStatus = false;
		this.refundPercentage = 0.0f;
		this.refundAmount = 0;
	}


	public void setApplicationStatus(ApplicationStatus applicationStatus) {
		this.applicationStatus = applicationStatus;
	}


	public void setRefundData(Float refundPercentage, Integer entryFee) {
		this.refundRequiredStatus = true;
		this.refundAmount = (int) (entryFee * (1 - refundPercentage));
		this.refundPercentage = refundPercentage;
	}


	public void setParticipationStatus(Boolean participationStatus) {
		this.participationStatus = participationStatus;
	}

}
