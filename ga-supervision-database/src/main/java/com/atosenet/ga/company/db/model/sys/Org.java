package com.atosenet.ga.company.db.model.sys;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.atosenet.ga.company.db.core.common.IdEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.collect.Lists;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "sys_org")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Org  {
	private static final long serialVersionUID = 1L;
	public static final Integer DEL_FLAG_NORMAL = 0;
	public static final Integer DEL_FLAG_DELETED = 1;
	public static final Integer DEL_FLAG_DRAFT = 2;
	
	public static final String DEL_FLAG_NORMAL_STR = "0";
	public static final String DEL_FLAG_DELETED_STR = "1";
	public static final String DEL_FLAG_DRAFT_STR = "2";
	/** 主键ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public long id;
	/** 上级ID */

	@ManyToOne
	@JoinColumn(name="parent_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonBackReference
	private Org parent;
	
	@OneToMany(mappedBy="parent")
	@JsonManagedReference
	List<Org> children = Lists.newArrayList();
	
	/** 组织层次 */
	private Integer depth;
	
	/** 组织名称 */
	@Column(name="`name`")
	private String name;
	
	/** 组织类型 */
	private long orgtype;
	
	/** 职务 */
	private String jobtitle;
	
	/** 部门电话 */
	private String telephone;
	
	/** 组织排序 */
	private Integer sort;
	
	
	@Column(name="parent_ids")
	private String parentids;
	
	
	/** 创建时间 */
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date createTime;

	/** 更新时间 */
	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date updateTime;

	/** 删除标记 */
	@Column(name = "del_flag")
	public Integer delFlag;

}
