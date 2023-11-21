package com.moa.participate.dto.kafka;


import com.moa.participate.domain.ApplicationStatus;
import com.moa.participate.domain.ParticipantApplication;
import lombok.*;


@ToString
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParticipantApplicationUpdateEventDto {

	private long id;
	private long meetingId;
	private ApplicationStatus prevApplicationStatus;
	private ApplicationStatus currentApplicationStatus;


	public static ParticipantApplicationUpdateEventDto fromEntityAndApplicationStatus(ParticipantApplication participantApplication, ApplicationStatus applicationStatus) {
		ParticipantApplicationUpdateEventDto dto = new ParticipantApplicationUpdateEventDto();
		dto.id = participantApplication.getId();
		dto.prevApplicationStatus = participantApplication.getApplicationStatus();
		dto.currentApplicationStatus = applicationStatus;
		dto.meetingId = participantApplication.getMeetingId();
		return dto;
	}

}
