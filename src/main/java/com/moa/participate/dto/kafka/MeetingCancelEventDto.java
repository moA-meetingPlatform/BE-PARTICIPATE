package com.moa.participate.dto.kafka;


import lombok.*;


@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingCancelEventDto {

	private long meetingId;
	private boolean updateByHost;
	private int entryFee;

}
