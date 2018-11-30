/**  
 * 
 * @Title: CheckRecord.java 
 * @Package com.atosenet.ga.company.db.model.ga.company  
 * @author xyx   
 * @date 2018年8月8日 下午5:44:03 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.model.ga.company;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: CheckRecord 
 * @author xyx 
 * @date 2018年8月8日 下午5:44:03 
 *  
 */
@Entity
@Data
@Table(name="check_record")
public class CheckRecord {
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	/**
	 * 飞行器ID
	 */
	@Column(name="aircaft_id")
	private Long aircaftId;
	/**
	 * 记录标题
	 */
	@Column(name="record_title")
	@ApiModelProperty(value="记录标题")
	private String recordTitle;
	/**
	 * 定检人
	 */
	@Column(name="complete_person")
	@ApiModelProperty(value="定检人")
	private String completePerson;
	/**
	 * 定检时间
	 */
	@Column(name="complete_time")
	@ApiModelProperty(value="定检时间")
	private Date completeTime;
	/**
	 * 创建时间
	 */
	@Column(name="createtime")
	@ApiModelProperty(value="创建时间")
	private Date createTime;
	
	/**
	 * 修改时间
	 */
	@Column(name="updatetime")
	@ApiModelProperty(value="修改时间")
	private Date updateTime;
}
