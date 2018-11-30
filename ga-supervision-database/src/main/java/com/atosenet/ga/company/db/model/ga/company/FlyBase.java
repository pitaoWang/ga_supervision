package com.atosenet.ga.company.db.model.ga.company;
/***********************************************************************
 * Module:  flybase.java
 * Author: 熊江涛
 * Purpose: Defines the Class flybase
 ***********************************************************************/

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "flybase")
@Data
@ToString(exclude = {"companyId"})
public class FlyBase {
	/** 飞行基地ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long flybaseid;
	/** 飞行基地名称 */
	@Length(max = 20)
	private String flybasename;

	/** 归属企业ID */
	@OneToOne
	@JoinColumn(name = "company_id")
	@JsonBackReference
	private GaCompany companyId;
	
	/** 所属的管理局ID */
	@Column(name = "authority_id")
	private Long authorityId;
	/** 所属的监管局ID */
	@Column(name = "supervision_id")
	private Long supervisionId;
	/** 机场ID */
	// @ManyToOne
	// @JoinColumn(name = "airport_id")
	// @JsonIgnore
	@Column(name = "airport_id")
	private Integer airportId;
	
	@Column(name = "base_type")
	private String baseType;//飞行基地类型
	
	/** 飞行基地地址省 */
	@Length(max = 100)
	@Column(name = "flybase_province")
	private String flybaseProvince;
	/** 飞行基地地址市 */
	@Length(max = 100)
	@Column(name = "flybase_city")
	private String flybaseCity;
	/** 飞行基地详细地址 */
	@Length(max = 255)
	@Column(name = "flybase_detailed_adress")
	private String flybaseDetailedAdress;
	/** 飞行基地电话 */
	@Length(max = 15)
	@Column(name = "flybase_tel")
	private String flybaseTel;
	/** 飞行基地邮编 */
	@Length(max = 15)
	@Column(name = "flybase_postalcode")
	private String flybasePostalcode;
	/** 审核状态 */
	@Column(name = "auditing_status")
	private int auditingStatus;
	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createtime;
	/** 更新时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updatetime;
	/** 0-正常，审核通过，1-删除，2-待审核，3-驳回 */
	private short flag;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "company_flybase", joinColumns = { @JoinColumn(name = "company_id") }, inverseJoinColumns = {
			@JoinColumn(name = "flybaseid") })
	@JsonIgnore
	private List<GaCompany> gaCompany = Lists.newArrayList();
	/**
	 * 用于接收使用飞行基地的公司Id
	 */
	@Transient
	private Long gaCompanyId;

}