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
 * 
 * @author lvhao
 * 飞行执照签注信息
 */
@Entity
@Data
@Table(name = "pilotlicense_check")
public class PilotLicenseCheck {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long id;
	
	/**
	 * 执照id
	 */
	@Column(name = "license_id")
	@ApiModelProperty(value="执照ID")
	public Long licenseId;
	

	/**
	 * 签注时间
	 */
	@Column(name = "check_date")
	@ApiModelProperty(value="签注时间")
	private java.sql.Date checkDate;
	
	/**
	 * 签注人
	 */
	@Column(name = "check_man")
	@ApiModelProperty(value="签注人 ")
	public java.lang.String checkMan;
	

}
