package com.moa.participate.dto;


import lombok.*;

import java.util.UUID;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeetingLikeDeleteDto {

	private Long meetingId;
	private UUID userUuid;

}
