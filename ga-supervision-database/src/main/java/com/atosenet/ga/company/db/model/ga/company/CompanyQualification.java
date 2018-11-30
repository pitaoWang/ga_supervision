package com.atosenet.ga.company.db.model.ga.company;

import java.util.*;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



@Entity
@Data
@Table(name="company_qualification")
public class CompanyQualification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "qualification_id")
	@ApiModelProperty(value = "运行合格证id")
	private Long id;// 资质ID

	@Column(name="company_id")
	@ApiModelProperty(value = "所属企业id")
	private Long companyId;// 资质所属企业ID
	
	@Column(name="code")
	@ApiModelProperty(value = "运行合格证编号")
	private String code;// 资质编号
	
	@Column(name="qualification_name")
	@ApiModelProperty(value = "运行合格证名称")
	private String qualificationname;// 资质名称
	
	@Column(name="starttime")
	@ApiModelProperty(value = "运行合格证生效时间")
	private java.sql.Date starttime;// 资质生效时间
	
	@Column(name="endtime")
	@ApiModelProperty(value = "运行合格证截止时间")
	private java.sql.Date endtime;// 资质有效截止时间
	
	@Column(name="attchment_address")
	@ApiModelProperty(value = "运行合格证扫描件")
	private String attchmentaddress;// 资质附件地址
	
	/*@Transient
	@Column(name="version_id")
	private int versionid;// 资质历史版本ID
*/	
	@Column(name="issue_org_id")
	@ApiModelProperty(value = "颁发单位id")
	private Long issueorgid;// 资质颁发单位ID
	
	@Column(name="issue_org_name")
	@ApiModelProperty(value = "颁发单位名称")
	private String issueorgname;// 资质颁发单位名称
	
	@Column(name="createtime")
	@ApiModelProperty(value = "创建时间")
	private Date createtime;// 创建时间
	
	@Column(name="updatetime")
	@ApiModelProperty(value = "更新时间")
	private Date updatetime;// 更新时间
	
	/*@Transient
	@Column(name="flag")
	private int flag;// 0-正常，审核通过，1-删除，2-待审核，3-驳回
*/	
	@Column(name = "is_effective")
	@ApiModelProperty(value = "是否长期有效")
	private Integer isEffective; //是否长期有效
	
	@Column(name = "operate_type")
	@ApiModelProperty(value = "运行合格证类型")
	private String operateType;//运行合格证类型

}