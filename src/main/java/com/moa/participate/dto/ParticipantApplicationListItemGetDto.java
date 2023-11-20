package com.moa.participate.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
public class ParticipantApplicationListItemGetDto {

	private Long meetingId;
	private LocalDate applicationDate;

}
