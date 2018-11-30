/**
 * 
 */
package com.atosenet.ga.company.db.model.ga.base;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.atosenet.ga.company.db.model.ga.company.Document;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lvhao
 *
 */

@Entity
@Data
@Table(name="base_classroom")
public class BaseClassroom implements Serializable{
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="classroom_id")
	private Long classroomId;
	
	/**
	 * 运行基地id
	 */
	@Column(name="base_id")
	@ApiModelProperty(value = "运行基地id")
	private Long baseId;
	
	/**
	 *教室类型  包括教室   讲评室
	 */
	@Column(name="classroom_type")
	@ApiModelProperty(value = "教室类型  (1-教室   2-讲评室)")
	private String classroomType;
	
	/**
	 * 教室地址
	 */
	@Column(name="address")
	@ApiModelProperty(value = "教室地址")
	private String address;
	
	/**
	 * 	最大容纳学生  
	 */
	@Column(name="maximum_students")
	@ApiModelProperty(value = "最大容纳学生")
	private String maxStu;
	
	

}
