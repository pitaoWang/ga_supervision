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
 * 
 * Module: trainning_record.java Author: Administrator Purpose: Defines the
 * Class trainning_record
 ***********************************************************************/
@Entity
@Data
@Table(name = "trainning_record")
public class TrainningRecord {
	/** 人员培训记录ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long id;
	
	/** 员工ID */
	@Column(name = "employee_id")
	@ApiModelProperty(value="员工ID")
	public Long employeeId;
	
	/** 人员培训类型 */
	//@Column(name = "train_type")
	//public java.lang.String trainType;
	
	/** 培训课题 */
	@Column(name = "train_subject")
	@ApiModelProperty(value="培训课题 ")
	public java.lang.String trainSubject;
	
	/** 人员培训内容 */
	//@Column(name = "train_content")
	//public java.lang.String trainContent;
	
	/** 人员培训开始时间 */
//	@Temporal(TemporalType.DATE)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "begindate")
	@ApiModelProperty(value="人员培训开始时间")
	private java.sql.Date beginDate;
	
	/** 人员培训结束时间 */
//	@Temporal(TemporalType.DATE)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "enddate")
	@ApiModelProperty(value="人员培训结束时间")
	private java.sql.Date endDate;
	
	/** 人员培训结果 */
	@Column(name = "result")
	@ApiModelProperty(value="人员培训结果")
	public java.lang.String result;
	
	/** 培训证书附件 */
	@Column(name = "certifacte_file")
	@ApiModelProperty(value="培训证书附件")
	public java.lang.String certifactefile;
	
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
	
	//add begin by dgw
	/**培训机构名称**/
	@Column(name = "train_organization")
	@ApiModelProperty(value="培训机构名称")
	public java.lang.String trainOorganization;
	/**证明人**/
	@Column(name = "witness")
	@ApiModelProperty(value="证明人")
	public java.lang.String witness;
	/**证明人联系方式**/
	@Column(name = "witness_phone")
	@ApiModelProperty(value="证明人联系方式")
	public java.lang.String witnessPhone;
	/**合格证编号**/
	@Column(name = "certificate_no")
	@ApiModelProperty(value="合格证编号")
	public java.lang.String certificateNo;
	/**颁发日期**/
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "issue_date")
	@ApiModelProperty(value="颁发日期")
	public java.util.Date issueDate;
	/**培训合格证扫描件**/
	@Column(name = "certificate_scanning")
	@ApiModelProperty(value="培训合格证扫描件")
	public java.lang.String certificateScanning;
	//add end by dgw
}