/**  
 * 
 * @Title: prepareRelease.java 
 * @Package com.atosenet.ga.company.db.model.ga.company  
 * @author xyx   
 * @date 2018年9月27日 上午10:54:20 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.model.ga.company;


import java.sql.Time;
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

import lombok.Data;


/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: prepareRelease 
 * @author xyx 
 * @date 2018年9月27日 上午10:54:20 
 *  
 */
@Entity
@Data
@Table(name="flight_record")
public class FlightRecord {
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   @Column(name="ID")
	   private Long ID;
	   
	   /**创建人**/
	   @Column(name = "C_CREATE_BY")
	   private String createBy;
	   
	   /**创建时间**/
	   @Column(name = "C_CREATE_DATE")
	   private Date createDate;
	   
	   /**更新人**/
	   @Column(name = "C_UPDATE_BY")
	   private String updateBy;
	   
	   /**更新时间**/
	   @Column(name = "C_UPDATE_DATE")
	   private Date updateDate;
	   
	   /**删除标记：0-未删除，1-已删除**/
	   @Column(name = "C_DELETE_FLAG")
	   private int deleteFlag;
	   
	   /**机构ID**/
	   @Column(name = "C_ORGANIZATION_ID")
	   private Long organizationId;
	  
	   /**飞行计划ID**/
	   @Column(name = "C_FLIGHT_PLAN_ID")
	   private Long flightPlanId;
	   
	   /**飞行计划名称**/
	   @Column(name = "C_FLIGHT_PLAN_NAME")
	   private String flightPlanName;
	   
	   /**飞行日期**/
	    @Temporal(TemporalType.DATE)
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@JsonFormat(pattern = "yyyy-MM-dd")
	   @Column(name = "C_FLIGHT_DATE")
	   private Date flightDate;
	   
	   /**飞行起始时间**/
	   @Column(name = "C_FLIGHT_TIME_BEGIN")
	   private Time flightTimeBegin;
	   
	   /**飞行截止时间**/
	   @Column(name = "C_FLIGHT_TIME_END")
	   private Time flightTimeEnd;
	   
	   /**飞行性质：1-任务飞行，2-训练飞行，3-检查飞行，4-实践考试**/
	   @Column(name = "C_FLIGHT_NATURE")
	   private int flightNature;
	   
	   /**飞行人员ID**/
	   @Column(name = "C_FLIGHT_PERSON_ID")
	   private Long flightPerson_id;
	   
	   /**飞行人员姓名**/
	   @Column(name = "C_FLIGHT_PERSON_NAME")
	   private String flightPersonName;
	   
	   /**航空器ID**/
	   @Column(name = "C_AIRCRAFT_ID")
	   private Long aircraftId;
	   
	   /**航空器注册号**/
	   @Column(name = "C_AIRCRAFT_NO")
	   private String aircraftNo;
	   
	   /**航空器型别**/
	   @Column(name = "C_AIRCRAFT_MODEL")
	   private String aircraftModel;
	   
	   /**起飞点ID**/
	   @Column(name = "C_PORT_DEPARTURE_ID")
	   private Long portDepartureId;
	   
	   /**起飞点名称**/
	   @Column(name = "C_PORT_DEPARTURE_NAME")
	   private String portDepartureName;
	   
	   /**起飞点类型：1-机场，2-起降点**/
	   @Column(name = "C_PORT_DEPARTURE_TYPE")
	   private int portDepartureType;
	   
	   /**降落点ID**/
	   @Column(name = "C_PORT_DESTINATION_ID")
	   private Long portDestinationId;
	   
	   /**降落点名称**/
	   @Column(name = "C_PORT_DESTINATION_NAME")
	   private String portDestinationName;
	   
	   /**降落点类型：1-机场，2-起降点**/
	   @Column(name = "C_PORT_DESTINATION_TYPE")
	   private int portDestinationType;
	   
	   /**备降点ID**/
	   @Column(name = "C_PORT_ALTERNATE_ID")
	   private Long portAlternateId;
	   
	   /**备降点名称**/
	   @Column(name = "C_PORT_ALTERNATE_NAME")
	   private String portAlternateName;
	   
	   /**备降点类型：1-机场，2-起降点**/
	   @Column(name = "C_PORT_ALTERNATE_TYPE")
	   private int portAlternateType;
	   
	   /**白天降落次数**/
	   @Column(name = "C_DAY_LAND_COUNT")
	   private int dayLandCount;
	   
	   /**夜晚降落次数**/
	   @Column(name = "C_NIGHT_LAND_COUNT")
	   private int nightLandCount;
	   
	   /**白天飞行时间，单位：h**/
	   @Column(name = "C_DAY_FLIGHT_TIME")
	   private Double dayFlightTime;
	   
	   /**夜晚飞行时间，单位：h**/
	   @Column(name = "C_NIGHT_FLIGHT_TIME")
	   private Double nightFlightTime;
	   
	   /**机长飞行时间，单位：h**/
	   @Column(name = "C_PILOT_FLIGHT_TIME")
	   private Double pilotFlightTime;
	   
	   /**单飞飞行时间，单位：h**/
	   @Column(name = "C_SIGNLE_FLIGHT_TIME")
	   private Double signleFlightTime;
	   
	   /**机长或单飞转场时间，单位：h**/
	   @Column(name = "C_PILOT_TRANSFER_TIME")
	   private Double pilotTransferTime;
	   
	   /**副驾飞行时间，单位：h**/
	   @Column(name = "C_COPILOT_FLIGHT_TIME")
	   private Double copilotFlightTime;
	   
	   /**副驾转场时间，单位：h**/
	   @Column(name = "C_COPILOT_TRANSFER_TIME")
	   private Double copilotTransferTime;
	   
	   /**带飞飞行时间，单位：h**/
	   @Column(name = "C_INSTRUCTIONAL_FLIGHT_TIME")
	   private Double instructionalFlightTime;
	   
	   /**教员时间，单位：h**/
	   @Column(name = "C_TEACH_TIME")
	   private Double teachTime;
	   
	   /**带飞转场时间，单位：h**/
	   @Column(name = "C_INSTRUCTIONAL_TRANSFER_TIME")
	   private Double instructionalTransferTime;
	   
	   /**仪表类型ID（数据字典项ID）**/
	   @Column(name = "C_INSTRUMENT_TYPE_ID")
	   private Long instrumentTypeId;
	   
	   /**仪表类型名称**/
	   @Column(name = "C_INSTRUMENT_TYPE_NAME")
	   private String instrumentTypeName;
	   
	   /**其他仪表类型**/
	   @Column(name = "C_INSTRUMENT_TYPE_OTHER")
	   private String instrumentTypeOther;
	   
	   /**真实仪表时间**/
	   @Column(name = "C_INSTRUMENT_REAL_TIME")
	   private String instrumentRealTime;
	   
	   /**模拟仪表时间**/
	   @Column(name = "C_INSTRUMENT_SIMULATION_TIME")
	   private String instrumentSimulationTime;
	   
	   /**训练机构ID（数据字典项ID）**/
	   @Column(name = "C_TRAINING_INSTITUTION_ID")
	   private Long trainingInstitutionId;
	   
	   /**训练机构名称**/
	   @Column(name = "C_TRAINING_INSTITUTION_NAME")
	   private String trainingInstitutionName;
	   
	   /**模拟器编号**/
	   @Column(name = "C_SIMULATOR_NO")
	   private String simulatorNo;  
	   
	   /**训练器编号**/
	   @Column(name = "C_TRAINER_NO")
	   private String trainerNo;
	   
	   /**备注**/
	   @Column(name = "C_REMARK")
	   private String remark;
	   
	   /**抄送人Json数组**/
	   @Column(name = "C_COPY_FOR_PERSON_JSON")
	   private String copyForPersonJson;
	      
	   /**1-未提交，2-待确认，3-已确认，4-驳回**/
	   @Column(name = "C_STATUS")
	   private int status;
	   
	   /**机长确认人ID**/
	   @Column(name = "C_PILOT_CONFIRM_PERSON_ID")
	   private Long pilotConfirmPersonId;
	   
	   /**机长确认人名称**/
	   @Column(name = "C_PILOT_CONFIRM_PERSON_NAME")
	   private String pilotConfirmPersonName;
	   
	   /**机长确认时间**/
	   @Temporal(TemporalType.DATE)
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@JsonFormat(pattern = "yyyy-MM-dd")
	   @Column(name = "C_PILOT_CONFIRM_TIME")
	   private Date pilotConfirmTime;
}
