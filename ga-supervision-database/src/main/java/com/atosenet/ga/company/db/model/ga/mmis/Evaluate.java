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
 * 适航指令/服务通告评估实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="mmis_tc_as_evaluate")
@Data
public class Evaluate {
	
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

	@Column(name = "no")
	@ApiModelProperty(value="编号")
    private String no;

	@Column(name = "evaluate_company")
	@ApiModelProperty(value="企业信息ID")
    private String evaluateCompany;

	@Column(name = "as_inform_no")
	@ApiModelProperty(value="适航资料编号")
    private String asInformNo;

	@Column(name = "title")
	@ApiModelProperty(value="标题")
    private String title;

	@Column(name = "as_type")
	@ApiModelProperty(value="类型")
    private String asType;

	@Column(name = "as_source")
	@ApiModelProperty(value="来源")
    private String asSource;

	@Column(name = "involve_aircraft_model")
	@ApiModelProperty(value="涉及航空器型号")
    private String involveAircraftModel;

	@Column(name = "involve_aircraft_serial")
	@ApiModelProperty(value="涉及航空器序列号")
    private String involveAircraftSerial;

	@Column(name = "is_execute")
	@ApiModelProperty(value="是否执行 0不执行 1执行")
    private int isExecute;

	@Column(name = "execute_way")
	@ApiModelProperty(value="执行方式")
    private String executeWay;

	@Column(name = "execute_deadline")
	@ApiModelProperty(value="执行期限")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date executeDeadline;

	@Column(name = "evaluate_illustrate")
	@ApiModelProperty(value="评估说明")
    private String evaluateIllustrate;

	@Column(name = "evaluator")
	@ApiModelProperty(value="评估人")
    private String evaluator;

	@Column(name = "evaluate_time")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@ApiModelProperty(value="评估时间")
    private Date evaluateTime;

	@Column(name = "attachment_ids")
	@ApiModelProperty(value="附件")
    private String attachmentIds;

	@Column(name = "company_id")
	@ApiModelProperty(value="组织ID")
    private String companyId;

	@Column(name = "approver")
	@ApiModelProperty(value="审批人")
    private String approver;

	@Column(name = "approve_time")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@ApiModelProperty(value="审批时间")
    private Date approveTime;

	@Column(name = "is_delete")
	@ApiModelProperty(value="删除标志位 1删除 0未删")
    private int isDelete;

	@Column(name = "status")
	@ApiModelProperty(value="处理状态")
    private String status;

	@Column(name = "revisor")
	@ApiModelProperty(value="更新人")
    private String revisor;

	@Column(name = "revisor_name")
	@ApiModelProperty(value="更新人姓名")
    private String revisorName;

	@Column(name = "revise_time")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@ApiModelProperty(value="更新时间")
    private Date reviseTime;

}