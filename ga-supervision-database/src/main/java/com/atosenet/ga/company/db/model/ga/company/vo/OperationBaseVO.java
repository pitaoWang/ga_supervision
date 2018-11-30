/**  
 * 
 * @Title: OperationBaseVO.java 
 * @Package com.atosenet.ga.company.db.model.ga.company.vo  
 * @author dgw   
 * @date 2018年8月8日 上午10:45:24 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.model.ga.company.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.atosenet.ga.company.db.model.ga.company.FlyBase;
import com.atosenet.ga.company.db.model.ga.company.LandingPoint;
import com.atosenet.ga.company.db.model.ga.company.MaintainBase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: OperationBaseVO 
 * @author dgw 
 * @date 2018年8月8日 上午10:45:24 
 *  
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationBaseVO implements Serializable {
	
	public static final long serialVersionId = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="base_id")
	public long baseId;
	
	/**
	 * 所属企业
	 */
	@Column(name="company_id")
	public Long companyId;
	
	/**
	 * 基地名称
	 */
	@Column(name= "base_name")
	public String baseName;
	
	/**
	 * 基地类型
	 */
	@Column(name="base_type")
	public String baseType;
	
	/**
	 * 所在省
	 */
	@Column(name= "flybase_province")
	public String provience;
	
	/**
	 * 所在市
	 */
	@Column(name="flybase_city")
	public String city;
	
	/**
	 * 详细地址
	 */
	@Column(name="flybase_detailed_adress")
	public String address;
	
	/**
	 * 基地电话
	 */
	@Column(name="flybase_tel")
	public String tel;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	public Date createTime;
	
	/**承担课程id**/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "takecourse_id")
	private Integer takecourseId;
	
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
	
	/**基地教室id**/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "classroom_id")
	private Long classroomId;
	
	/**教室类型**/
	@Column(name = "classroom_type")
	private String classroomType;
	
	/**地址**/
	@Column(name = "address")
	private String classAddress;
	
	/**学员最大容量**/
	@Column(name = "maximum_students")
	private String maximumStudents;

	
	


	

	
	
}
