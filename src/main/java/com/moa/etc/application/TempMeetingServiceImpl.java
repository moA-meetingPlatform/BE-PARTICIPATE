package com.moa.etc.application;


import com.moa.etc.domain.TempMeeting;
import com.moa.etc.dto.TempMeetingCreateDto;
import com.moa.etc.dto.TempMeetingDeleteDto;
import com.moa.etc.dto.TempMeetingGetDto;
import com.moa.etc.infrastructure.TempMeetingRepository;
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
			tempMeetingRepository.save(tempMeetingCreateDto.toEntity());
		} else {    // 유저의 임시 미팅이 있으면 url 업데이트
			tempMeeting.updateTemporaryMeetingDataUrl(tempMeetingCreateDto.getTempUrl());
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
		String tempUrl = null;
		TempMeeting tempMeeting = tempMeetingRepository.getByUserUuid(userUuid);

		if (tempMeeting != null && tempMeeting.getTemporaryMeetingDataUrl() != null) {
			tempUrl = tempMeeting.getTemporaryMeetingDataUrl();
		}

		// 유저의 임시 미팅 테이블이 있고 url이 비어있지 않으면 url 리턴
		return new TempMeetingGetDto(tempUrl);
	}

}
