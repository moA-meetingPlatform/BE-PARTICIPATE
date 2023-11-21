package com.moa.participate.presentation;


import com.moa.global.common.ApiResult;
import com.moa.global.common.exception.CustomException;
import com.moa.global.common.exception.ErrorCode;
import com.moa.participate.application.ParticipantApplicationService;
import com.moa.participate.domain.ApplicationStatus;
import com.moa.participate.dto.ParticipantApplicationGetDto;
import com.moa.participate.dto.ParticipantCreateDto;
import com.moa.participate.vo.request.ParticipantCreateRequest;
import com.moa.participate.vo.response.MyParticipantApplicationListItemResponse;
import com.moa.participate.vo.response.MyParticipantApplicationListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/meeting-feature/participate")
@Slf4j
@RequiredArgsConstructor
public class ParticipateController {

	private final ModelMapper modelMapper;
	private final ParticipantApplicationService participantApplicationService;


	@Operation(summary = "모임 참가", description = "모임 참가")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	})
	@PostMapping("")
	public ResponseEntity<ApiResult<Void>> createParticipant(@RequestBody ParticipantCreateRequest request) {
		participantApplicationService.createParticipantApplication(modelMapper.map(request, ParticipantCreateDto.class));
		return ResponseEntity.ok(ApiResult.ofSuccess(null));
	}


	@Operation(summary = "내가 참가하는 모임 리스트 조회", description = "내가 참가하는 모임 조회")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK",
			content = @Content(schema = @Schema(implementation = MyParticipantApplicationListResponse.class))),
		@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	})
	@GetMapping("/my")
	public ResponseEntity<ApiResult<MyParticipantApplicationListResponse>> getParticipantList(@RequestParam("userUuid") UUID userUuid,
		@RequestParam("applicationStatus") String applicationStatusStr) {

		ApplicationStatus applicationStatus;
		try {
			applicationStatus = ApplicationStatus.findByTitle(applicationStatusStr);

			// 승인, 대기 상태 외에는 조회할 수 없다
			if (applicationStatus != ApplicationStatus.APPROVE && applicationStatus != ApplicationStatus.WAIT) throw new IllegalArgumentException();
		} catch (IllegalArgumentException e) {
			throw new CustomException(ErrorCode.BAD_REQUEST);
		}

		// 모임 참여 신청 리스트 조회
		List<ParticipantApplicationGetDto> dtoList = participantApplicationService.getParticipantApplicationListByApplicationStatus(userUuid, applicationStatus);
		List<MyParticipantApplicationListItemResponse> responseList =
			dtoList.stream()
				.map(MyParticipantApplicationListItemResponse::new)
				.toList();
		return ResponseEntity.ok(ApiResult.ofSuccess(new MyParticipantApplicationListResponse(responseList, responseList.size())));
	}

}
