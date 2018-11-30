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
 * 故障保留单实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="mmis_mc_fault_reserve")
@Data
public class FaultReserve {
	
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

	@Column(name = "fault_describe")
	@ApiModelProperty(value="保留故障描述")
    private String faultDescribe;

	@Column(name = "ata_section")
	@ApiModelProperty(value="ATA章节号")
    private String ataSection;

	@Column(name = "measures_taken")
	@ApiModelProperty(value="已采取措施")
    private String measuresTaken;

	@Column(name = "no")
	@ApiModelProperty(value="故障保留单编号")
    private String no;

	@Column(name = "reserve_company")
	@ApiModelProperty(value="保留单位")
    private String reserveCompany;

	@Column(name = "fault_reserve_cause")
	@ApiModelProperty(value="保留故障原因")
    private String faultReserveCause;

	@Column(name = "reserve_basis")
	@ApiModelProperty(value="保留依据")
    private String reserveBasis;

	@Column(name = "reserve_date")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@ApiModelProperty(value="保留期限")
    private Date reserveDate;

	@Column(name = "apply")
	@ApiModelProperty(value="申请人")
    private String apply;

	@Column(name = "apply_time")
	@ApiModelProperty(value="申请时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
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

	@Column(name = "is_delete")
	@ApiModelProperty(value="删除标志位 1删除 0未删")
    private int isDelete;
    
    @Column(name = "aircraft_model")
	@ApiModelProperty(value="航空器型号")
    private String aircraftModel;
    
    @Column(name = "aircraft_id")
	@ApiModelProperty(value="航空器ID")
    private String aircraftId;

	@Column(name = "aircraft_reg_no")
	@ApiModelProperty(value="航空器信息注册号")
    private String aircraftRegNo;

    @Column(name = "approver")
	@ApiModelProperty(value="审批人")
    private String approver;

	@Column(name = "approve_time")
	@ApiModelProperty(value="审批时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date approveTime;

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