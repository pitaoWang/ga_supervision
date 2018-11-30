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
 * 工作保留单实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="mmis_mc_work_reserve")
@Data
public class WorkReserve {
	
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

	@Column(name = "company_id")
	@ApiModelProperty(value="组织id")
    private String companyId;

	@Column(name = "no")
	@ApiModelProperty(value="工作保留单编号")
    private String no;

	@Column(name = "delay_reason")
	@ApiModelProperty(value="推迟原因")
    private String delayReason;

	@Column(name = "delay_basis")
	@ApiModelProperty(value="推迟依据")
    private String delayBasis;

	@Column(name = "delay_date")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@ApiModelProperty(value="推迟期限")
    private Date delayDate;

	@Column(name = "applicant")
	@ApiModelProperty(value="申请人")
    private String applicant;

	@Column(name = "apply_time")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@ApiModelProperty(value="申请时间")
    private Date applyTime;

	@Column(name = "completor")
	@ApiModelProperty(value="完成人")
    private String completor;

	@Column(name = "complete_time")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@ApiModelProperty(value="完成时间")
    private Date completeTime;

	@Column(name = "status")
	@ApiModelProperty(value="保留处理状态")
    private String status;

	@Column(name = "company_name")
	@ApiModelProperty(value="公司名字")
    private String companyName;
	
	@Column(name = "woc_no")
	@ApiModelProperty(value="工作单卡编号")
    private String wocNo;

	@Column(name = "woc_title")
	@ApiModelProperty(value="工作单卡标题")
    private String wocTitle;

	@Column(name = "woc_id")
	@ApiModelProperty(value="工卡ID")
    private String wocId;

	@Column(name = "approver")
	@ApiModelProperty(value="审批人")
    private String approver;

	@Column(name = "approve_time")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@ApiModelProperty(value="审批时间")
    private Date approveTime;

	@Column(name = "is_delete")
	@ApiModelProperty(value="删除标志位 1删除  0未删")
    private int isDelete;

	@Column(name = "aircraft_model")
	@ApiModelProperty(value="航空器型号")
    private String aircraftModel;

	@Column(name = "aircraft_reg_no")
	@ApiModelProperty(value="航空器信息注册号")
    private String aircraftRegNo;

	@Column(name = "color")
	@ApiModelProperty(value="高亮显示")
    private String color;

	@Column(name = "revisor")
	@ApiModelProperty(value="更新人")
    private String revisor;

	@Column(name = "revisor_name")
	@ApiModelProperty(value="更新人姓名")
    private String revisorName;

	@Column(name = "revise_time")
	@ApiModelProperty(value="更新时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date reviseTime;
	
}