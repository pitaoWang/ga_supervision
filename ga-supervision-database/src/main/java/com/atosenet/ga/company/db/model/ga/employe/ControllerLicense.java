/**  
 * 
 * @Title: ControllerLicense.java 
 * @Package com.atosenet.ga.company.db.model.ga.employe  
 * @author dgw   
 * @date 2018年8月10日 上午9:25:43 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.model.ga.employe;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 
 * TODO(管制人员执照)
 * 
 * @ClassName: ControllerLicense 
 * @author dgw 
 * @date 2018年8月10日 上午9:25:43 
 *  
 */
@Entity
@Data
@Table(name = "controller_license")
public class ControllerLicense {
	
	/**管制人员执照id**/
	@Id
	@Column(name = "license_id")
	private long licenseId;
	
	/**管制人员id**/
	@Column(name = "user_id")
	@ApiModelProperty("人员id")
	private long userId;
	
	/**执照编号**/
	@Column(name = "license_code")
	@ApiModelProperty("执照编号")
	private long licenseCode;
	
	/**签发日期**/
	@Column(name = "given_date")
	@ApiModelProperty("签发日期")
	private Date givenDate;
	
	/**签发单位**/
	@Column(name = "given_unit")
	@ApiModelProperty("签发单位")
	private String givenUnit;
	
	/**更新日期**/
	@Column(name = "update_date")
	@ApiModelProperty("更新日期")
	private Date updateDate;
	
	/**有效期限**/
	@Column(name = "userful_time")
	@ApiModelProperty("有效期限")
	private Date userfulTime;
	
	/**执照扫描件**/
	@Column(name = "scan_pic")
	@ApiModelProperty("执照扫描件")
	private String scanPic;
}
