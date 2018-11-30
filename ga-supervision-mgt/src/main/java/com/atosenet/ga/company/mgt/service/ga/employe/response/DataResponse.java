package com.atosenet.ga.company.mgt.service.ga.employe.response;

import com.atosenet.ga.company.core.common.model.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
 * 创建时间:  2017/07/25 18:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class DataResponse<T> extends BaseResponse {
	private static final long serialVersionUID = 1L;
	
	/** 数据 **/
    private T data = null;
    
    private Object dataOther=null;
}