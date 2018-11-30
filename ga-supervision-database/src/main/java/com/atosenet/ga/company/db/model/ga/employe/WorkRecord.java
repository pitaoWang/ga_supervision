/**  
 * 
 * @Title: WorkRecord.java 
 * @Package com.atosenet.ga.company.db.model.ga.employe  
 * @author xyx   
 * @date 2018年7月31日 下午5:23:39 
 * @version V1.0
 *
 */
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

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: WorkRecord 
 * @author xyx 
 * @date 2018年7月31日 下午5:23:39 
 *  
 */
@Entity
@Data
@Table(name = "work_experience")
public class WorkRecord {
	/** 人员工作ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "work_id")
	public long id;
	/** 员工ID */
	@Column(name = "employee_id")
	public Long employeeId;
	/** 人员工作单位*/
	@Column(name = "work_unit")
	public java.lang.String workUnit;
	/** 工作时间 */
	@Column(name = "work_time")
	public java.lang.String workTime;
	/** 所任职位 */
	@Column(name = "post")
	public java.lang.String post;
	/** 工作内容 */
	@Column(name = "job_content")
	public java.lang.String jobContent;
	/** 证明人 */
	@Column(name = "witness")
	public java.lang.String witness;
	/**证明人联系方式**/
	@Column(name = "proof_contact")
	public java.lang.String proofContact;
	
}
