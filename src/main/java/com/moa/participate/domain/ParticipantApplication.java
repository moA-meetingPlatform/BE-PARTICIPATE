package com.moa.participate.domain;


import com.moa.participate.common.BaseDateTime;
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

	@Column(name = "user_uuid")
	private UUID userUuid;

	@Convert(converter = ApproveStatusConverter.class)
	@Column(name = "approve_status", nullable = false)
	private ApproveStatus approveStatus;

	@Column(name = "is_attend", columnDefinition = "tinyint default 0")
	private Boolean isAttend;

	@Column(name = "meeting_answer")
	private String meetingAnswer;

	@Column(name = "is_refund_need", columnDefinition = "tinyint default 0", nullable = false)
	private Boolean isRefundNeed;

	@Column(name = "refund_rate", columnDefinition = "float(3, 1)")
	private Float refundRate;


}
