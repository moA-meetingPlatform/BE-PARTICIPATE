package com.moa.etc.vo.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;


@ToString
@Getter
public class TempMeetingCreateRequest {

	@Schema(description = "임시 모임 데이터 (url 형식)", nullable = false, requiredMode = Schema.RequiredMode.REQUIRED, example = "moa.com/meeting/create?data=1")
	private String tempUrl;

	@Schema(description = "유저 uuid", nullable = false, requiredMode = Schema.RequiredMode.REQUIRED, example = "a642406c-6e20-11ee-b962-0242ac120002")
	private UUID userUuid;

}
