/**  
 * 
 * @Title: FlightDispatcherLicenseDTO.java 
 * @Package com.atosenet.ga.company.db.model.ga.employe  
 * @author xyx   
 * @date 2018年9月4日 下午3:21:31 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.model.ga.employe;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: FlightDispatcherLicenseDTO 
 * @author xyx 
 * @date 2018年9月4日 下午3:21:31 
 *  
 */
@Data
public class FlightDispatcherLicenseDTO {
	@ApiModelProperty(value="飞行签派员执照基本信息")
    private PersonQulificationInfo personQulificationInfo;
	@ApiModelProperty(value="飞行签派员执照详细信息")
	private FlightDispatcherLicense flightDispatcherLicense;
}
