package com.atosenet.ga.company.db.model.ga.company;

import java.util.Date;

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

@Entity
@Table(name = "aircraft_repair_license_special_check")
@Data

public class AircraftRepairLicenseSpecialCheck {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	/*
	 * 航空器执照id
	 */
	@Column(name = "license_id")
	private long licenseId;
	/*
	 * 航空器维修执照续签专业代码
	 */
	@Column(name = "special_code")
	@ApiModelProperty(value="航空器维修执照续签专业代码")
	private String specialCode;
	/*
	 * 航空器维修执照签署人
	 */
	@Column(name = "checkor_user_id")
	@ApiModelProperty(value="航空器维修执照签署人")
	private String checkorman;
	/*
	 * 航空器签署时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value="航空器签署时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "check_date")
	private Date checkDate;
	/*
	 * 航空器截止时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value="航空器截止时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "expire_date")
	private Date expireDate;
	/*
	 * 航空器创建时间
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value="航空器创建时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="create_time")
	private Date createTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="update_time")
	private Date updateTime;
	
	@Column(name="flag")
	private short flag;
}
