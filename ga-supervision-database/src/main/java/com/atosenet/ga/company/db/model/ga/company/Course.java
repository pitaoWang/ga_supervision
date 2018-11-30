/**
 * 
 */
package com.atosenet.ga.company.db.model.ga.company;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lvhao
 *
 */
@Entity
@Data
@Table(name="course")
public class Course {
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	/**
	 * 模拟器id
	 */
	@Column(name="simulator_id")
	@ApiModelProperty(value="模拟器id")
	private Long simulatorId;
	
	/**
	 * 课程编号
	 */
	@Column(name="course_number")
	@ApiModelProperty(value="课程编号")
	private String courseNum;
	
	/**
	 * 课程类型
	 */
	@Column(name="course_type")
	@ApiModelProperty(value="课程类型：私用驾驶员执照课程、商用驾驶员执照课程、仪表等级课程、高性能训练课程")
	private String courseType;
	
	/**
	 * 课程名称
	 */
	@Column(name="course_name")
	@ApiModelProperty(value="课程名称")
	private String courseName;
	
	/**
	 * 创建时间
	 */
	@Column(name="createtime")
	@ApiModelProperty(value="创建时间")
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	@Column(name="updatetime")
	@ApiModelProperty(value="更新时间")
	private Date updateTime;
	
}
