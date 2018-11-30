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
 * 换件记录实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="mmis_mc_replace_record_list")
@Data
public class ReplaceRecord {
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

	@Column(name = "parts_type")
	@ApiModelProperty(value="部件类型")
    private String partsType;

	@Column(name = "broken_name")
	@ApiModelProperty(value="拆卸件名称")
    private String brokenName;

	@Column(name = "broken_part_no")
	@ApiModelProperty(value="拆卸件件号")
    private String brokenPartNo;

	@Column(name = "broken_serial_no")
	@ApiModelProperty(value="拆卸件件号")
    private String brokenSerialNo;

	@Column(name = "install_name")
	@ApiModelProperty(value="安装件名称")
    private String installName;

	@Column(name = "install_serial_no")
	@ApiModelProperty(value="安装件序号")
    private String installSerialNo;

	@Column(name = "install_part_no")
	@ApiModelProperty(value="安装件件号")
    private String installPartNo;

    @Column(name = "status")
	@ApiModelProperty(value="数据业务状态")
    private String status;

	@Column(name = "is_delete")
	@ApiModelProperty(value="是否删除,1:删除,0:未删除")
    private int isDelete;

	@Column(name = "parent_type")
	@ApiModelProperty(value="主键类型")
    private String parentType;

	@Column(name = "parent_id")
	@ApiModelProperty(value="主键id")
    private String parentId;

	@Column(name = "parent_no")
	@ApiModelProperty(value="主表编号")
    private String parentNo;

	@Column(name = "install_part_certificate")
	@ApiModelProperty(value="安装航材合格证")
    private String installPartCertificate;

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