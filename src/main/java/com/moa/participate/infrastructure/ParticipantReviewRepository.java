package com.moa.participate.infrastructure;


import com.moa.participate.domain.ParticipantReview;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ParticipantReviewRepository extends JpaRepository<ParticipantReview, Long> {
}
