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
 * Module: person_exam_record.java Author: Administrator Purpose: Defines the Class
 * person_exam_record
 ***********************************************************************/
@Entity
@Data
@Table(name = "person_exam_record")
public class PersonExamRecord {
	/** 人员考试信息ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long id;
	/** 人员考试科目 */
	@Column(name = "subject")
	@ApiModelProperty(value="人员考试科目")
	public java.lang.String subject;
	/** 员工ID */
	@Column(name = "employee_id")
	@ApiModelProperty(value="员工ID")
	public Long employeeId;
	/** 员工考试成绩 */
	@ApiModelProperty(value="员工考试成绩")
	@Column(name = "score")
	public Float score;
	/** 员工考试通过状态 */
	@Column(name = "pass_status")
	@ApiModelProperty(value="员工考试通过状态")
	public String passStatus;
	
	/**
	 * 考试类型
	 */
	@Column(name="exam_type")
	@ApiModelProperty(value="考试类型")
	public String examType;
	
	/**考试时间 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "exame_date")
	@ApiModelProperty(value="考试时间")
	public java.util.Date exameDate;
	/** 考试地点 */
	@Column(name = "exam_site")
	@ApiModelProperty(value="考试地点")
	public java.lang.String examSite;
	/** 监考老师id */
	@Column(name = "exam_teacher_id")
	@ApiModelProperty(value="监考老师id")
	public String examTeacherId;
	/** 成绩照片 */
	@Column(name = "score_file")
	@ApiModelProperty(value="成绩照片")
	public java.lang.String scoreFile;
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
	/** 考试合格证编号 */
	@Column(name = "certificate_no")
	@ApiModelProperty(value="考试合格证编号")
	public java.lang.String certificateNo;
	/**颁发时间 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "issue_date")
	@ApiModelProperty(value="颁发时间")
	public java.util.Date issueDate;
	/** 颁发单位 */
	@Column(name = "issue_unit")
	@ApiModelProperty(value="颁发单位")
	public java.lang.String issueUnit;
	/** 考试合格证扫描件 */
	@Column(name = "certificate_scanning")
	@ApiModelProperty(value="考试合格证扫描件")
	public java.lang.String scanning;
}