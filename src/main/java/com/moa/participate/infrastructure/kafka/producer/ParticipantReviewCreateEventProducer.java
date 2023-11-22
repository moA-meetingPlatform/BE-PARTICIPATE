package com.moa.participate.infrastructure.kafka.producer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moa.global.common.exception.CustomException;
import com.moa.global.common.exception.ErrorCode;
import com.moa.participate.dto.kafka.ParticipantReviewCreateEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class ParticipantReviewCreateEventProducer {

	private final KafkaTemplate<String, String> kafkaTemplate;

	private final ObjectMapper objectMapper;
	private final String TOPIC = "participate-review-create";


	public void sendEvent(ParticipantReviewCreateEventDto dto) {
		log.debug(String.format("Produce dto : %s", dto));
		try {
			String message = objectMapper.writeValueAsString(dto);
			kafkaTemplate.send(TOPIC, message);
		} catch (JsonProcessingException e) {
			log.error("JsonProcessingException : {}", e.getMessage() + "\n" + dto.toString());
			throw new CustomException(ErrorCode.BAD_REQUEST);
		}
	}

}
