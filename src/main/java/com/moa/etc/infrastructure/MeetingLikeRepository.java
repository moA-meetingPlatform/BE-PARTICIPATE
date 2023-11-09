package com.moa.etc.infrastructure;


import com.moa.etc.domain.MeetingLike;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


public interface MeetingLikeRepository extends JpaRepository<MeetingLike, Long> {

	Boolean existsByMeetingIdAndUserUuid(Long meetingId, UUID userUuid);
	Slice<MeetingLike> findByUserUuid(UUID userUuid, Pageable pageable);
	@Transactional
	void deleteByMeetingIdAndUserUuid(Long meetingId, UUID userUuid);

}
