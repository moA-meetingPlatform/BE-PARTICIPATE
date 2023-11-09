package com.moa.participate.presentation;


import com.moa.global.common.ApiResult;
import com.moa.participate.application.ParticipantApplicationService;
import com.moa.participate.dto.ParticipantCreateDto;
import com.moa.participate.vo.request.ParticipantCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/participant")
@Slf4j
@RequiredArgsConstructor
public class ParticipateController {

	private final ModelMapper modelMapper;
	private final ParticipantApplicationService participantApplicationService;


	@Operation(summary = "모임 참가", description = "모임 참가")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK",
			content = @Content(schema = @Schema(implementation = ParticipantCreateRequest.class))),
		@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	})
	@PostMapping("")
	public ResponseEntity<ApiResult<Void>> createParticipant(@RequestBody ParticipantCreateRequest request) {
		participantApplicationService.createParticipantApplication(modelMapper.map(request, ParticipantCreateDto.class));
		return ResponseEntity.ok(ApiResult.ofSuccess(null));
	}

}
