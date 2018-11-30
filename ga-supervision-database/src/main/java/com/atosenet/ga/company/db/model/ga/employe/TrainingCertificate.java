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

import lombok.Data;
@Entity
@Data
@Table(name = "trainning_certificate")
public class TrainingCertificate {
	
	/** 人员证书记录ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long id;
	/** 合格证类型：
	 * 气象人员理论培训；
	 * 气象人员技能培训；
	 * 情报人员理论培训；
	 * 情报任运技能培训 
	 * 
	 * */
	@Column(name = "certificate_type")
	public java.lang.String certificatetype;
	/** 员工ID */
	@Column(name = "employee_id")
	public Long employeeId;
	
	/** 合格证编号 */
	@Column(name = "certificate_no")
	public java.lang.String certificateno;
	/** 合格证颁发单位 */
	@Column(name = "issue_office")
	public java.lang.String issueoffice;
	
	/** 合格证颁发日期 */
//	@Temporal(TemporalType.DATE)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "issue_time")
	private java.sql.Date issuetime;
	/** 合格证有效日期 */
//	@Temporal(TemporalType.DATE)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "expire_time")
	private java.sql.Date expiretime;
	
	/** 图片路径 */
	@Column(name = "certificate_file")
	public java.lang.String certificatefile;
	
	
	/** 合格证创建日期 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "create_time")
	public java.util.Date createtime;
	
	/** 合格证修改日期 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "update_time")
	public java.util.Date updatetime;
	
	/** 图片路径 */
	@Column(name = "flag")
	public int flag;
}
