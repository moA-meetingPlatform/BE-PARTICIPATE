package com.moa.participate.vo.response;


import lombok.Getter;

import java.util.UUID;


@Getter
public class ParticipantApplicationWaitResponse {

	private Long id;

	private UUID participantUuid;

	private String meetingParticipationAnswer;

}
