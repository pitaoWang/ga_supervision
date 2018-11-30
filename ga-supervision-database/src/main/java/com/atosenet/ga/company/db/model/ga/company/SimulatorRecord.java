/**
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
 * @author lvhao
 *
 */
@Entity
@Data
@Table(name="simulator_record")
public class SimulatorRecord {
	
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
	 * 记录类型
	 */
	@Column(name="record_type")
	@ApiModelProperty(value="记录类型")
	private String recordType;
	
	/**
	 * 开始时间
	 */
	@Column(name="begin_time")
	@ApiModelProperty(value="开始时间")
	private Date beginTime;
	
	/**
	 * 结束时间
	 */
	@Column(name="end_time")
	@ApiModelProperty(value="结束时间")
	private Date endTime;
	
	/**
	 * 鉴定结果
	 */
	@Column(name="identification_result")
	@ApiModelProperty(value="鉴定结果")
	private String identificationResult;
	
	/**
	 * 申请等级
	 * 1-6
	 */
	@Column(name="application_lever")
	@ApiModelProperty(value="申请等级：1-6")
	private int applicationLever;
	
	/**
	 * 鉴定标准
	 * 1 CCAR-60，2 ICAO Doc9625，3 FAR-60，4 CS-FSTD(A)/(H)
	 */
	@Column(name="ident_standard")
	@ApiModelProperty(value="鉴定标准：CCAR-60，ICAO Doc9625，FAR-60， CS-FSTD(A)/(H)")
	private String identStandard;
		
	/**
	 * 备注
	 */
	@Column(name="content")
	@ApiModelProperty(value="备注")
	private String content;
	

}
