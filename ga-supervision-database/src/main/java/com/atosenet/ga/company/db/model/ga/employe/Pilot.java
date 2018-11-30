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

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***********************************************************************
 * 
 * Module: pilot.java Author: Administrator Purpose: Defines the Class pilot
 ***********************************************************************/
@Entity
@Data
@Table(name="pilot")
public class Pilot {
	/** 主键ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pilot_id")
	public long pilotId;
	
	/** 员工ID */
	@Column(name = "employee_id")
	public Long employeeId;
	/** 所属大队 */
	@ApiModelProperty(value="所属大队")
	@Column(name = "flight_team")
	public java.lang.String flightTeam;
	/** 技术级别 */
	@ApiModelProperty(value="技术级别")
	@Column(name = "tech_class")
	public java.lang.String techClass;
	/** 英语水平 */
	/*@Column(name = "english_level")
	public java.lang.String englishLevel;*/
	/** 汉语水平 */
	/*@Column(name = "chinese_level")
	public java.lang.String chineselevel;*/
	/** 航空英语考试通过时间 */
	/*@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "englis_pass_date")
	public java.util.Date englisPassDate;*/
	/** 所飞机型 */
	/*@Column(name = "aircraft_type")
	public java.lang.String aircraftType;*/
	/** 资料是否完善 */
	/*@Column(name = "iscomplete")
	public Integer isComplete;*/
	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="createtime")
	public java.util.Date createTime;
	/** 更新时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="updatetime")
	public java.util.Date updateTime;
	/** 0-正常，审核通过，1-删除，2-待审核，3-驳回 */
	@Column(name = "flag")
	public Integer flag;
	/** 飞行员表增加飞行总时长 (s) **/
	@ApiModelProperty(value="飞行员表增加飞行总时长{s}")
	@Column(name = "fly_sum_time")
	public Long flySumTime;
	/** 飞行员违规次数**/
	@ApiModelProperty(value="飞行员违规次数")
	@Column(name = "fly_sum_illegal")
	public Integer flySumIllegal;
	/** 是否委任代表 */
	/*@Column(name = "delegate")
	public java.lang.String delegate;*/
	/** 无线电通信资格证 */
	@ApiModelProperty(value="无线电通信资格证")
	@Column(name = "wireless_qualification_certificate")
	public java.lang.String wqc;
	/** 英语通信等级证 */
	@ApiModelProperty(value="英语通信等级证")
	@Column(name = "english_class_certificate")
	public java.lang.String esCertificate;
	/** 汉语通信等级证 */
	@ApiModelProperty(value="汉语通信等级证")
	@Column(name = "chinese_class_certificate")
	public java.lang.String chCertificate;
	
	/** 无线电通信资格证编号 */
	@ApiModelProperty(value="无线电通信资格证编号")
	@Column(name = "wireless_qualification_num")
	public java.lang.String wqn;
	/** 英语通信等级证编号 */
	@ApiModelProperty(value="英语通信等级证编号")
	@Column(name = "english_class_num")
	public java.lang.String esNum;
	/** 汉语通信等级证编号 */
	@ApiModelProperty(value="汉语通信等级证编号")
	@Column(name = "chinese_class_num")
	public java.lang.String chNum;
	
	
	/** 飞行经历扫描件 */
	@ApiModelProperty(value="飞行经历扫描件")
	@Column(name = "fly_experience_scanning")
	public java.lang.String scanning;
	/** 教员任命书扫描件 */
	@ApiModelProperty(value="教员任命书扫描件")
	@Column(name = "scanned_teacher")
	public java.lang.String scannedTeacher;
	/** 教师资格证 */
	@ApiModelProperty(value="教师资格证")
	@Column(name = "teacher_qualification")
	public java.lang.String teacherQualification;
	/** 教员证书有效期 */
	@ApiModelProperty(value="教员证书有效期")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "validity_time")
	public java.util.Date validityTime;
	/** 教学类型 */
	@Column(name = "teach_categories")
	@ApiModelProperty(value="教学类型")
	public java.lang.String teachCategories;
	
	
}