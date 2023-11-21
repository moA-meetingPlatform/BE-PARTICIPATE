package com.moa.participate.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
public class ParticipantApplicationListGetDto {

	private List<ParticipantApplicationGetDto> list; // 모임 참여 신청 리스트
	private Integer count; // 모임의 갯수

}
