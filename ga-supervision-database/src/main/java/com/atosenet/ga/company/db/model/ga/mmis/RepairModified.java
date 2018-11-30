package com.atosenet.ga.company.db.model.ga.mmis;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 维修改装实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="mmis_mc_repair_modified")
@Data
public class RepairModified {
	
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

	@Column(name = "is_delete")
	@ApiModelProperty(value="删除标志位 1删除  0未删")
    private int isDelete;
	
	@Column(name = "status")
	@ApiModelProperty(value="记录处理状态")
    private String status;

	@Column(name = "company_id")
	@ApiModelProperty(value="组织ID")
    private String companyId;

	@Column(name = "no")
	@ApiModelProperty(value="重要修理及改装记录编号")
    private String no;

    @Column(name = "company_name")
	@ApiModelProperty(value="公司名字")
    private String companyName;

    @Column(name = "aircraft_manufacturer")
	@ApiModelProperty(value="公司名字")
    private String aircraftManufacturer;

    @Column(name = "aircraft_model")
	@ApiModelProperty(value="航空器型号")
    private String aircraftModel;

    @Column(name = "aircraft_id")
	@ApiModelProperty(value="航空器id")
    private String aircraftId;

	@Column(name = "aircraft_reg_no")
	@ApiModelProperty(value="航空器信息注册号")
    private String aircraftRegNo;

	@Column(name = "aircraft_serial_no")
	@ApiModelProperty(value="航空器信息序列号")
    private String aircraftSerialNo;

	@Column(name = "possessor")
	@ApiModelProperty(value="所有人名称")
    private String possessor;

	@Column(name = "address")
	@ApiModelProperty(value="地址")
    private String address;

	@Column(name = "type")
	@ApiModelProperty(value="类型")
    private String type;

	@Column(name = "project")
	@ApiModelProperty(value="项目")
    private String project;

	@Column(name = "releaser")
	@ApiModelProperty(value="放行人")
    private String releaser;

	@Column(name = "release_time")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@ApiModelProperty(value="放行时间")
    private String releaseTime;

	@Column(name = "revisor")
	@ApiModelProperty(value="更新人")
    private String revisor;

	@Column(name = "revisor_name")
	@ApiModelProperty(value="更新人姓名")
    private String revisorName;

	@Column(name = "revise_time")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date reviseTime;

	@Column(name = "mc_summary")
	@ApiModelProperty(value="维修工作概述")
    private String mcSummary;
    
}