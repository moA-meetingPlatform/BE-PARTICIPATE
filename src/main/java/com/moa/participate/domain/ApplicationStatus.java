package com.moa.participate.domain;


import com.moa.global.common.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Getter
@AllArgsConstructor
public enum ApplicationStatus implements BaseEnum<Character, String> {
	APPROVE('A', "승인"),
	DENY('D', "거절"),
	WAIT('W', "대기"),
	FAIL('F', "실패"),
	CANCEL('C', "취소");

	private static final Map<String, ApplicationStatus> TITLE_OPERATOR_MAP =
		Collections.unmodifiableMap(Stream.of(values())
			.collect(Collectors.toMap(ApplicationStatus::getTitle, Function.identity())));
	private final Character code;
	private final String title;


	public static ApplicationStatus findByTitle(String title) {
		if (TITLE_OPERATOR_MAP.containsKey(title)) {
			return TITLE_OPERATOR_MAP.get(title);
		}
		throw new IllegalArgumentException("해당 enum 없음");
	}

}
