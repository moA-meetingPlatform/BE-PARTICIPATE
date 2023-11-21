package com.moa.participate.dto;


import com.moa.participate.domain.ApplicationStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParticipantApplicationListItemGetDto {

	private Long meetingId;
	private ApplicationStatus applicationStatus;

}
