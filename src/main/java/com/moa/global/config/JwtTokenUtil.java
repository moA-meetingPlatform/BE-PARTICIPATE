package com.moa.global.config;


import org.springframework.boot.json.BasicJsonParser;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Map;

import static java.util.Base64.getUrlDecoder;


@Service
public class JwtTokenUtil {

	public String getSubject(String token) {
		return getPayloadMap(token).get("sub").toString();
	}


	/**
	 * JWT 토큰에서 payload를 추출합니다.
	 *
	 * @param token Authorization 헤더에서 추출한 JWT 토큰
	 * @return payload map
	 */
	public Map<String, Object> getPayloadMap(String token) {
		token = token.replace("Bearer ", "");   // 토큰 앞의 "Bearer " 문자열 제거
		String payloadJwt = token.split("\\.")[1];  // payload (두번째 파트) 추출

		// Base64 디코딩
		Base64.Decoder decoder = getUrlDecoder();
		String payload = new String(decoder.decode(payloadJwt));

		// JSON 파싱
		BasicJsonParser parser = new BasicJsonParser();
		return parser.parseMap(payload);
	}

}
