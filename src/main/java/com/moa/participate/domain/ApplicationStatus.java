package com.moa.participate.domain;


import com.moa.global.common.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ApplicationStatus implements BaseEnum<Character, String> {
	APPROVE('A', "승인"),
	DENY('D', "거절"),
	WAIT('W', "대기"),
	FAIL('F', "실패"),
	CANCEL('C', "취소");

	private final Character code;
	private final String title;
}
