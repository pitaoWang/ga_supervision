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

import lombok.Data;

/***********************************************************************
 * Module: flight_plan.java Author: Administrator Purpose: Defines the Class
 * flight_plan
 ***********************************************************************/
@Entity
@Data
@Table(name = "flight_plan")
public class FlightPlan {
	/** 飞行计划ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long id;
	/** 该计划的发布的企业 */
	@Column(name = "company_id")
	public int companyId;
	/** 机长ID */
	@Column(name = "captain_id")
	public int captainId;
	/** 副机长ID */
	@Column(name = "co_pilot_id")
	public int coPilotId;
	/** 飞行计划编号 */
	@Column(name = "flightplan_number")
	public java.lang.String flightPlanNumber;
	/** 飞行任务性质 */
	@Column(name = "mission_character")
	public java.lang.String missionCharacter;
	/** 乘机人数 */
	@Column(name = "boarding_number")
	public int boardingNumber;
	/** 通信方式 */
	@Column(name = "communication_method")
	public java.lang.String communicationMethod;
	/** 预计飞行开始时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "flightpan_begintime")
	public java.util.Date flightpanBegintime;
	/** 预计飞行结束时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "flightpan_endtime")
	public java.util.Date flightpanEndtime;
	/** 飞行规则 */
	@Column(name = "flight_rule")
	public java.lang.String flightRule;
	/** 特殊任务附件 */
	@Column(name = "mission_attachment")
	public java.lang.String missionAttachment;
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

}