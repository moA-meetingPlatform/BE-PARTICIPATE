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
	private boolean updateByHost;


	public static ParticipantApplicationUpdateEventDto fromEntityAndPrevApplicationStatus(ParticipantApplication participantApplication, ApplicationStatus prevApplicationStatus,
		boolean updateByHost) {
		ParticipantApplicationUpdateEventDto dto = new ParticipantApplicationUpdateEventDto();
		dto.id = participantApplication.getId();
		dto.prevApplicationStatus = prevApplicationStatus;
		dto.currentApplicationStatus = participantApplication.getApplicationStatus();
		dto.meetingId = participantApplication.getMeetingId();
		dto.setUpdateByHost(updateByHost);
		return dto;
	}

}
