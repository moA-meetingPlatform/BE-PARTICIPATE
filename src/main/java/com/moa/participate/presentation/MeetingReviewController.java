package com.moa.participate.presentation;


import com.moa.global.common.ApiResult;
import com.moa.global.common.SliceResponse;
import com.moa.participate.application.MeetingReviewService;
import com.moa.participate.dto.MeetingReviewGetDto;
import com.moa.participate.vo.request.ParticipantCreateRequest;
import com.moa.participate.vo.response.MeetingReviewResponse;
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
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/meeting-feature/meeting-review")
@Slf4j
@RequiredArgsConstructor
public class MeetingReviewController {

	private final ModelMapper modelMapper;
	private final MeetingReviewService meetingReviewService;


	@Operation(summary = "모임 리뷰 조회", description = "모임 리뷰 조회")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK",
			content = @Content(schema = @Schema(implementation = ParticipantCreateRequest.class))),
		@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	})
	@Parameters({
		@Parameter(in = ParameterIn.QUERY, name = "meetingHostUuid", description = "호스트 유저 uuid", required = true, example = "a642406c-6e20-11ee-b962-0242ac120002")
	})
	@GetMapping("")
	public ResponseEntity<ApiResult<SliceResponse<MeetingReviewResponse>>> getMeetingReviewList(@RequestParam("meetingHostUuid") UUID meetingHostUuid, Pageable pageable) {
		Slice<MeetingReviewGetDto> meetingReviewGetDtoSlice = meetingReviewService.getMeetingReviewListByHostUuid(meetingHostUuid, pageable);
		Slice<MeetingReviewResponse> meetingReviewResponseSlice = meetingReviewGetDtoSlice.map(meetingReviewGetDto -> modelMapper.map(meetingReviewGetDto, MeetingReviewResponse.class));
		return ResponseEntity.ok(ApiResult.ofSuccess(SliceResponse.of(meetingReviewResponseSlice)));
	}

}
