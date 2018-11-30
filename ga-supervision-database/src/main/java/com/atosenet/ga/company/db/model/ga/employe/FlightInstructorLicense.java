package com.atosenet.ga.company.db.model.ga.employe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***********************************************************************
 * Module: flight_instructor_license.java Author: Administrator Purpose: Defines
 * the Class flight_instructor_license
 ***********************************************************************/
@Entity
@Data
@Table(name = "flight_instructor_license")
public class FlightInstructorLicense {
	/** ID */
	@Id
	@TableGenerator(
            name="flightTeachIdGenerator",
            table="sys_id_gen",
            pkColumnName="gen_key",
            valueColumnName="gen_val",
            pkColumnValue="FL",
            initialValue=100000000,
            allocationSize=1)

	@GeneratedValue(strategy=GenerationType.TABLE, generator="flightTeachIdGenerator")
	@Column(name = "id")
	public int id;
	/** 飞行教员航空器类别等级（飞机、旋翼机、滑翔机、初级飞机） */
	@Column(name = "instructor_aircraft_type")
	@ApiModelProperty("飞行教员航空器类别等级（飞机、旋翼机、滑翔机、初级飞机）")
	public java.lang.String instructorAircraftType;
	/** 飞行教员飞机级别等级（单发、多发） */
	@Column(name = "instructor_aircraft_class")
	@ApiModelProperty("飞行教员飞机级别等级（单发、多发）")
	public java.lang.String instructorAircraftClass;
	/** 飞行教员旋翼机级别等级（直升机、自转旋翼机） */
	@Column(name = "instructor_rotorcraft_class")
	@ApiModelProperty("飞行教员旋翼机级别等级（直升机、自转旋翼机）")
	public java.lang.String instructorRotorcraftClass;
	/** 飞行教员仪表等级（仪表-飞机、仪表-直升机） */
	@Column(name = "instructor_meter_class")
	@ApiModelProperty("飞行教员仪表等级（仪表-飞机、仪表-直升机）")
	public java.lang.String instructorMeterClass;
	/** 是否多发主任教员 0-不是，1-是 */
	@Column(name = "is_multi_engine")
	@ApiModelProperty("是否多发主任教员 0-不是，1-是")
	public java.lang.Integer ismultiengine;
	/** 是否单发主任教员 0-不是，1-是 */
	@Column(name = "is_single_engine")
	@ApiModelProperty("是否单发主任教员 0-不是，1-是")
	public java.lang.Integer issingleengine;
	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="createtime")
	public java.util.Date createTime;
	/** 更新时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="updatetime")
	public java.util.Date updateTime;
	/** 0-正常，审核通过，1-删除，2-待审核，3-驳回 */
	@Column(name = "flag")
	public long flag;
	
	public static final String KEY_1 = "key_1";
	public static final String KEY_2 = "key_2";
	public static final String KEY_3 = "key_3";
	

}