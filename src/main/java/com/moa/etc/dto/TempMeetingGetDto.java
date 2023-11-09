package com.moa.etc.dto;


import com.moa.etc.domain.TempMeeting;
import lombok.Getter;


@Getter
public class TempMeetingGetDto {

	private String tempUrl;


	public TempMeetingGetDto(TempMeeting tempMeeting) {
		this.tempUrl = tempMeeting.getTemporaryMeetingDataUrl();
	}

}
