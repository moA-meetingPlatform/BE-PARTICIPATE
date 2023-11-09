package com.moa.participate.vo.request;


import lombok.Getter;

import java.util.UUID;


@Getter
public class ParticipantCreateRequest {

	private Long meetingId;
	private UUID participantUuid;
	private String meetingParticipationAnswer;

}
