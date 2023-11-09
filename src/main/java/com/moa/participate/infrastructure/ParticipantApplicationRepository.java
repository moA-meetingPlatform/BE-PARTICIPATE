package com.moa.participate.infrastructure;


import com.moa.participate.domain.ParticipantApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface ParticipantApplicationRepository extends JpaRepository<ParticipantApplication, Long> {

	Optional<ParticipantApplication> findByMeetingIdAndParticipantUuid(Long meetingId, UUID participantUuid);

}
