/**  
 * 
 * @Title: TakeCourse.java 
 * @Package com.atosenet.ga.company.db.model.ga.company  
 * @author dgw   
 * @date 2018年8月7日 上午11:49:47 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.model.ga.company;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/** 
 * TODO(承担课程)
 * 
 * @ClassName: TakeCourse 
 * @author dgw 
 * @date 2018年8月7日 上午11:49:47 
 *  
 */
@Entity
@Data
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table(name = "take_course")
public class TakeCourse {
	/**承担课程id**/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "takecourse_id")
	private Long takecourseId;
	
	/**运行基地id**/
	@Column(name="base_id")
	public Long baseId;
	
	
	
	/*@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="base_id",referencedColumnName="base_id" ,insertable=false, updatable=false)
	private OperationBase operationBase;
	
	 public OperationBase getOperationBase() {
	    return operationBase;
	 }
	 public void setOperationBase(OperationBase operationBase) {
	        this.operationBase = operationBase;
	 }*/
	
	/**课程等级**/
	@Column(name = "course_grade")
	private String courseGrade;
	
	/**课程分类**/
	@Column(name = "course_type")
	private String courseType;
	
	/**训练大纲id**/
	@Column(name = "syllabus_id")
	private String syllabusId;
	
	/**有效期**/
	@Column(name = "effective_date")
	private Date effectiveDate;
	
	/**容量**/
	@Column(name = "capacity")
	private String capacity;
}
