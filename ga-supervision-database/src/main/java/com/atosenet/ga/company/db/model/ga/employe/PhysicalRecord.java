package com.atosenet.ga.company.db.model.ga.employe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***********************************************************************
 * Module: physical_record.java Author: Administrator Purpose: Defines the Class
 * physical_record
 ***********************************************************************/
@Entity
@Data
@Table(name = "physical_record")
public class PhysicalRecord {
	/** 人员体检信息ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long id;
	/** 员工ID */
	@Column(name = "employee_id")
	@ApiModelProperty(value="员工ID")
	public Long employeeId;
	/** 体检合格证级别 */
	@Column(name = "certificate_grade")
	@ApiModelProperty(value="体检合格证等级包括：Ⅰ级、Ⅱ级、Ⅲa级、Ⅲb级、Ⅳa级、Ⅳb级")
	public java.lang.String certificateGrade;
	/** 体检合格证颁发时间 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "certificate_start_time")
	@ApiModelProperty(value="体检合格证颁发时间")
	public java.util.Date certificateStartTime;
	/** 体检合格证有效截止时间 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "certificate_validity_time")
	@ApiModelProperty(value="体检合格证有效截止时间")
	public java.util.Date certificateValidityTime;
	/** 体检合格证扫描件目录 */
	@Column(name = "certificate_filepath")
	@ApiModelProperty(value="体检合格证扫描件目录")
	public java.lang.String certificateFilepath;
	/** 体检合格状态 */
	@Column(name = "pass_status")
	@ApiModelProperty(value="体检合格状态")
	public long passStatus;
	/** 备注 */
	@Column(name = "remark")
	@ApiModelProperty(value="备注")
	public java.lang.String remark;
	/** 合格证编号 */
	@Column(name = "certificate_no")
	@ApiModelProperty(value="合格证编号 ")
	public java.lang.String certificateNo;
	/** 发证单位 */
	@Column(name = "issue_unit")
	@ApiModelProperty(value="发证单位 ")
	public java.lang.String issueUnit;
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

}