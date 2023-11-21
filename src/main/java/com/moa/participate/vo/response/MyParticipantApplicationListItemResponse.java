package com.moa.participate.vo.response;


import com.moa.participate.dto.ParticipantApplicationGetDto;
import lombok.*;


@ToString
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyParticipantApplicationListItemResponse {

	private Long meetingId;
	private String applicationStatus;


	/**
	 * dto를 받아서 response로 변환
	 * (enum을 string으로 변환)
	 *
	 * @param dto
	 */
	public MyParticipantApplicationListItemResponse(ParticipantApplicationGetDto dto) {
		this.meetingId = dto.getMeetingId();
		this.applicationStatus = dto.getApplicationStatus().getTitle();
	}

}
