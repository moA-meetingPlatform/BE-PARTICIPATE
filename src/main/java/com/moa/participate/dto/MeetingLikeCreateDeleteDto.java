package com.moa.participate.dto;


import lombok.*;

import java.util.UUID;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeetingLikeCreateDeleteDto {

	private Long meetingId;
	private UUID userUuid;

}
