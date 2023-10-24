package com.moa.participate.infrastructure;


import java.util.UUID;


public interface MeetingLikeQueryDslRepository {

	void deleteByMeetingIdAndUserUuid(Long meetingId, UUID userUuid);

}
