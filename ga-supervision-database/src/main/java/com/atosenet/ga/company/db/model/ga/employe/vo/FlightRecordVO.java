package com.atosenet.ga.company.db.model.ga.employe.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/***********************************************************************
 * 
 ***********************************************************************/
@Entity
@Data
public class FlightRecordVO {
	/** 飞行计划ID */
	@Id
	@Column(name = "fly_plan_id")
	public int FlyPlanId;
	
	/** 该计划的发布的企业 */
	@Column(name = "company_id")
	public int companyId;
	/** 机长ID = 飞行员ID*/
	@Column(name = "captain_id")
	public int captainId;
	
	/** 飞行日期 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fly_date")
	public java.util.Date flyDate;
	/** 航空器型号 */
//	@Column(name = "model_number")
//	public java.lang.String modelNumber;
	/** 起飞站 */
	@Column(name = "origin")
	public java.lang.String origin;
	/** 降落站 */
	@Column(name = "destination")
	public java.lang.String destination;
	/** 起飞时刻 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "take_off_time")
	public java.util.Date takeOffTime;
	/** 降落时刻 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "fall_time")
	public java.util.Date fallTime;
	/** 飞行种类 */
	@Column(name = "fly_type")
	public int flyType;
	/** 仪表类型（ILS、NDB、VOR、其他） */
	@Column(name = "instrument_type")
	public int instrumentType;

}