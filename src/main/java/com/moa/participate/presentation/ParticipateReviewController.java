package com.moa.participate.presentation;


import com.moa.global.common.ApiResult;
import com.moa.global.config.JwtTokenUtil;
import com.moa.participate.application.ParticipantApplicationService;
import com.moa.participate.application.ParticipantReviewService;
import com.moa.participate.dto.ParticipantReviewCreateDto;
import com.moa.participate.vo.request.ParticipantReviewCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/meeting-feature/participate")
@Slf4j
@RequiredArgsConstructor
public class ParticipateReviewController {

	private final ParticipantReviewService participantReviewService;
	private final ParticipantApplicationService participantApplicationService;
	private final ModelMapper modelMapper;
	private final JwtTokenUtil jwtTokenUtil;


	@Operation(summary = "모임 참가자 리뷰 작성", description = "모임 참가자 리뷰 작성")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	})
	@PostMapping("{participateId}/review")
	public ResponseEntity<ApiResult<Void>> createParticipantReview(@PathVariable("participateId") Long participateId,
		@RequestBody ParticipantReviewCreateRequest request,
		@RequestHeader(required = false, value = "Authorization") String token) {

		UUID loginUserUuid = UUID.fromString(jwtTokenUtil.getSubject(token));
		ParticipantReviewCreateDto participantReviewCreateDto = modelMapper.map(request, ParticipantReviewCreateDto.class);
		participantReviewCreateDto.setReviewrUserUuid(loginUserUuid);
		participantReviewCreateDto.setParticipantApplicationId(participateId);

		log.debug("uuid: " + loginUserUuid);

		// 리뷰 생성
		participantReviewService.createParticipantReview(participantReviewCreateDto);

		// 모임 참석 여부 업데이트
		participantApplicationService.updateParticipationStatusByReview(participateId, loginUserUuid, request.getParticipationStatus());

		return ResponseEntity.ok(ApiResult.ofSuccess(null));
	}

}
