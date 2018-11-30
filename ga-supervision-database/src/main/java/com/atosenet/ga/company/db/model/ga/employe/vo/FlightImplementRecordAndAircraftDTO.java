package com.atosenet.ga.company.db.model.ga.employe.vo;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Data;


@Entity
@Data

public class FlightImplementRecordAndAircraftDTO {
	
	/** 飞行记录ID */
	@Id
	@Column(name = "id")
	public long id;
	/** 所属企业ID */
	@Column(name = "company_id")
	public int companyId;
	/** 飞行计划编号 */
	@Column(name = "fly_plan_id")
	public java.lang.String flyPlanId;
	/** 飞行任务编号 */
	@Column(name = "fly_task_id")
	public java.lang.String flyTaskId;
	/** 航空器ID */
	@Column(name = "aircraft_id")
	public long aircraftId;
	/** 飞行日期 */
//	@Temporal(TemporalType.TIMESTAMP)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fly_date")
	private java.sql.Date flyDate;
	/** 机长ID */
	@Column(name = "captain_id")
	public long captainId;
	/** 副机长ID */
	@Column(name = "copilot_id")
	public long copilotId;
//	/** 航空器型号 */
//	@Column(name = "model_number")
//	public java.lang.String modelNumber;
//	/** 航空器登记号 */
//	@Column(name = "reg_number")
//	public java.lang.String regNumber;
	/** 起飞站 */
	@Column(name = "origin")
	public java.lang.String origin;
	/** 降落站 */
	@Column(name = "destination")
	public java.lang.String destination;
	/** 起飞时刻 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
	@Column(name = "take_off_time")
	public java.util.Date takeOffTime;
	/** 降落时刻 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
	@Column(name = "land_time")
	public java.util.Date landTime;
	/** 飞行种类 */
	@Column(name = "fly_type")
	public int flyType;
	/** 白天降落次数 */
	@Column(name = "diurnal_count")
	public int diurnalCount;
	/** 夜晚降落次数 */
	@Column(name = "night_count")
	public int nightCount;
	/** 白天飞行时间 */
	@Column(name = "diumal_fly_time")
	public java.lang.String diumalFlyTime;
	/** 夜晚飞行时间 */
	@Column(name = "night_fly_time")
	public java.lang.String nightFlyTime;
	/** 机长飞行时间 */
	@Column(name = "captain_fly_time")
	public java.lang.String captainFlyTime;
	/** 机长单飞时间 */
	@Column(name = "captain_alone_fly_time")
	public java.lang.String captainAloneFlyTime;
	/** 机长转场时间 */
	@Column(name = "captain_transit_time")
	public java.lang.String captainTransitTime;
	/** 带飞时间 */
	@Column(name = "lead_fly_time")
	public java.lang.String leadFlyTime;
	/** 带飞转场时间 */
	@Column(name = "lead_transit_time")
	public java.lang.String leadTransitTime;
	/** 副驾驶飞行时间 */
	@Column(name = "copilot_fly_time")
	public java.lang.String copilotFlyTime;
	/** 副驾驶转场飞行时间 */
	@Column(name = "copilot_transit_time")
	public java.lang.String copilotTransitTime;
	/** 仪表类型（ILS、NDB、VOR、其他） */
	@Column(name = "instrument_type")
	public java.lang.String instrumentType;
	/** 真实仪表时间 */
	@Column(name = "actual_instrumen_time")
	public java.lang.String actualInstrumenTime;
	/** 模拟仪表时间 */
	@Column(name = "simulate_instrumen_time")
	public java.lang.String simulateInstrumenTime;
	/** 模拟/训练器器训练机构 */
	@Column(name = "simulate_org")
	public java.lang.String simulateOrg;
	/** 模拟器时间 */
	@Column(name = "simulator_time")
	public java.lang.String simulatorTime;
	/** 训练器时间 */
	@Column(name = "training_time")
	public java.lang.String trainingTime;
	/** 教员时间 */
	@Column(name = "instructor_time")
	public java.lang.String instructorTime;
	/** 教员签字 */
	@Column(name = "instructor_sign")
	public java.lang.String instructorSign;
	/** 任务执行情况 */
	@Column(name = "task_info")
	public java.lang.String taskInfo;
	/** 备注 */
	@Column(name = "remark")
	public java.lang.String remark;
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
	
	/** 飞行任务 */
	@Column(name = "fly_task")
	public java.lang.String flyTask;
	/** 航空器国籍等级证号 */
	@Column(name = "aircraft_cer_number")
	public java.lang.String aircraftCerNumber;
	
	/** 飞行性质 */
	@Column(name = "flight_properties")
	public java.lang.String flightProperties;
	
	/** 随机机长 */
	@Column(name = "random_captain")
	public java.lang.String randomCaptain;
	
	/** 随机学员 */
	@Column(name = "random_copilot")
	public java.lang.String randomCopilot;
	
	/** 本架次飞行时间 */
	@Column(name = "sortie_fly_time")
	public java.lang.String sortieFlyTime;
	
	/** 本架次昼间降落架次 */
	@Column(name = "sortie_diurnal_count")
	public int sortieDiurnalCount;
	
	/** 本架次夜间降落架次 */
	@Column(name = "sortie_night_count")
	public int sortieNightCount;
	
	/** 本架次昼间飞行时间 */
	@Column(name = "sortie_diurnal_flytime")
	public java.lang.String sortieDiurnalFlytime;
	
	/** 本架次夜间飞行时间 */
	@Column(name = "sortie_night_flytime")
	public java.lang.String sortieNightFlytime;
	
	/** 机长夜航时间 */
	@Column(name = "captain_night_fly_time")
	public java.lang.String captainNightFlyTime;
	
	/** 机长特技飞行时间 */
	@Column(name = "captain_stunt_fly_time")
	public java.lang.String captainStuntFlyTime;
	
	/** 监视时间（机长） */
	@Column(name = "captain_watch_time")
	public java.lang.String captainWatchTime;
	
	/** 被监视时间（机长） */
	@Column(name = "captain_watched_time")
	public java.lang.String captainWatchedTime;
	
	/** PF时间 */
	@Column(name = "captain_pf_time")
	public java.lang.String captainPfTime;
	
	/** PNF时间（机长） */
	@Column(name = "captain_pnf_time")
	public java.lang.String captainPnfTime;
	
	/** 昼间降落次数(副驾驶) */
	@Column(name = "copilot_diurnal_count")
	public int copilotDiurnalCount;
	
	/** 夜间降落次数（副驾驶） */
	@Column(name = "copilot_night_count")
	public int copilotNightCount;
	
	/** 昼间飞行时间(副驾驶) */
	@Column(name = "copilot_diurnal_fly_time")
	public java.lang.String copilotDiurnalFlyTime;
	
	/** 夜间飞行时间（副驾驶） */
	@Column(name = "copilot_night_fly_time")
	public java.lang.String copilotNightFlyTime;
	
	/** 被带飞转场时间 */
	@Column(name = "leaded_transit_time")
	public java.lang.String leadedTransitTime;
	
	/** 副驾驶特技时间 */
	@Column(name = "copilot_stunt_fly_time")
	public java.lang.String copilotStuntFlyTime;
	
	/** 副驾驶仪表类型 */
	@Column(name = "copilot_instrument_type")
	public java.lang.String copilotInstrumentType;
	
	/** 副驾驶真实仪表时间 */
	@Column(name = "copilot_actual_instrumen_time")
	public java.lang.String copilotActualInstrumenTime;
	
	/** 副驾驶模拟仪表时间 */
	@Column(name = "copilot_simulate_instrumen_time")
	public java.lang.String copilotSimulateInstrumenTime;
	
	/** 副驾驶监视时间 */
	@Column(name = "copilot_watch_time")
	public java.lang.String copilotWatchTime;
	
	/** 副驾驶被监视时间 */
	@Column(name = "copilot_watched_time")
	public java.lang.String copilotWatchedTime;
	
	/** PF时间 副驾驶 */
	@Column(name = "copilot_pf_time")
	public java.lang.String copilotPfTime;
	
	/** 副驾驶PNF时间 */
	@Column(name = "copilot_pnf_time")
	public java.lang.String copilotPnfTime;

	/** 机长/教员状态（在职员工/兼职员工/离职员工） */
	@Column(name = "captain_state")
	public java.lang.String captainState;
	
	/** 副驾驶/学员状态（在职员工/兼职员工/离职员工） */
	@Column(name = "copilot_state")
	public java.lang.String copilotState;
	
	/** 航空器来源（自有航空器，其他公司航空器） */
	@Column(name = "aircraft_from")
	public java.lang.String aircraftFrom;
	
	/** 航空器所属公司 */
	@Column(name = "aircraft_company_name")
	public java.lang.String aircraftCompanyName;
	
	/** 机长/教员所属公司 */
	@Column(name = "captain_company_id")
	public long captainCompanyId;
	
	/** 副驾驶/学员所属公司 */
	@Column(name = "copilot_company_id")
	public long copilotCompanyId;
	
	
	
	@Column(name = "aircraft_model")
	public String aircraftModel;
	
	
	
}
