package com.moa.participate.dto;


import com.moa.participate.domain.ApplicationStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParticipantApplicationGetDto {

	private Long id;

	private Long meetingId;

	private UUID participantUuid;

	private ApplicationStatus applicationStatus;

	private Boolean participationStatus;

	private String meetingParticipationAnswer;

	private Boolean refundRequiredStatus;

	private Float refundPercentage;

	private Integer refundAmount;

}
