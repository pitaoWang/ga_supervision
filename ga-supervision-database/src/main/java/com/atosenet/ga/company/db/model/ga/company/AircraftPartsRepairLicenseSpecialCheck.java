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
/**
 * 专业续签记录
 * @author gfound
 *
 */
@Entity
@Table(name = "aircraft_parts_repair_license_special_check")
@Data
public class AircraftPartsRepairLicenseSpecialCheck {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	/* 航空器部件维修执照ID */
	@Column(name = "license_id")
	private long licenseId;
	/*航空器部件维修执照续签专业代码*/
	@Column(name = "special_code")
	@ApiModelProperty(value="航空器部件维修执照续签专业代码")
	private String specialCode;
	/*航空器部件维修签署项目签署人*/
	@Column(name = "checkor_user_id")
	@ApiModelProperty(value="航空器部件维修签署项目签署人")
	private String checkorman;
	/*航空器部件维修执照续签签发时间*/
	@Column(name = "check_date")
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value="航空器部件维修执照续签签发时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date checkDate;

	/*航空器部件维修执照续签有效截止日期*/
	@Column(name = "expire_date")
	@ApiModelProperty(value="航空器部件维修执照续签有效截止日期")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date expireDate;

	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createDate;

	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateDate;

	private short flag;

}
