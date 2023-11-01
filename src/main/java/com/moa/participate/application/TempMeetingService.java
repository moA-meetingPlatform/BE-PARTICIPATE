package com.moa.participate.application;


import com.moa.participate.dto.TempMeetingCreateDto;
import com.moa.participate.dto.TempMeetingDeleteDto;
import com.moa.participate.dto.TempMeetingGetDto;

import java.util.UUID;


public interface TempMeetingService {

	/**
	 * 유저의 uuid로 조회하여 유저의 임시 미팅 테이블이 없으면 생성하고, 있으면 임시 미팅 데이터(tempUrl)를 수정한다.
	 *
	 * @param tempMeetingCreateDto 유저의 uuid, 임시 미팅 데이터(url 형식)
	 */
	void createOrUpdateTempMeeting(TempMeetingCreateDto tempMeetingCreateDto);

	/**
	 * 임시 미팅 삭제,
	 * 실제 동작은 db 컬럼을 빈 문자열로 update함
	 *
	 * @param tempMeetingDeleteDto 임시 미팅 삭제 dto, 유저 한명당 임시미팅 하나만 저장되므로 유저의 uuid만 받음
	 */
	void deleteTempMeeting(TempMeetingDeleteDto tempMeetingDeleteDto);

	/**
	 * 유저의 임시 미팅 조회
	 * 유저의 임시 미팅 테이블이 있고 url이 비어있지 않으면 url 리턴
	 *
	 * @param userUuid 유저의 uuid
	 * @return 유저의 임시 미팅 데이터, url 형식
	 */
	TempMeetingGetDto getTempMeetingByUuid(UUID userUuid);

}
