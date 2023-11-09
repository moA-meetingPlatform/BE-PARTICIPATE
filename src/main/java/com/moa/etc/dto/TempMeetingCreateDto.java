package com.moa.etc.dto;


import com.moa.etc.domain.TempMeeting;
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
