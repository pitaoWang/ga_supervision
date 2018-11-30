package com.atosenet.ga.company.db.model.ga.mmis;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 发动机实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="mmis_base_engine")
@Data
public class BaseEngine {
	
	/**
	 * 主键
	 */
	@Id
	@Column(name = "id")
    private String id;
	
	/** 创建人 */
	@Column(name = "creator")
	@ApiModelProperty(value="创建人")
    private String creator;

	@Column(name = "creator_name")
	@ApiModelProperty(value="创建者姓名")
    private String creatorName;
	
	@Column(name = "create_time")
	@ApiModelProperty(value="创建时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;

	@Column(name = "revisor")
	@ApiModelProperty(value="修改人")
    private String revisor;

	@Column(name = "revisor_name")
	@ApiModelProperty(value="修改人姓名")
    private String revisorName;

	@Column(name = "revise_time")
	@ApiModelProperty(value="修改时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date reviseTime;

	@Column(name = "company_id")
	@ApiModelProperty(value="公司id")
    private String companyId;

	@Column(name = "status")
	@ApiModelProperty(value="数据业务状态")
    private String status;

	@Column(name = "is_delete")
	@ApiModelProperty(value="是否删除,1:删除,0:未删除")
    private int isDelete;

	@Column(name = "model")
	@ApiModelProperty(value="发动机型号")
    private String model;

	@Column(name = "serial")
	@ApiModelProperty(value="序号")
    private String serial;

	@Column(name = "equipment_status")
	@ApiModelProperty(value="装机状态  1未装机 2已装机")
    private String equipmentStatus;

	@Column(name = "equipment_date")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@ApiModelProperty(value="装机日期")
    private Date equipmentDate;

	@Column(name = "aircraft_id")
	@ApiModelProperty(value="所在航空器id")
    private String aircraftId;

	@Column(name = "aircraft_reg_no")
	@ApiModelProperty(value="所在航空器注册号")
    private String aircraftRegNo;

	@Column(name = "aircraft_serial")
	@ApiModelProperty(value="所在航空器序号")
    private String aircraftSerial;

	@Column(name = "life_fly_time")
	@ApiModelProperty(value="飞行小时数寿命")
    private BigDecimal lifeFlyTime;

	@Column(name = "remain_fly_time")
	@ApiModelProperty(value="剩余飞行小时数")
    private BigDecimal remainFlyTime;

	@Column(name = "life_cycle_time")
	@ApiModelProperty(value="循环数寿命")
    private BigDecimal lifeCycleTime;

	@Column(name = "remain_cycle_time")
	@ApiModelProperty(value="剩余循环数")
    private BigDecimal remainCycleTime;

	@Column(name = "manufacturer")
	@ApiModelProperty(value="生产厂家")
    private String manufacturer;
   
}