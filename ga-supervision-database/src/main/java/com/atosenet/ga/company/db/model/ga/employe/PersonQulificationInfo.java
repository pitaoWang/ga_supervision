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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***********************************************************************
 * Module: person_qulificationinfo.java Author: Administrator Purpose: Defines
 * the Class person_qulificationinfo
 ***********************************************************************/
@Entity
@Data
@Table(name = "person_qulificationinfo")
@JsonIgnoreProperties(value = { "employee"})
public class PersonQulificationInfo {
	/** 人员资质ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long id;
	/** 用户ID */
	@Column(name = "employee_id")
	@ApiModelProperty(value="用户ID")
	public Long employeeId;
	/** 执照编号 */
	@Column(name = "license_no")
	@ApiModelProperty(value="执照编号")
	public java.lang.String licenseno;
	/** 执照类型*/
	@Column(name = "license_type")
	@ApiModelProperty(value="执照类型(气象人员执照，飞行人员执照，管制人员执照，情报人员执照，飞行签派员执照，航空器部件维修执照，航空器维修执照)")
	public java.lang.String licensetype;

	/** 执照颁发时间 */
//	@Temporal(TemporalType.DATE)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "issue_date")
	@ApiModelProperty(value="执照颁发时间")
	public java.sql.Date issuedate;
	/** 执照到期时间 */
//	@Temporal(TemporalType.DATE)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "expire_date")
	@ApiModelProperty(value="执照到期时间 ")
	public java.sql.Date expiredate;
	/** 执照扫描件目录 */
	@Column(name = "filepath")
	@ApiModelProperty(value="执照扫描件目录")
	public java.lang.String filePath;
	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="createtime")
	@ApiModelProperty(value="创建时间")
	public java.util.Date createTime;
	/** 更新时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="updatetime")
	@ApiModelProperty(value="更新时间")
	public java.util.Date updateTime;
	/** 0-正常，审核通过，1-删除，2-待审核，3-驳回 */
	@Column(name = "flag")
	public long flag;
	/** 执照颁发单位 */
	@Column(name = "issue_unit")
	@ApiModelProperty(value="执照颁发单位")
	public java.lang.String issueUnit;
	/** 执照更新时间 */
//	@Temporal(TemporalType.DATE)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "license_updatetime")
	@ApiModelProperty(value="执照更新时间")
	public java.sql.Date licenseUpdatetime;
}