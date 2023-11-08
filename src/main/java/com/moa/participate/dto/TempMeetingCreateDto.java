package com.moa.participate.dto;


import com.moa.participate.domain.TempMeeting;
import lombok.*;

import java.util.UUID;


@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TempMeetingCreateDto {

	private String tempUrl;
	private UUID userUuid;


	public TempMeeting toEntity() {
		return TempMeeting.builder()
			.temporaryMeetingDataUrl(tempUrl)
			.userUuid(userUuid)
			.build();
	}

}
