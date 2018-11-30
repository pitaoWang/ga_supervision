package com.atosenet.ga.company.db.model.ga.employe;

import java.util.Date;

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

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@Table(name = "award_record")
public class AwardRecord {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long id;
	
	/** 员工ID */
	@Column(name = "employee_id")
	@ApiModelProperty(value="员工ID")
	public Long employeeId;
	
	/**
	 * 发生时间
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "time")
	@ApiModelProperty(value="发生时间")
	public Date time;
	
	/**
	 * 内容
	 */
	@Column(name = "content")
	@ApiModelProperty(value="内容")
	public String content;
	
	/**
	 * 所在单位
	 */
	@Column(name = "unit")
	@ApiModelProperty(value="所在单位")
	public String unit;
}
