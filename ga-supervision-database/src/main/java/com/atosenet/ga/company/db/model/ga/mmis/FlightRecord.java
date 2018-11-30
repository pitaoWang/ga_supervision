package com.atosenet.ga.company.db.model.ga.mmis;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 飞行记录实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="mmis_mc_flight_record")
@Data
public class FlightRecord {
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

	@Column(name = "proc_id")
	@ApiModelProperty(value="流程id")
    private String procId;

	@Column(name = "attachment_ids")
	@ApiModelProperty(value="附件")
    private String attachmentIds;

	@Column(name = "company_name")
	@ApiModelProperty(value="公司名称")
    private String companyName;

    @Column(name = "company_id")
	@ApiModelProperty(value="公司id")
    private String companyId;

    @Column(name = "status")
	@ApiModelProperty(value="数据业务状态")
    private String status;

	@Column(name = "is_delete")
	@ApiModelProperty(value="是否删除,1:删除,0:未删除")
    private int isDelete;

	@Column(name = "no")
	@ApiModelProperty(value="飞行记录本编号")
    private String no;
	
	@Column(name = "engine1_id")
	@ApiModelProperty(value="发动机1ID")
    private String engine1Id;

	@Column(name = "engine1_serial_no")
	@ApiModelProperty(value="发动机1序列号")
    private String engine1SerialNo;

	@Column(name = "loop1")
	@ApiModelProperty(value="发动机1循环数（单发选择）")
    private String loop1;

	@Column(name = "loop2")
	@ApiModelProperty(value="发动机2循环数")
    private String loop2;

	@Column(name = "rise_fall")
	@ApiModelProperty(value="起落次数")
    private String riseFall;

	@Column(name = "captain")
	@ApiModelProperty(value="机长ID")
    private String captain;

	@Column(name = "releaser")
	@ApiModelProperty(value="放行人员ID")
    private String releaser;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Column(name = "release_time")
	@ApiModelProperty(value="放行时间")
    private Date releaseTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Column(name = "detrusion_time")
	@ApiModelProperty(value="推出时刻")
    private Date detrusionTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Column(name = "take_off_time")
	@ApiModelProperty(value="起飞时刻")
    private Date takeOffTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Column(name = "land_time")
	@ApiModelProperty(value="降落时刻")
    private Date landTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Column(name = "close_time")
	@ApiModelProperty(value="关车时刻")
    private Date closeTime;

    @Column(name = "fuel")
   	@ApiModelProperty(value="总燃油量")
    private String fuel;

    @Column(name = "add_fuel")
   	@ApiModelProperty(value="燃油添加量")
    private String addFuel;

    @Column(name = "surplus_fuel")
   	@ApiModelProperty(value="剩余燃油量")
    private String surplusFuel;

    @Column(name = "aircraft_model")
	@ApiModelProperty(value="航空器型号")
    private String aircraftModel;

    @Column(name = "aircraft_id")
	@ApiModelProperty(value="航空器id")
    private String aircraftId;

	@Column(name = "aircraft_reg_no")
	@ApiModelProperty(value="航空器注册号")
    private String aircraftRegNo;

	@Column(name = "aircraft_fly_time")
	@ApiModelProperty(value="航空器飞行时间")
    private String aircraftFlyTime;

	@Column(name = "work_record")
	@ApiModelProperty(value="工作记录")
    private String workRecord;

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

	@Column(name = "engine2_id")
	@ApiModelProperty(value="发动机2ID")
    private String engine2Id;

	@Column(name = "engine2_serial_no")
	@ApiModelProperty(value="发动机2序列号")
    private String engine2SerialNo;

}