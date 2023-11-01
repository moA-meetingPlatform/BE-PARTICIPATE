package com.moa.participate.infrastructure;


import com.moa.participate.domain.MeetingLike;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface MeetingLikeRepository extends JpaRepository<MeetingLike, Long> {

	Boolean existsByMeetingIdAndUserUuid(Long meetingId, UUID userUuid);
	Slice<MeetingLike> findByUserUuid(UUID userUuid, Pageable pageable);

}
