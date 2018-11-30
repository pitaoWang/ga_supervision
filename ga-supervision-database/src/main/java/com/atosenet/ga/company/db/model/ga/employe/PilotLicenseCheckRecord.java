package com.atosenet.ga.company.db.model.ga.employe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***********************************************************************
 * Module: physical_record.java Author: Administrator Purpose: Defines the Class
 * physical_record
 ***********************************************************************/
/**
 * 
 * @author lvhao
 * 执照签注信息
 */
@Entity
@Data
@Table(name = "license_checkrecord")
public class PilotLicenseCheckRecord {
	/** 主键 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long id;
	
	/** 执照ID */
	@Column(name = "license_id")
	@ApiModelProperty(value="执照ID")
	public Long licenseId;
	

	/** 执照签注时间*/
	@Column(name = "license_check_date")
	@ApiModelProperty(value="执照签注时间")
	private java.sql.Date licenseCheckDate;
	
	/** 执照签注内容  */
	@Column(name = "license_check_content")
	@ApiModelProperty(value="执照签注内容，当执照签注为飞行执照签注时为机型 ")
	public java.lang.String licenseCheckContent;
	
	/**
	 * 签注人
	 */
	@Column(name = "check_man")
	@ApiModelProperty(value="签注人 ")
	public java.lang.String checkMan;
	

}