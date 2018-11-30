/**  
 * 
 * @Title: IntelligenceLicenseDTO.java 
 * @Package com.atosenet.ga.company.db.model.ga.employe  
 * @author xyx   
 * @date 2018年9月4日 下午3:31:14 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.model.ga.employe;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: IntelligenceLicenseDTO 
 * @author xyx 
 * @date 2018年9月4日 下午3:31:14 
 *  
 */
@Data
public class IntelligenceLicenseDTO {
	@ApiModelProperty(value="情报基本信息")
    private PersonQulificationInfo personQulificationInfo;
	@ApiModelProperty(value="情报执照详细信息")
	private IntelligenceLicense intelligenceLicense;
}
