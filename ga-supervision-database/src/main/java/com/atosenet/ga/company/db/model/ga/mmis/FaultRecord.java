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
 * 排故记录实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="mmis_mc_fault_record_list")
@Data
public class FaultRecord {
	
	/**
	 * 主键id（航材清单id）
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
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@ApiModelProperty(value="创建时间")
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
	@ApiModelProperty(value="编号")
    private String no;

	@Column(name = "status")
	@ApiModelProperty(value="数据业务状态")
    private String status;

	@Column(name = "is_delete")
	@ApiModelProperty(value="是否删除,1:删除,0:未删除")
    private int isDelete;

	@Column(name = "approver")
	@ApiModelProperty(value="审批人")
    private String approver;

	@Column(name = "approve_time")
	@ApiModelProperty(value="审批时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date approveTime;

	@Column(name = "fault_describe")
	@ApiModelProperty(value="发现的故障")
    private String faultDescribe;

	@Column(name = "discovery")
	@ApiModelProperty(value="发现人")
    private String discovery;

	@Column(name = "deal_with")
	@ApiModelProperty(value="处理措施")
    private String dealWith;

	@Column(name = "deal_who")
	@ApiModelProperty(value="处理人")
    private String dealWho;

	@Column(name = "is_reserve")
	@ApiModelProperty(value="是否保留")
    private int isReserve;

	@Column(name = "parent_type")
	@ApiModelProperty(value="主键类型")
    private String parentType;

	@Column(name = "parent_id")
	@ApiModelProperty(value="主键id")
    private String parentId;

	@Column(name = "parent_no")
	@ApiModelProperty(value="主表编号")
    private String parentNo;

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

}