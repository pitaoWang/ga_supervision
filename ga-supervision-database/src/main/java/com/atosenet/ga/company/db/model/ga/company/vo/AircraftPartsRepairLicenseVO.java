/**  
 * 
 * @Title: AircraftPartsRepairLicenseVO.java 
 * @Package com.atosenet.ga.company.db.model.ga.company.vo  
 * @author xyx   
 * @date 2018年9月4日 下午2:32:39 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.model.ga.company.vo;

import com.atosenet.ga.company.db.model.ga.company.AircraftPartsRepairLicense;
import com.atosenet.ga.company.db.model.ga.employe.PersonQulificationInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: AircraftPartsRepairLicenseVO 
 * @author xyx 
 * @date 2018年9月4日 下午2:32:39 
 *  
 */
@Data
public class AircraftPartsRepairLicenseVO {
	@ApiModelProperty(value="航空器部件维修执照基本信息")
	private PersonQulificationInfo personQulificationInfo;
	@ApiModelProperty(value="航空器部件维修执照详细信息")
	private AircraftPartsRepairLicense aircraftPartsRepairLicense;
}
