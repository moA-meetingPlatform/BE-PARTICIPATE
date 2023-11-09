package com.moa.etc.domain;


import com.moa.global.common.BaseCreateDateTime;
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
@Table(name = "meeting_like")
public class MeetingLike extends BaseCreateDateTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_uuid", nullable = false)
	private UUID userUuid;

	@Column(name = "meeting_id", nullable = false)
	private Long meetingId;

}
