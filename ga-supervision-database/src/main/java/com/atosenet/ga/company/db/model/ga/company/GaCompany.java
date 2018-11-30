package com.atosenet.ga.company.db.model.ga.company;
/***********************************************************************
 * Module:  ga_company.java
 * Author:  熊江涛
 * Purpose: Defines the Class ga_company
 ***********************************************************************/


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.transaction.Transactional;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.collect.Lists;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ga_company")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
public class GaCompany {

	/** 通用航空企业ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="company_id")
	@ApiModelProperty(value = "企业id")
	private Integer companyId;
	
	public GaCompany(Integer companyId) {
		this.companyId = companyId;
	}
	
	/** 组织ID */
	//@Transient
	@Column(name="orgid")
	@ApiModelProperty(value = "组织id")
	private Long orgId;
	
	/** 通航企业名称 */
	@Length(max = 100)
	@Column(name="name")
	@ApiModelProperty(value = "通航企业名称")
	private String name;
	
	
	/*企业法定代表人身份证号*/
	@Column(name = "cardid")
	@ApiModelProperty(value = "企业法定代表人身份证号")
	private String cardid;
	
	/** 对应的管理局ID */
	@Column(name="authority_id")
	@ApiModelProperty(value = "对应的管理局ID")
	private Long authorityId;
	
	/** 对应的管理局名称 */
	@Column(name="authority_name")
	@ApiModelProperty(value = "对应的管理局名称")
	private String authorityName;
	
	/** 对应监管局ID */
	@Column(name="supervision_id")
	@ApiModelProperty(value = "对应的监管局ID")
	private Long supervisionId;
	
	@Column(name="supervision_name")
	@ApiModelProperty(value = "对应的监管局名称")
	private String supervisionName;
	
	/** 运行合格证编号 */
	
	/*@Column(name="certid")
	@ApiModelProperty(value = "运行合格证编号")
	private String certId;*/
	
	/** 企业LOGO照片目录*/
	@Length(max = 255)
	@Column(name="logo_file")
	@ApiModelProperty(value = "企业LOGO照片目录")
	private String logoFile;
	
	/** 工商等级证扫描件目录 */
	@Length(max = 255)
	@Column(name="register_cert_file")
	@ApiModelProperty(value = "工商等级证扫描件目录")
	private String registerCertFile;
	
	/** 运行合格证描件目录 */
	/*@Length(max = 255)
	@Column(name="standard_cert_file")
	@ApiModelProperty(value = "运行合格证描件目录")
	private String standardCertFile;*/
	
	/** 经营许可证描件目录 */
	@Length(max = 255)
	@Column(name="business_cert_file")
	@ApiModelProperty(value = "经营许可证描件目录")
	private String businessCertFile;
	
	/** 运行合格证类型（91部,135部,141部,145部） */
/*	@Transient
	@Column(name="operate_type")
	@ApiModelProperty(value = "运行合格证类型（91部,135部,141部,145部）")
	private String operateType;*/
	
	/** 主运行基地 */
	@Column(name="main_base")
	@ApiModelProperty(value = "主运行基地 ")
	private String mainBase;
	
	/** 企业联系电话 */
	@Length(max = 15)
	@Column(name="telephone")
	@ApiModelProperty(value = "企业联系电话")
	private String telephone;
	
	/** 传真号码 */	
	@Length(max = 15)
	@Column(name="fax")
	@ApiModelProperty(value = "传真号码")
	private String fax;
		
	
	/** 企业详细地址 */
	
	@Length(max = 255)
	@Column(name="address")
	@ApiModelProperty(value = "企业详细地址")
	private String address;
	
	/** 企业邮编 */
	@Length(max = 15)
	@Column(name="postcode")
	@ApiModelProperty(value = "企业邮编")
	private String postcode;
	
	/** 企业类别 */
	@Length(max = 25)
	@Column(name="company_type")
	@ApiModelProperty(value = "企业类别（1-合资,2-独资,3-国有,4-私营,5-全民所有制,6-集体所有制,7-股份制,8-有限责任）")
	private String companyType;

	/**
	 * 主任运行监察员
	 */
	
	@Column(name="poi")
	@ApiModelProperty(value = "主任运行监察员")
	private String poi;
	/**
	 * 主任试航监查员
	 */
	
	@Column(name="pmi")
	@ApiModelProperty(value = "主任试航监查员")
	private String pmi;
	
//	@Temporal(TemporalType.TIMESTAMP)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@JsonFormat(pattern = "yyyy-MM-dd")
	/**运行许可证颁发日期**/
	/*@Transient
    @Column(name = "license_begindate")
    @ApiModelProperty(value = "运行许可证颁发日期")
    private Date licenseBegindate;*/
    
//	@Temporal(TemporalType.TIMESTAMP)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@JsonFormat(pattern = "yyyy-MM-dd")
    /**运行许可证有效日期**/
	/*@Transient
    @Column(name = "license_enddate")
    @ApiModelProperty(value = "运行许可证有效日期")
    private Date licenseEnddate;*/
    
    @Column(name = "standard_type")
    @ApiModelProperty(value = "运行种类(1-一般商业飞行 2-农林喷洒 3-外载荷作业 4-训练飞行  5-空中游览 6-小型运输 7-大型运输)")
    private String standardType;
   
	
    /** 创建时间 */
	@Column(name="create_time")
	@CreatedDate
	@ApiModelProperty(value = "创建时间")
	private Date createtime;
	/** 更新时间 */
	@Column(name="update_time")
	@LastModifiedDate
	@ApiModelProperty(value = "更新时间")
	private Date updatetime;
	/** 0-正常，审核通过，1-删除，2-待审核，3-驳回 */
	@Column(name="flag")
	@ApiModelProperty(value = "")
	private short flag;
	
	/**
	 * (经营项目)经营范围类别，多个类别用逗号分隔 1-甲类 2-乙类 3-丙类 4-飞行俱乐部
	 */
	
	@Column(name="business_type")
	@ApiModelProperty(value = "经营项目(1-甲类 2-乙类 3-丙类 4-丁类)")
	private String businessType;
	/**
	 * 企业经营范围
	 */
	
	@Column(name="business_scope")
	@ApiModelProperty(value = "企业经营范围")
	private String businessScope;
	
	/**
	 * 企业法人联系电话
	 */
	@Column(name="user_tel")
	@ApiModelProperty(value = "企业法人联系电话")
	private String userTel;
	
	/**
	 * 经营许可证编号
	 */
	@Column(name="businessid")
	@ApiModelProperty(value = "经营许可证编号")
	private String businessid;
	
	/**
	 * 经营许可证颁发时间
	 */
	@Column(name = "business_begindate")
	@ApiModelProperty(value = "经营许可证颁发时间")
	private Date businessBegindate;
	
	/**
	 * 经营许可证有效期
	 */
	@Column(name = "business_enddate")
	@ApiModelProperty(value = "经营许可证有效期")
	private Date businessEnddate; 
	
	/**
	 * 企业注册地址
	 */
	@Column(name= "register_add")
	@ApiModelProperty(value = "企业注册地址")
	private String registerAdd;
	
	/**
	 * 企业注册资本
	 */
	@Column(name="register_cost")
	@ApiModelProperty(value = "企业注册资本(万元)")
	private String registerCost;
	
	/**
	 * 基地机场
	 */
	@Column(name="base_airport")
	@ApiModelProperty(value = "基地机场")
	private String baseAirport;
	


		

	
	/**企业法定代表人**/
	@Column(name = "legal_person")
	@ApiModelProperty(value = "企业法定代表人")
	private String legalPerson;
	
	/**
	 * 经营许可证附件
	 */
/*	@Transient
	@Column(name="cert_file")
	private String certFile;*/
	
	/**经营项目类别**/
	@Column(name = "business_project")
	@ApiModelProperty(value = "经营项目类别(1-经营 2-非经营)")
	private String businessProject;
	
	
	
	
	// add begin by dgw
	@Column(name = "province")
	@ApiModelProperty(value = "所属省份")
	private String province;
	
	@Column(name = "city")
	@ApiModelProperty(value = "所属城市")
	private String city;
	
	@Column(name = "legal_sex")
	@ApiModelProperty(value = "法人代表性别 1-男 2-女")
	private Integer legalSex;
	
	@Column(name = "legal_email")
	@ApiModelProperty(value = "法人邮箱")
	private String legalEmail;
	
	/*@Column(name = "business_is_effective")
	@ApiModelProperty(value = "经营许可证是否长期有效 1-是,2-否")
	private int businessIsEffective;*/
	
	@Column(name = "business_company")
	@ApiModelProperty(value = "经营许可证颁发单位")
	private String businessCompany;
	
	@Column(name = "register_cert_code")
	@ApiModelProperty(value = "工商等级证编号")
	private String registerCertCode;
	
	/*@Column(name = "register_is_effective")
	@ApiModelProperty(value = "工商等级是否长期有效 1-是 2-否")
	private int registerIsEffective;*/
	
	@Column(name = "register_begindate")
	@ApiModelProperty(value = "工商等级生效日期")
	private Date registerBegindate;
	
	@Column(name = "register_enddate")
	@ApiModelProperty(value = "工商等级有效日期")
	private Date registerEnddate;
	
	@Column(name = "register_company")
	@ApiModelProperty(value = "工商管理证颁发单位")
	private String registerCompany;
	
	@Column(name = "company_nature")
	@ApiModelProperty(value = "公司性质")
	private String companyNature;
	//end by dgw
	@Transient
	private boolean _checked = true;
	
	//公司拥有的所有飞行基地
	//by dgw
	/*@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "company_flybase", joinColumns = { @JoinColumn(name = "company_id") }, inverseJoinColumns = { @JoinColumn(name = "flybaseid") })
	@JsonManagedReference
	private List<FlyBase> flyBaseList = Lists.newArrayList();*/
	
	//公司拥有的所有的机场
	/*@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "company_airport", joinColumns = { @JoinColumn(name = "company_id") }, inverseJoinColumns = { @JoinColumn(name = "airport_id") })
	private List<Airport> airportList = Lists.newArrayList();*/
	
	//公司拥有的所有的维修基地
	//by dgw
	/*@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "company_maintainbase", joinColumns = { @JoinColumn(name = "company_id") }, inverseJoinColumns = { @JoinColumn(name = "maintainbase_id") })
	@JsonManagedReference
	private List<MaintainBase> maintainBase = Lists.newArrayList();*/
	
	//公司拥有的所有的维修基地
	//by dgw
	/*@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "company_landing_point", joinColumns = { @JoinColumn(name = "company_id") }, inverseJoinColumns = { @JoinColumn(name = "landing_point_id") })
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonManagedReference
	private List<LandingPoint> landingPoint = Lists.newArrayList();*/
	
	@Transactional
	public String[] getBusinesslist(String businessType)
	{
		return businessType.split(",");
	}
}
