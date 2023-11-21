package com.moa.participate.vo.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
public class MyParticipantApplicationListResponse {

	private List<MyParticipantApplicationListItemResponse> list; // 모임 참여 신청 리스트
	private Integer count; // 모임의 갯수

}
