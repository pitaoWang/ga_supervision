package com.atosenet.ga.company.db.model.ga.employe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***********************************************************************
 * Author: Administrator Purpose: Defines the Class pilot_license
 ***********************************************************************/
@Entity
@Data
@Table(name = "pilot_license")
public class PilotLicense {
	/** 驾驶员执照ID */
	/** ID */
	@Id
	@Column(name = "id")
	public long id;
	/** 执照档案号 */
	@Column(name = "archives_no")
	@ApiModelProperty("执照档案号")
	public java.lang.String archivesno;
	/**执照性质：临时执照，正式执照*/
	@Column(name = "property")
	@ApiModelProperty("执照性质：临时执照，正式执照")
	public java.lang.String property;
	/** 颁发单位 */
	@Column(name = "issue_dept")
	@ApiModelProperty("颁发单位")
	public java.lang.String issuedept;
	/** 驾驶员执照类型（学生驾驶员、运动驾驶员、私用驾驶员、商用驾驶员、多人制机组驾驶员、航线运输驾驶员） */
	@Column(name = "license_class")
	@ApiModelProperty("驾驶员执照类型（学生驾驶员、运动驾驶员、私用驾驶员、商用驾驶员、多人制机组驾驶员、航线运输驾驶员）")
	public java.lang.String licenseclass;
	/** 航空器型号 */
	@Column(name = "aircraft_type")
	@ApiModelProperty("航空器型号")
	public java.lang.String aircraftType;
	/** 航空器级别等级（单发陆地、多发陆地、单发水上、多发水上，多选） */
	@Column(name = "aircraft_grade")
	@ApiModelProperty("航空器级别等级（单发陆地、多发陆地、单发水上、多发水上，多选）")
	public java.lang.String aircraftgrade;
	/** 航空器类别等级（飞机、直升机、飞艇、倾转旋翼机，多选）） */
	@Column(name = "aircraft_class")
	@ApiModelProperty("航空器类别等级（飞机、直升机、飞艇、倾转旋翼机，多选））")
	public java.lang.String aircraftClass;
	/** 仪表等级（无、仪表-飞机、仪表-直升机、仪表-飞艇、仪表-倾转旋翼机）） */
	@Column(name = "meter_grade")
	@ApiModelProperty("仪表等级（无、仪表-飞机、仪表-直升机、仪表-飞艇、仪表-倾转旋翼机））")
	public java.lang.String metergrade;
	/** 是否是飞行教员（0-否, 1-是）） */
	@Column(name = "is_pilot_teacher")
	@ApiModelProperty("是否是飞行教员（0-否, 1-是））")
	public int ispilotteacher;
	/** 飞行教员基础教员等级（单发飞机、多发飞机、直升机、飞艇、倾转旋翼机） */
	@Column(name = "teacher_grade")
	@ApiModelProperty("飞行教员基础教员等级（单发飞机、多发飞机、直升机、飞艇、倾转旋翼机）")
	public java.lang.String teachergrade;
	/**飞行教员仪表等级（无、仪表-飞机、仪表-直升机、仪表-飞艇、仪表-倾转旋翼机） */
	@Column(name = "teacher_meter_grade")
	@ApiModelProperty("飞行教员仪表等级（无、仪表-飞机、仪表-直升机、仪表-飞艇、仪表-倾转旋翼机）")
	public java.lang.String teachermetergrade;
	/** 飞行教员航空器型别（审定为最大起飞全重在5700千克以上的航空器、审定为最大起飞全重在3180千克以上的航空器、涡轮喷气动力飞机、其他航空器，可多选） */
	@Column(name = "teacher_aircraft_type")
	@ApiModelProperty("飞行教员航空器型别（审定为最大起飞全重在5700千克以上的航空器、审定为最大起飞全重在3180千克以上的航空器、涡轮喷气动力飞机、其他航空器，可多选）")
	public java.lang.String teacheraircrafttype;
	/** 多人制机组驾驶员航空器类别等级（飞机） */
	@Column(name = "multi_aircraft_class")
	@ApiModelProperty("多人制机组驾驶员航空器类别等级（飞机）")
	public java.lang.String multiaircraftclass;
	/** 多人制机组驾驶员航空器级别等级（多发陆地） */
	@Column(name = "multi_aircraft_grade")
	@ApiModelProperty("多人制机组驾驶员航空器级别等级（多发陆地）")
	public java.lang.String multiaircraftgrade;
	/**多人制机组驾驶员航空器型别等级（审定为最大起飞全重在5700千克以上的航空器、审定为最大起飞全重在3180千克以上的航空器、涡轮喷气动力飞机、其他航空器，可多选） */
	@Column(name = "multi_aircraft_type")
	@ApiModelProperty("多人制机组驾驶员航空器型别等级（审定为最大起飞全重在5700千克以上的航空器、审定为最大起飞全重在3180千克以上的航空器、涡轮喷气动力飞机、其他航空器，可多选）")
	public java.lang.String multiaircrafttype;
	/** 是否是运动驾驶员教员（1-是，0-否） */
	@Column(name = "is_sports_pilot_teacher")
	@ApiModelProperty("是否是运动驾驶员教员（1-是，0-否）")
	public int issportspilotteacher;
	/**运动驾驶员航空器类别等级（初级飞机、自转旋翼机、滑翔机、自由气球、小型飞艇） */
	@Column(name = "sports_aircraft_class")
	@ApiModelProperty("运动驾驶员航空器类别等级（初级飞机、自转旋翼机、滑翔机、自由气球、小型飞艇）")
	public java.lang.String sportsaircraftclass;
	/**运动驾驶员航空器级别等级（初级飞机-陆地、初级飞机-水上） */
	@Column(name = "sports_aircraft_grade")
	@ApiModelProperty("运动驾驶员航空器级别等级（初级飞机-陆地、初级飞机-水上）")
	public java.lang.String sportsaircraftgrade;
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
	
	/**
	 * 签注等级
	 */
	@Column(name="sign_grade")
	@ApiModelProperty("签注等级")
	public String signGrade;

}