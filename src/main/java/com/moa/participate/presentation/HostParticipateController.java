package com.moa.participate.presentation;


import com.moa.global.common.ApiResult;
import com.moa.global.common.exception.CustomException;
import com.moa.global.common.exception.ErrorCode;
import com.moa.participate.application.ParticipantApplicationService;
import com.moa.participate.dto.ParticipantApplicationGetDto;
import com.moa.participate.vo.response.ParticipantApplicationWaitListResponse;
import com.moa.participate.vo.response.ParticipantApplicationWaitResponse;
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


@RestController
@RequestMapping("/api/v1/meeting-feature/participate")
@Slf4j
@RequiredArgsConstructor
public class HostParticipateController {

	private final ModelMapper modelMapper;
	private final ParticipantApplicationService participantApplicationService;


	@Operation(summary = "모임 참여 신청 리스트 조회", description = "모임 참여 신청 리스트 조회")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK",
			content = @Content(schema = @Schema(implementation = ParticipantApplicationWaitListResponse.class))),
		@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	})
	@GetMapping("/meeting/{meetingId}")
	public ResponseEntity<ApiResult<ParticipantApplicationWaitListResponse>> getParticipantApplicationListByMeetingId(@PathVariable("meetingId") Long meetingId) {
		List<ParticipantApplicationGetDto> dtoList = participantApplicationService.getWaitParticipantApplicationListByMeetingId(meetingId);
		List<ParticipantApplicationWaitResponse> responseList = dtoList.stream()
			.map(o -> modelMapper.map(o, ParticipantApplicationWaitResponse.class))
			.toList();
		return ResponseEntity.ok(ApiResult.ofSuccess(new ParticipantApplicationWaitListResponse(responseList, responseList.size())));
	}


	@Operation(summary = "모임 참여 신청 승인(Y) / 거절(N), status: Y, N", description = "ParticipantApplication의 status를 변경, 승인시 Meeting의 currentParticipantCount 증가")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	})
	@PostMapping("/{participateId}/{status}")
	public ResponseEntity<ApiResult<Void>> modifyParticipantApplication(@PathVariable("participateId") Long participateId, @PathVariable("status") String status) {
		if (status.equals("Y")) {
			participantApplicationService.approveParticipantApplication(participateId);
		} else if (status.equals("N")) {
			participantApplicationService.denyParticipantApplication(participateId);
		} else {
			throw new CustomException(ErrorCode.BAD_REQUEST);
		}
		return ResponseEntity.ok(ApiResult.ofSuccess(null));
	}

}
