package com.moa.participate.application;


import com.moa.participate.common.exception.CustomException;
import com.moa.participate.common.exception.ErrorCode;
import com.moa.participate.domain.TempMeeting;
import com.moa.participate.dto.TempMeetingCreateDto;
import com.moa.participate.dto.TempMeetingDeleteDto;
import com.moa.participate.dto.TempMeetingGetDto;
import com.moa.participate.infrastructure.TempMeetingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class TempMeetingServiceImpl implements TempMeetingService {

	private final ModelMapper modelMapper;

	private final TempMeetingRepository tempMeetingRepository;


	@Transactional
	@Override
	public void createOrUpdateTempMeeting(TempMeetingCreateDto tempMeetingCreateDto) {
		TempMeeting tempMeeting = tempMeetingRepository.getByUserUuid(tempMeetingCreateDto.getUserUuid());
		if (tempMeeting == null) {    // 유저의 임시 미팅이 없으면 생성
			tempMeetingRepository.save(modelMapper.map(tempMeetingCreateDto, TempMeeting.class));
		} else {    // 유저의 임시 미팅이 있으면 url 업데이트
			tempMeeting.updateTempUrl(tempMeetingCreateDto.getTempUrl());
		}
	}


	@Transactional
	@Override
	public void deleteTempMeeting(TempMeetingDeleteDto tempMeetingDeleteDto) {
		// 유저의 임시 미팅이 있으면 url 업데이트
		TempMeeting tempMeeting = tempMeetingRepository.getByUserUuid(tempMeetingDeleteDto.getUserUuid());
		if (tempMeeting != null) {
			tempMeeting.updateTempUrlNull();
		}
	}


	@Override
	public TempMeetingGetDto getTempMeetingByUuid(UUID userUuid) {
		TempMeeting tempMeeting = tempMeetingRepository.getByUserUuid(userUuid);

		if (tempMeeting == null || tempMeeting.getTempUrl() == null) {
			// 유저의 임시 미팅 테이블이 없거나 url이 비어있으면 에러
			throw new CustomException(ErrorCode.NOT_FOUND_RESOURCE);
		}

		// 유저의 임시 미팅 테이블이 있고 url이 비어있지 않으면 url 리턴
		return modelMapper.map(tempMeeting, TempMeetingGetDto.class);
	}

}
