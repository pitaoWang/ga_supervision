package com.atosenet.ga.company.db.model.ga.employe;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@Table(name = "flight_dispatcher_license")
public class FlightDispatcherLicense {
	
	/** 飞行签派执照信息ID */
	@Id
	@Column(name = "id")
	public long id;
	
	/** 执照状态：在用，作废 */
	@Column(name = "license_state")
	@ApiModelProperty("执照状态：在用，作废")
	public java.lang.String licenseState;
	
	/** 执照类型：临时，永久 */
	@Column(name = "license_prop")
	@ApiModelProperty("执照类型：临时，永久")
	public java.lang.String licenseProp;
	
	/** 飞行签派执照颁发人 */
	@Column(name = "holder_name")
	@ApiModelProperty("飞行签派执照颁发人")
	public java.lang.String holderName;
	
	/** 持证人执业单位ID */
	@Column(name = "company_id")
	@ApiModelProperty("持证人执业单位ID")
	public long companyId;
	
}
