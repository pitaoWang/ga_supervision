package com.atosenet.ga.company.db.model.ga.company;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author dgw
 *
 */
@Entity
@Data
@Table(name = "mmis_base_engine")
public class MmisBaseEngine {
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column(name = "creator")
	@ApiModelProperty(value = "创建者")
	private String creator;
	
	@Column(name = "creator_name")
	@ApiModelProperty(value = "创建者姓名")
	private String creatorName;
	
	@Column(name = "create_time")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	
	@Column(name = "revisor")
	@ApiModelProperty(value = "修改者")
	private String revisor;
	
	@Column(name = "revisor_name")
	@ApiModelProperty(value = "修改者姓名")
	private String revisorName;
	
	@Column(name = "revise_time")
	@ApiModelProperty(value = "修改时间")
	private Date reviseTime;
	
	@Column(name = "company_id")
	@ApiModelProperty(value = "公司id")
	private String companyId;
	
	@Column(name = "status")
	@ApiModelProperty(value = "数据业务状态")
	private String status;
	
	@Column(name = "is_delete")
	@ApiModelProperty(value = "是否删除 1-删除 0-未删除")
	private int isDelete;
	
	@Column(name = "model")
	@ApiModelProperty(value = "发动机型号")
	private String model;
	
	@Column(name = "serial")
	@ApiModelProperty(value = "序号")
	private String serial;
	
	@Column(name = "equipment_status")
	@ApiModelProperty(value = "装机状态 1-未装机 2-已装机")
	private String equipmentStatus;
	
	@Column(name = "equipment_date")
	@ApiModelProperty(value = "装机日期")
	private Date equipmentDate;
	
	@Column(name = "aircraft_id")
	@ApiModelProperty(value = "所在航空器id")
	private String aircraftId;
	
	@Column(name = "aircraft_reg_no")
	@ApiModelProperty(value = "所在航空器注册号")
	private String aircraftRegNo;
	
	@Column(name = "aircraft_serial")
	@ApiModelProperty(value = "所在航空器序号")
	private String aircraftSerial;
	
	@Column(name = "life_fly_time")
	@ApiModelProperty(value = "飞行小时数寿命")
	private Double lifeFlyTime;
	
	@Column(name = "remain_fly_time")
	@ApiModelProperty(value = "剩余飞行小时数")
	private Double remainFlyFime;
	
	@Column(name = "life_cycle_time")
	@ApiModelProperty(value = "循环数寿命")
	private Double lifeCycleTime;
	
	@Column(name = "remain_cycle_time")
	@ApiModelProperty(value = "剩余循环数")
	private Double remainCycleTime;
	
	@Column(name = "manufacturer")
	@ApiModelProperty(value = "生产厂家")
	private String manufacturer;
}
