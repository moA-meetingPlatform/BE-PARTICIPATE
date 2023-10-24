package com.moa.participate.application;


import com.moa.participate.dto.MeetingLikeCreateDto;
import com.moa.participate.dto.MeetingLikeDeleteDto;


public interface MeetingLikeService {

	void createMeetingLike(MeetingLikeCreateDto meetingLikeCreateDto);
	void deleteMeetingLike(MeetingLikeDeleteDto meetingLikeDeleteDto);

}
