package com.moa.participate.infrastructure;


import com.moa.participate.domain.MeetingReview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface MeetingReviewRepository extends JpaRepository<MeetingReview, Long> {

	Slice<MeetingReview> findByMeetingHostUuid(UUID uuid, Pageable pageable);

}
