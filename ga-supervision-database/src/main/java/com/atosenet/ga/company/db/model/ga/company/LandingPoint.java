package com.atosenet.ga.company.db.model.ga.company;
/***********************************************************************
 * Module:  landing_point.java
 * Author:  熊江涛
 * Purpose: Defines the Class landing_point
 ***********************************************************************/

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "landing_point")
public class LandingPoint {
	/** 起降点ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	/** 起降点名称 */
	@Length(max = 255)
	private String name;
	/** 归属企业ID */
	@OneToOne
	@JoinColumn(name = "company_id")
	@JsonBackReference
	private GaCompany companyId;
	
	@Column(name="landing_grade")
	private String landingGrade;
	/** 经度 */
	private String longitude;
	/** 纬度 */
	private String latitude;
	@Column(name = "isnightfight")
	private short isnightfight;/*是否支持夜航*/
	@Column(name = "iscontroltower")
	private short iscontroltower;/* 是否塔台服务*/
	@Column(name = "isnavidevice")
	private short isnavidevice;/* 是否有导航设施*/
	@Column(name = "fire_grade")
	private String fireGrade;/* 消防等级*/
    @Column(name = "altitude")
    private String altitude;/*海拔高度*/
    
//	@Temporal(TemporalType.TIMESTAMP)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "license_begindate")
    private java.sql.Date licenseBegindate;/*许可证起始日期*/
	
//	@Temporal(TemporalType.TIMESTAMP)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "license_enddate")
    private java.sql.Date licenseEnddate;/*许可证截止日期*/
    /* 机场是否可用 默认值为0,0可用,1不可用 */
    private short isavailable;
	
	/** 所在省 */
	@Length(max = 255)
	private String province;
	/** 所在市 */
	@Length(max = 255)
	private String city;
	/** 所属管理局 */
	@Column(name = "manage_org_id")
	private long manageOrgId;
	/** 所属监管局 */
	@Column(name = "supervise_org_id")
	private long superviseOrgId;
	/** 地址 */
	@Length(max = 255)
	private String address;
	/** 电话 */
	@Length(max = 15)
	private String tel;
	/**
	 * 用于接收使用起降点公司的Id
	 */
	@Transient
	private Long gaCompanyId;
	
	/** 更新时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updatetime;
	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createtime;
	/** 0-正常，审核通过，1-删除，2-待审核，3-驳回 */
	private long flag;
	
}