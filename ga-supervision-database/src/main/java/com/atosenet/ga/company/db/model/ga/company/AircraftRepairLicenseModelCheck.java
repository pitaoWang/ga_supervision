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
@Table(name = "aircraft_repair_license_model_check")
@Data
/**
 * 	航空器维修执照机型签发
 * @author gfound
 *
 */
public class AircraftRepairLicenseModelCheck {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	/*
	 * 航空器维修执照Id
	 */
	@Column(name = "license_id")
	private long licenseId;
	/*
	 * 航空器类别
	 */
	@Column(name = "aircraft_class")
	@ApiModelProperty(value="航空器类别")
	private String aircraftClass;
	/*
	 * 航空器型号
	 */
	@Column(name = "aircraft_model")
	@ApiModelProperty(value="航空器型号")
	private String aircraftModel;

	/*
	 * 航空器签署人的Id
	 *
	 */
	@Column(name = "checkor_user_id")
	@ApiModelProperty(value="航空器签署人")
	private String checkorman;

	/*
	 * 航空器维修签署机型日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value="航空器维修签署机型日期")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "check_date")
	private Date checkDate;
	/*
	 *
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "create_time")
	private Date createTime;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "update_time")
	private Date updateTime;

}
