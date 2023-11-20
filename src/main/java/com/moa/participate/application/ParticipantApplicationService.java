package com.moa.participate.application;


import com.moa.participate.domain.ApplicationStatus;
import com.moa.participate.dto.ParticipantApplicationListGetDto;
import com.moa.participate.dto.ParticipantCreateDto;

import java.util.UUID;


public interface ParticipantApplicationService {

	void createParticipantApplication(ParticipantCreateDto participantCreateDto);
	ParticipantApplicationListGetDto getParticipantApplicationListByApplicationStatus(UUID uuid, ApplicationStatus applicationStatus);

}
