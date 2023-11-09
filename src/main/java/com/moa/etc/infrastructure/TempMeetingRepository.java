package com.moa.etc.infrastructure;


import com.moa.etc.domain.TempMeeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface TempMeetingRepository extends JpaRepository<TempMeeting, Long> {

	TempMeeting getByUserUuid(UUID userUuid);

}
