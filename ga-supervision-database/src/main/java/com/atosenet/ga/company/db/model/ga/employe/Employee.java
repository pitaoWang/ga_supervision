package com.atosenet.ga.company.db.model.ga.employe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.atosenet.ga.company.db.utils.ExcelData;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***********************************************************************
 * Module: employee.java Author: Administrator Purpose: Defines the Class
 * employee
 ***********************************************************************/
@Entity
@Data
@Table(name = "employee")
public class Employee implements ExcelData{
	/** 主键ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	public Long employeeId;
	/** 企业ID */
	@Column(name = "company_id")
	@ApiModelProperty(value="企业ID")
	public Long companyId;
	/** 员工ID */
	@Column(name = "userid")
	@ApiModelProperty(value="员工ID")
	public Long userId;
	/** 用户工号 */
	@Column(name = "workid")
	@ApiModelProperty(value="用户工号")
	public java.lang.String workId;
	/** 入职时间 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "join_date")
	@ApiModelProperty(value="入职时间")
	public java.util.Date joinDate;
	/** 身份证号码 */
	@Column(name = "cardid")
	@ApiModelProperty(value="身份证号码")
	public java.lang.String cardId;
	/** 护照号码 */
	@Column(name = "passport")
	@ApiModelProperty(value="护照号码")
	public java.lang.String passPort;
	/** 用户姓名 */
	@Column(name = "username")
	@ApiModelProperty(value="用户姓名 ")
	public java.lang.String userName;
	/** 人员类别 */
	@Column(name = "jobtitle")
	@ApiModelProperty(value="人员类别：飞行员、维修人员、气象人员、情报人员、飞行签派人员、管制人员、市场人员、财务人员、保障人员、飞行管理人员")
	public java.lang.String jobTitle;
	/** 性别 */
	@Column(name = "gender")
	@ApiModelProperty(value="性别")
	public java.lang.String gender;
	
	/** 生日 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "birthday")
	@ApiModelProperty(value="生日")
	public java.util.Date birthday;
	
	/** 有效通讯地址 */
	@Column(name = "home_address")
	@ApiModelProperty(value="有效通讯地址")
	public java.lang.String homeAddress;
	/** 邮编 */
	@Column(name = "home_postcode")
	@ApiModelProperty(value="邮编")
	public java.lang.String homePostcode;
	
	/** 手机 */
	@Column(name = "home_mobile")
	@ApiModelProperty(value="手机")
	public java.lang.String homeMobile;
	
	/** 政治面貌 */
	@Column(name = "political_status")
	@ApiModelProperty(value="政治面貌")
	public java.lang.String politicalStatus;
	/** 头像 */
	@Column(name = "photo")
	@ApiModelProperty(value="头像")
	public java.lang.String photo;
	/** 身份证正面扫描件文件路径 */
	@Column(name = "scan_card")
	@ApiModelProperty(value="身份证正面扫描件文件路径")
	public java.lang.String scanCard;
	
	/**
	 * 身份证反面扫描文件路径
	 */
	@Column(name = "back_scancard")
	@ApiModelProperty(value="身份证反面扫描件文件路径")
	public java.lang.String backScancard;
	
	/**
	 * 电子签名扫描路径
	 */
	@Column(name = "name_photo")
	@ApiModelProperty(value="电子签名扫描路径")
	public java.lang.String namePhoto;
	
	
	/** 国籍 */
	@Column(name = "nationality")
	@ApiModelProperty(value="国籍 ")
	public java.lang.String nationality;
	/** 种族 */
	@Column(name = "ethnicity")
	@ApiModelProperty(value="种族")
	public java.lang.String ethnicity;
	/** 教育程度 */
	@Column(name = "education")
	@ApiModelProperty(value="教育程度")
	public java.lang.String education;
	/** 是否是局方委任代表 */
	@Column(name = "isagent")
	@ApiModelProperty(value="是否是局方委任代表")
	public java.lang.String isagent;
	/** 部门名称 */
	@Column(name = "deptname")
	@ApiModelProperty(value="部门名称")
	public java.lang.String deptname;
	
	/**技术职务：主任教员、地面主任教员、助理主任教员、(企业）检查员*/
	@Column(name = "tech_pos")
	@ApiModelProperty(value="技术职务：主任教员、地面主任教员、助理主任教员、(企业）检查员")
	public java.lang.String  techpos;
	
	/** 年龄*/
	@Column(name = "age")
	@ApiModelProperty(value="年龄")
	public java.lang.String  age;
	/** 学校*/
	@Column(name = "school")
	@ApiModelProperty(value="学校")
	public java.lang.String  school;
	/** 邮箱*/
	@Column(name = "e_mail")
	@ApiModelProperty(value="邮箱")
	public java.lang.String  email;
	/** 紧急联系人*/
	@Column(name = "urgency_linkman")
	@ApiModelProperty(value="紧急联系人名称")
	public java.lang.String  urgencyLinkman;
	
	@Column(name="link_tel")
	@ApiModelProperty(value="紧急联系人电话")
	public java.lang.String  linkTel;
	
	/** 所属地区管理局id */
	@Column(name = "authority_id")
	@ApiModelProperty(value="所属地区管理局id")
	public Long authorityId;
	/** 所属监管局id */
	@Column(name = "supervision_id")
	@ApiModelProperty(value="所属监管局id")
	public Long supervisionId;
	/** 出勤状态 */
	@Column(name = "turn_out")
	@ApiModelProperty(value="出勤状态包括：出勤、病假、事假、休假")
	public java.lang.String turnOut;
	/** 合同状态*/
	@Column(name = "contract")
	@ApiModelProperty(value="合同状态包括：全职、兼职")
	public java.lang.String contract;
	/** "所属企业"*/
	@Transient
	@ApiModelProperty(value="所属企业名称")
	public java.lang.String companyName;
	/** 所属地区管理局*/
	@Transient
	@ApiModelProperty(value="所属地区管理局名称")
	public String authorityName;
	/** 所属监管局*/
	@Transient
	@ApiModelProperty(value="所属地区监管局名称")
	public String supervisionName;
	/** 所属运行基地 */
	@Transient
	@ApiModelProperty(value="所属运行基地名称")
	public String baseName;
	/** 所属运行基地id */
	@Column(name = "base_id")
	@ApiModelProperty(value="所属运行基地id")
	public Long baseId;
	/** 创建时间 */
	@CreatedDate
	@Column(name="createtime")
	@ApiModelProperty(value="创建时间 ")
	public java.util.Date createTime;
	/** 更新时间 */
	@LastModifiedDate
	@Column(name="updatetime")
	@ApiModelProperty(value="更新时间")
	public java.util.Date updateTime;
	/** 0-正常，审核通过，1-删除，2-待审核，3-驳回 */
	@Column(name = "flag")
	public Integer flag;
	@Transient
	@ApiModelProperty(value="分页总条数")
	public Long total;
	/**
	 * (non-Javadoc)
	 * <p>Title: getFieldTitles</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.atosenet.ga.company.db.utils.ExcelData#getFieldTitles()
	 * 
	 */
	@Override
	public String[] getFieldTitles() {
		String[] Titles = {"姓名","人员类型","性别","年龄","身份证号","出生年月","国籍","民族",
				"学历","毕业院校","有效通讯地址","邮政编号","手机号码","电子邮箱","紧急联系人","所属企业",
				"职位","出勤状态","合同状态","是否是委任代表","所属运行基地"};
		return Titles;
	}
	/**
	 * (non-Javadoc)
	 * <p>Title: getFieldNames</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.atosenet.ga.company.db.utils.ExcelData#getFieldNames()
	 * 
	 */
	@Override
	public String[] getFieldNames() {
		String[] Name = {"userName","jobTitle","gender","age","cardId","birthday",
				"nationality","ethnicity","education","school","homeAddress","homePostcode",
				"homeMobile","email","urgencyLinkman","companyName",
				"techpos","turnOut","contract","isagent","baseName"};
		return Name;
	}
    
}