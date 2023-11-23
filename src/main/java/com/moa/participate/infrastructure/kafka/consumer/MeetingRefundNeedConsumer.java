package com.moa.participate.infrastructure.kafka.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moa.global.common.exception.CustomException;
import com.moa.global.common.exception.ErrorCode;
import com.moa.participate.application.ParticipantApplicationService;
import com.moa.participate.dto.kafka.MeetingRefundNeedDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class MeetingRefundNeedConsumer {

	private final ObjectMapper objectMapper;
	private final ParticipantApplicationService participantApplicationService;


	@KafkaListener(topics = "participate-update", groupId = "participate-update")
	public void consume(String message) {
		log.debug(String.format("Consumed message : %s", message));

		Map<Object, Object> map;

		try {
			map = objectMapper.readValue(message, Map.class);
		} catch (JsonProcessingException e) {
			log.error("JsonProcessingException : {}", e.getMessage() + "\n" + message);
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}

		MeetingRefundNeedDto dto = MeetingRefundNeedDto.builder()
			.meetingId(((Number) (map.get("meetingId"))).longValue())
			.participateId(((Number) (map.get("participateId"))).longValue())
			.refundPercentage(((Number) (map.get("refundPercentage"))).floatValue())
			.refundAmount(((Number) (map.get("refundAmount"))).intValue())
			.build();

		log.debug("dto : {}", dto.toString());
		participantApplicationService.updateParticipantRefundInfoByKafka(dto);
	}

}
