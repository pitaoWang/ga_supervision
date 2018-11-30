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
 * 
 * @author 熊江涛 执照项目签署记录
 */
@Entity
@Table(name = "aircraft_parts_repair_license_project_check")
@Data
public class AircraftPartsRepairLicenseProjectCheck {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	/* 航空器部件维修执照ID */
	@Column(name = "license_id")
	
	private long licenseId;
	/* 航空器部件维修签署项目名称 */
	@Column(name = "project_name")
	@ApiModelProperty(value="航空器部件维修签署项目名称")
	private String projectName;

	/* 航空器部件维修签署项目签署人 */
	@Column(name = "checkor_user_id")
	@ApiModelProperty(value="航空器部件维修签署项目签署人")
	private String checkorman;

	/* 航空器部件维修签署项目日期 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value="航空器部件维修签署项目日期")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="check_date")
	private Date checkDate;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="create_time")
	private Date createTime;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "update_time")
	private Date updateTime;

	private short flag;
}
