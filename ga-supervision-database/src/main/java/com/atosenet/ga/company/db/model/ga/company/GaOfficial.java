package com.atosenet.ga.company.db.model.ga.company;
/***********************************************************************
 * Module:  ga_official.java
 * Author:  Administrator
 * Purpose: Defines the Class ga_official
 ***********************************************************************/

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name="ga_official")
@Data
public class GaOfficial {

	/** 主键ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="authority_id")
	private long authorityId;
	/** 组织ID */
	@Column(name="orgid")
	private Long orgId;
	/** 局方名称 */
	@Length(max = 255)
	@Column(name="name")
	private String name;
	/** 局方管理范围 */
	@Column(name="manage_scope")
	private String manageScope;
	
	/** 局方所在地省 */
	@Column(name="provice")
	private String provice;
	/** 局方所在地市 */
	@Column(name="city")
	private String city;
	/** 局方详细地址 */
	@Column(name="address")
	private String address;
	/** 局方所在地邮编 */
	@Column(name="postcode")
	private String postcode;
	/** 局方联系电话 */
	@Length(max = 255)
	@Column(name="telephone")
	private String telephone;
	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="createtime")
	private Date createtime;
	/** 更新时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="updatetime")
	private Date updatetime;
	/** 0-正常，审核通过，1-删除，2-待审核，3-驳回 */
	private short flag;


}