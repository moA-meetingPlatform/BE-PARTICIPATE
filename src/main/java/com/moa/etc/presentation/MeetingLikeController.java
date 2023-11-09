package com.moa.etc.presentation;


import com.moa.etc.application.MeetingLikeService;
import com.moa.etc.dto.MeetingLikeCreateDeleteDto;
import com.moa.global.common.ApiResult;
import com.moa.global.common.SliceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/meeting-feature/meeting")
@Slf4j
@RequiredArgsConstructor
public class MeetingLikeController {

	private final MeetingLikeService meetingLikeService;


	@Operation(summary = "모임 즐겨찾기 추가 및 삭제", description = "모임 즐겨찾기 추가 및 삭제")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
		@ApiResponse(responseCode = "409", description = "DUPLICATE_RESOURCE"),
		@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	})
	@Parameters({
		@Parameter(in = ParameterIn.QUERY, name = "userUuid", description = "유저 uuid", required = true, example = "a642406c-6e20-11ee-b962-0242ac120002"),
	})
	@PutMapping("/like/{meetingId}")
	public ResponseEntity<ApiResult<Void>> createMeetingLike(@PathVariable("meetingId") Long meetingId, @RequestParam("userUuid") UUID userUuid) {
		MeetingLikeCreateDeleteDto dto = MeetingLikeCreateDeleteDto.builder()
			.meetingId(meetingId)
			.userUuid(userUuid)
			.build();
		meetingLikeService.createOrDeleteMeetingLike(dto);
		return ResponseEntity.ok(ApiResult.ofSuccess(null));
	}


	@Operation(summary = "사용자가 좋아요한 모임 목록 조회", description = "사용자가 좋아요한 모임 목록 조회")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK",
			content = @Content(schema = @Schema(implementation = SliceResponse.class))),
		@ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
		@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	})
	@Parameters({
		@Parameter(in = ParameterIn.QUERY, name = "userUuid", description = "유저 uuid", required = true, example = "a642406c-6e20-11ee-b962-0242ac120002"),
	})
	@GetMapping("/like")
	public ResponseEntity<ApiResult<SliceResponse<Long>>> getMeetingLikeSlice(@RequestParam("userUuid") UUID userUuid, Pageable pageable) {
		Slice<Long> meetingIdSlice = meetingLikeService.getMeetingLikeSlice(userUuid, pageable);
		return ResponseEntity.ok(ApiResult.ofSuccess(SliceResponse.of(meetingIdSlice)));
	}

}
