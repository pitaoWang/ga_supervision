package com.atosenet.ga.company.db.model.ga.employe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/***********************************************************************
 * Module: ground_instructor_license.java Author: Administrator Purpose: Defines
 * the Class ground_instructor_license
 ***********************************************************************/
@Entity
@Data
@Table(name = "ground_instructor_license")
public class GroundInstructorLicense {
	@Id
	@TableGenerator(
            name="groundTeachIdGenerator",
            table="sys_id_gen",
            pkColumnName="gen_key",
            valueColumnName="gen_val",
            pkColumnValue="GL",
            initialValue=120000000,
            allocationSize=1)

	@GeneratedValue(strategy=GenerationType.TABLE, generator="groundTeachIdGenerator")
	@Column(name = "id")
	public int id;
	/** 地面教员执照种类（基础、高级、仪表 */
	@Column(name = "ground_instructor_license_type")
	public java.lang.String groundInstructorLicenseType;
	/** 地面教员航空器类别级别（飞机、旋翼机、滑翔机、初级飞机） */
	@Column(name = "ground_instructor_aircraft_class")
	public java.lang.String groundInstructorAircraftClass;
	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="createtime")
	public java.util.Date createTime;
	/** 更新时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="updatetime")
	public java.util.Date updateTime;
	/** 0-正常，审核通过，1-删除，2-待审核，3-驳回 */
	@Column(name = "flag")
	public long flag;

}