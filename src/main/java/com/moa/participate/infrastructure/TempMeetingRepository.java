package com.moa.participate.infrastructure;


import com.moa.participate.domain.TempMeeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface TempMeetingRepository extends JpaRepository<TempMeeting, Long> {

	Optional<TempMeeting> findByUserUuid(UUID userUuid);
	TempMeeting getByUserUuid(UUID userUuid);

}
