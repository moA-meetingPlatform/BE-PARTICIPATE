package com.moa.participate.dto;


import lombok.Getter;

import java.util.UUID;


@Getter
public class ParticipantCreateDto {

	private Long meetingId;
	private UUID participantUuid;
	private String meetingParticipationAnswer;

}
