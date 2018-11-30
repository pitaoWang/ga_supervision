package com.atosenet.ga.company.db.model.ga.employe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/***********************************************************************
 * 
 * Module: pilot_experience.java Author: Administrator Purpose: Defines the
 * Class pilot_experience
 ***********************************************************************/
@Entity
@Data
@Table(name = "pilot_experience")
@JsonIgnoreProperties(value = { "employee"})
public class PilotExperience {
	/** 飞行员航空经历ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long id;
	/** 员工ID */
	@Column(name = "employee_id")
	public long employeeId;
	/** 航空经历内容 */
	@Column(name = "flight_experience")
	public java.lang.String flightExperience;
	/** 航空经历开始时间 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "begin_date")
	public java.util.Date beginDate;
	/** 航空经历结束时间 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "end_date")
	public java.util.Date endDate;
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
	@OneToOne(optional=false)
	@JoinColumn(name="employee_id",insertable=false,updatable=false)
	@JsonBackReference
	public Employee employee;
}