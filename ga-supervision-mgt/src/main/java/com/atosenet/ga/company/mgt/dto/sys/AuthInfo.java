package com.atosenet.ga.company.mgt.dto.sys;

import com.atosenet.ga.company.core.common.model.response.BaseResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class AuthInfo extends BaseResponse{

	private static final long serialVersionUID = 1L;

	String tk;
	
	String un;
	
	Long ts;
}
