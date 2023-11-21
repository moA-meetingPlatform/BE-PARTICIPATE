package com.moa.participate.vo.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class ParticipantApplicationWaitListResponse {

	private List<ParticipantApplicationWaitResponse> list;
	private Integer count;

}
