package com.moa.participate.domain;


import com.moa.global.common.BaseDateTime;
import com.moa.participate.infrastructure.converter.ApproveStatusConverter;
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
@Table(name = "participant_application")
public class ParticipantApplication extends BaseDateTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "meeting_id", nullable = false)
	private Long meetingId;

	@Column(name = "participant_uuid")
	private UUID participantUuid;

	@Convert(converter = ApproveStatusConverter.class)
	@Column(name = "approve_status", nullable = false)
	private ApproveStatus approveStatus;

	@Column(name = "participation_status", columnDefinition = "tinyint default 0")
	private Boolean participationStatus;

	@Column(name = "meeting_participation_answer")
	private String meetingParticipationAnswer;  // 모임 참여 질문에 대한 답변

	@Column(name = "refund_required_status", columnDefinition = "tinyint default 0", nullable = false)
	private Boolean refundRequiredStatus;

	@Column(name = "refund_percentage", columnDefinition = "float(3, 1)")
	private Float refundPercentage;

	@Column(name = "refund_amount")
	private Integer refundAmount;

}
