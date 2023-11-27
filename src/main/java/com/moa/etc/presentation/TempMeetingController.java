package com.moa.etc.presentation;


import com.moa.etc.application.TempMeetingService;
import com.moa.etc.dto.TempMeetingCreateDto;
import com.moa.etc.dto.TempMeetingGetDto;
import com.moa.etc.vo.request.TempMeetingCreateRequest;
import com.moa.etc.vo.response.TempMeetingGetResponse;
import com.moa.global.common.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/meeting-feature/temp-meeting")
@Slf4j
@RequiredArgsConstructor
public class TempMeetingController {

	private final ModelMapper modelMapper;
	private final TempMeetingService tempMeetingService;


	@Operation(summary = "유저의 임시 모임 데이터 조회", description = "모임 임시 데이터 조회, 유저 uuid 필요, 없을 경우 \"tempUrl\": null 반환")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
		@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	})
	@Parameters({
		@Parameter(in = ParameterIn.QUERY, name = "userUuid", description = "유저 uuid", required = true, example = "a642406c-6e20-11ee-b962-0242ac120002")
	})
	@GetMapping("")
	public ResponseEntity<ApiResult<TempMeetingGetResponse>> getTempMeetingByUuid(@RequestParam("userUuid") UUID userUuid) {
		TempMeetingGetDto tempMeetingGetDto = tempMeetingService.getTempMeetingByUuid(userUuid);
		return ResponseEntity.ok(ApiResult.ofSuccess(modelMapper.map(tempMeetingGetDto, TempMeetingGetResponse.class)));
	}


	@Operation(summary = "유저의 임시 모임 생성 또는 update", description = "모임 임시 데이터 (url 형식) 저장 또는 덮어쓰기, 유저 uuid 필요")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
		@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	})
	@PutMapping("")
	public ResponseEntity<ApiResult<Void>> createOrUpdateTempMeeting(@RequestBody TempMeetingCreateRequest request) {
		TempMeetingCreateDto dto = modelMapper.map(request, TempMeetingCreateDto.class);
		tempMeetingService.createOrUpdateTempMeeting(dto);
		return ResponseEntity.ok(ApiResult.ofSuccess(null));
	}

}
