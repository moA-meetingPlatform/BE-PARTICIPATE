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

	@Column(name = "temp_url")
	private String tempUrl;

	@Column(name = "user_uuid", nullable = false)
	private UUID userUuid;


	public void updateTempUrl(String tempUrl) {
		this.tempUrl = tempUrl;
	}


	public void updateTempUrlNull() {
		this.tempUrl = null;
	}

}
