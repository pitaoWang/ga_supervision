/**  
 * 
 * @Title: AircraftCheckDTO.java 
 * @Package com.atosenet.ga.company.mgt.service.ga.company.dto  
 * @author xyx   
 * @date 2018年8月23日 下午4:33:06 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.service.ga.company.dto;

import java.util.List;

import com.atosenet.ga.company.db.model.ga.company.Aircraft;
import com.atosenet.ga.company.db.model.ga.company.CheckRecord;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: AircraftCheckDTO 
 * @author xyx 
 * @date 2018年8月23日 下午4:33:06 
 *  
 */
@Data
public class AircraftCheckDTO {
	@ApiModelProperty(value="航空器")
	private Aircraft aircraft;
	@ApiModelProperty(value="定检记录")
	private List<CheckRecord> checkRecord;
}
