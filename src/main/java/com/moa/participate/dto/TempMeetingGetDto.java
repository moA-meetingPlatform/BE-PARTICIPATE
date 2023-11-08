package com.moa.participate.dto;


import com.moa.participate.domain.TempMeeting;
import lombok.Getter;


@Getter
public class TempMeetingGetDto {

	private String tempUrl;


	public TempMeetingGetDto(TempMeeting tempMeeting) {
		this.tempUrl = tempMeeting.getTemporaryMeetingDataUrl();
	}

}
