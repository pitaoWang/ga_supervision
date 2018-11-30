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


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name="prepare_release")
public class PrepareRelease {
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
	   private Integer deleteFlag;
	   
	   /**机构ID**/
	   @Column(name = "C_ORGANIZATION_ID")
	   private Long organizationId;
	   
	   /**运行基地ID**/
	   @Column(name = "C_ORGANIZATION_BASE_ID")
	   private Long organizationBaseId;
	   
	   /**运行基地名称**/
	   @Column(name = "C_ORGANIZATION_BASE_NAME")
	   private String organizationBaseName;
	   
	   /**飞行计划ID**/
	   @Column(name = "C_FLIGHT_PLAN_ID")
	   private Long flightPlanId;
	   
	   /**飞行计划名称**/
	   @Column(name = "C_FLIGHT_PLAN_NAME")
	   private String flightPlanName;
	   
	   /**航空器ID**/
	   @Column(name = "C_AIRCRAFT_ID")
	   private Long aircraftId;
	   
	   /**航空器名称**/
	   @Column(name = "C_AIRCRAFT_NAME")
	   private String aircraftName;
	   
	   /**航空器型别**/
	   @Column(name = "C_AIRCRAFT_TYPE")
	   private String aircraftType;
	   
	   /**航空器注册号**/
	   @Column(name = "C_AIRCRAFT_NO")
	   private String aircraftNo;
	   
	   /**航空器呼号**/
	   @Column(name = "C_AIRCRAFT_CALL")
	   private String aircraftCall;
	   
	   /**起飞机场ID**/
	   @Column(name = "C_AIRPORT_DEPARTURE_ID")
	   private Long airportDepartureId;
	   
	   /**起飞机场名称**/
	   @Column(name = "C_AIRPORT_DEPARTURE_NAME")
	   private String airportDepartureName;
	   
	   /**降落机场ID**/
	   @Column(name = "C_AIRPORT_DESTINATION_ID")
	   private Long airportDestinationId;
	   
	   /**降落机场名称**/
	   @Column(name = "C_AIRPORT_DESTINATION_NAME")
	   private String airportDestinationName;
	   
	   /**备降机场ID**/
	   @Column(name = "C_AIRPORT_ALTERNATE_ID")
	   private Long airportAlternateId;
	   
	   /**备降机场名称**/
	   @Column(name = "C_AIRPORT_ALTERNATE_NAME")
	   private String airportAlternateName;
	   
	   /**飞行规则**/
	   @Column(name = "C_FLIGHT_RULE")
	   private String flightRule;
	   
	   /**是否载重平衡：0-否，1-是**/
	   @Column(name = "C_IS_BALANCE")
	   private Integer isBalance;
	   
	   /**配载时间**/
	   @Column(name = "C_STOWAGE_TIME")
	   private Date stowageTime;
	   
	   /**空机重量**/
	   @Column(name = "C_EMPTY_WEIGHT")
	   private String emptyWeight;
	   
	   /**起飞油量**/
	   @Column(name = "C_TAKEOFF_FUEL")
	   private String takeoffFuel;
	   
	   /**最大起飞重量**/
	   @Column(name = "C_TAKEOFF_WEIGHT_MAX")
	   private String takeoffWeightMax;
	   
	   /**实际起飞重量**/
	   @Column(name = "C_TAKEOFF_WEIGHT_REAL")
	   private String takeoffWeightReal;
	   
	   /**飞行航线及高度**/
	   @Column(name = "C_AIRLINE_HEIGHT")
	   private String airlineHeight;
	   
	   /**机长ID**/
	   @Column(name = "C_CAPTAIN_ID")
	   private Long captainId;
	   
	   /**机长姓名**/
	   @Column(name = "C_CAPTAIN_NAME")
	   private String captainName;
	   
	   /**机长酒精限制是否合格：0-不合格，1-合格**/
	   @Column(name = "C_IS_PASS_CAPTAIN_WINE")
	   private Integer isPassCaptainWine;
	   
	   /**机长药物限制是否合格：0-不合格，1-合格**/
	   @Column(name = "C_IS_PASS_CAPTAIN_DRUG")
	   private Integer isPassCaptainDrug;
	   
	   /**机长身体状况是否合格：0-不合格，1-合格**/
	   @Column(name = "C_IS_PASS_CAPTAIN_HEALTH")
	   private Integer isPassCaptainHealth;
	   
	   /**机长思想是否稳定：0-不合格，1-合格**/
	   @Column(name = "C_IS_PASS_CAPTAIN_MIND")
	   private Integer isPassCaptainMind;
	   
	   /**副驾驶ID**/
	   @Column(name = "C_COPILOT_ID")
	   private Long copilotId;
	   
	   /**副驾驶姓名**/
	   @Column(name = "C_COPILOT_NAME")
	   private String copilotName;
	   
	   /**副驾驶酒精限制是否合格：0-不合格，1-合格**/
	   @Column(name = "C_IS_PASS_COPILOT_WINE")
	   private Integer isPassCopilotWine;
	   
	   /**副驾驶药物限制是否合格：0-不合格，1-合格**/
	   @Column(name = "C_IS_PASS_COPILOT_DRUG")
	   private Integer isPassCopilotDrug;
	   
	   /**副驾驶身体状况是否合格：0-不合格，1-合格**/
	   @Column(name = "C_IS_PASS_COPILOT_HEALTH")
	   private Integer isPassCopilotHealth;
	   
	   /**副驾驶思想是否稳定：0-不合格，1-合格**/
	   @Column(name = "C_IS_PASS_COPILOT_MIND")
	   private Integer isPassCopilotMind;
	   
	   /**起降场天气实况**/
	   @Column(name = "C_AIRPORT_WEATHER")
	   private String airportWeather;
	   
	   /**气象条件是否合格：0-不合格，1-合格**/
	   @Column(name = "C_IS_PASS_WEATHER")
	   private Integer isPassWeather;
	   
	   /**放行时间**/
	   @Column(name = "C_RELEASE_TIME")
	   private Date releaseTime;
}
