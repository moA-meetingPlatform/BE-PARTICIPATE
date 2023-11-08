package com.moa.participate.domain;


import com.moa.participate.common.BaseCreateDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "temp_meeting")
public class TempMeeting extends BaseCreateDateTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "temporary_meeting_url", length = 5000)
	private String temporaryMeetingDataUrl;

	@Column(name = "user_uuid", nullable = false)
	private UUID userUuid;


	public void updateTemporaryMeetingDataUrl(String tempUrl) {
		this.temporaryMeetingDataUrl = tempUrl;
	}


	public void updateTempUrlNull() {
		this.temporaryMeetingDataUrl = null;
	}

}
