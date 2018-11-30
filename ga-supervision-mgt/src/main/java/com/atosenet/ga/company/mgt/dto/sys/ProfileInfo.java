package com.atosenet.ga.company.mgt.dto.sys;

import java.util.Map;
import java.util.Set;

import com.atosenet.ga.company.core.common.model.response.BaseResponse;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ProfileInfo extends BaseResponse{

	private static final long serialVersionUID = 1L;

	//User user;
	Map<String, Object> user = Maps.newHashMap();
	Set<String> roles = Sets.newHashSet();
	//Org org;
	Map<String, Object> org = Maps.newHashMap();
	
	Map<String, Object> buz = Maps.newHashMap();
}
