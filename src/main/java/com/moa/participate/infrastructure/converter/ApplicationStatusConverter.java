package com.moa.participate.infrastructure.converter;


import com.moa.global.common.AbstractBaseEnumConverter;
import com.moa.participate.domain.ApplicationStatus;


public class ApplicationStatusConverter extends AbstractBaseEnumConverter<ApplicationStatus, Character, String> {

	public ApplicationStatusConverter() {
		super(ApplicationStatus.class);
	}

}
