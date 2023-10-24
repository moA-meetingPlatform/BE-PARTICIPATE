package com.moa.participate.infrastructure.converter;


import com.moa.participate.common.AbstractBaseEnumConverter;
import com.moa.participate.domain.ApproveStatus;


public class ApproveStatusConverter extends AbstractBaseEnumConverter<ApproveStatus, Character, String> {

	public ApproveStatusConverter() {
		super(ApproveStatus.class);
	}

}
