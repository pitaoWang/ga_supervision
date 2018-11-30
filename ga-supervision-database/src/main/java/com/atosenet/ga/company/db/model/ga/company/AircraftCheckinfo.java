package com.atosenet.ga.company.db.model.ga.company;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "aircraft_checkinfo")
public class AircraftCheckinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long id;
	
	/** 检查类型（定检、年检） */
	@Column(name = "check_type")
	public int checktype;
	
	/** 航空器ID */
	@Column(name = "aircraft_id")
	public long aircraftid;
	
	/** 航空器检查内容 */
	@Column(name = "check_content")
	public String checkcontent;
	
	/** 检查证照扫描路径 */
	@Column(name = "check_scan_path")
	public String checkscanpath;
	
	/** 检查等级 */
	@Column(name = "grade")
	public String grade;
	
	/** 检查结果 */
	@Column(name = "check_result")
	public String checkresult;
	
	/** 检查有限起始时间 */
	@Column(name = "begindate")
	public Date begindate;
	
	/** 检查有效截止时间 */
	@Column(name = "enddate")
	public Date enddate;
	
	/** 检查历史版本信息 */
	@Column(name = "version")
	public String version;
	
	/** 检查单位ID（选填） */
	@Column(name = "org_id")
	public long orgid;
	
	/** 检查单位名称 */
	@Column(name = "org_name")
	public String orgname;
	
	/** 检查组织者姓名 */
	@Column(name = "organiger_name")
	public String organigername;
	
	/** 检查参与者（多个人的姓名） */
	@Column(name = "participator_name")
	public String participatorname;
	
	/** 检查描述 */
	@Column(name = "description")
	public String description;
	
	/** 创建时间 */
	@Column(name = "createtime")
	public Date createtime;
	
	/** 更新时间 */
	@Column(name = "updatetime")
	public Date updatetime;
	
	/** 0-正常，审核通过，1-删除，2-待审核，3-驳回 */
	@Column(name = "flag")
	public long flag;

}