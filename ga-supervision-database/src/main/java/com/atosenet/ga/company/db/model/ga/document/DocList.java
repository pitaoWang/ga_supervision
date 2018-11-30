package com.atosenet.ga.company.db.model.ga.document;
/***********************************************************************
 * Module:  ga_official.java
 * Author:  Administrator
 * Purpose: Defines the Class ga_official
 ***********************************************************************/

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name="doc_list")
@Data
public class DocList {

	/** 主键ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	/** 标题*/
	@Column(name="title")
	private String title;
	/** 内容 */
	@Column(name="content")
	private String content;
	/** 附件路径，多个附件用逗号隔开 */
	@Column(name="filepath")
	private String filepath;
	
	/**公文创建者id(通航管理员创建的)*/
	@Column(name="creator")
	private long creator;
	
	
	/**公文创建者(监管人员创建者的id)*/
	@Column(name="superviseId")
	private Long superviseId;
	
	/**公文创建者(监管人员创建者的监管局名称)*/
	@Column(name="supervisecompanyname")
	private String supervisecompanyname;
	
	/**公文创建者(监管人员创建者的名子)*/
	@Column(name="superviseemployeename")
	private String superviseemployeename;

	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="createtime")
	private Date createtime;

	/**状态，0-发布 1-删除 2-待审核*/
	private short flag;


}