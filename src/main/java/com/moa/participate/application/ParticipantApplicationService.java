package com.moa.participate.application;


import com.moa.participate.domain.ApplicationStatus;
import com.moa.participate.dto.ParticipantApplicationGetDto;
import com.moa.participate.dto.ParticipantCreateDto;
import com.moa.participate.dto.kafka.MeetingCancelEventDto;
import com.moa.participate.dto.kafka.MeetingRefundNeedDto;

import java.util.List;
import java.util.UUID;


public interface ParticipantApplicationService {

	void createParticipantApplication(ParticipantCreateDto participantCreateDto);
	List<ParticipantApplicationGetDto> getParticipantApplicationListByApplicationStatus(UUID uuid, ApplicationStatus applicationStatus);
	List<ParticipantApplicationGetDto> getWaitParticipantApplicationListByMeetingId(Long meetingId);

	void updateParticipantApplicationByHost(Long id, ApplicationStatus applicationStatus);

	void cancelParticipantApplication(Long id, UUID userUuid);

	void updateParticipationStatusByReview(Long meetingId, UUID targetUuid, boolean participationStatus);

	void updateParticipantRefundInfoByKafka(MeetingRefundNeedDto dto);

	void updateParticipantRefundInfoByMeetingCancelEvent(MeetingCancelEventDto cancelEventDto);

}
