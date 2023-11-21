package com.moa.participate.application;


import com.moa.participate.domain.ApplicationStatus;
import com.moa.participate.dto.ParticipantApplicationGetDto;
import com.moa.participate.dto.ParticipantCreateDto;

import java.util.List;
import java.util.UUID;


public interface ParticipantApplicationService {

	void createParticipantApplication(ParticipantCreateDto participantCreateDto);
	List<ParticipantApplicationGetDto> getParticipantApplicationListByApplicationStatus(UUID uuid, ApplicationStatus applicationStatus);
	List<ParticipantApplicationGetDto> getWaitParticipantApplicationListByMeetingId(Long meetingId);

}
