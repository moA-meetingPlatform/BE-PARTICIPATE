package com.moa.participate.infrastructure;


import com.moa.participate.domain.ParticipantApplication;
import com.moa.participate.domain.ParticipantReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface ParticipantReviewRepository extends JpaRepository<ParticipantReview, Long> {

	boolean existsByParticipantApplication(ParticipantApplication participantApplication);

	boolean existsByParticipantApplicationAndReviewerUserUuid(ParticipantApplication participantApplication, UUID reviewerUserUuid);

}
