package com.moa.participate.dto;


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

}
