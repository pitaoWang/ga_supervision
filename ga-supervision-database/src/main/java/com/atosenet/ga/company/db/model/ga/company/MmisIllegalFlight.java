package com.atosenet.ga.company.db.model.ga.company;

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

import com.atosenet.ga.company.db.model.ga.company.vo.OperationBaseVO;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MmisIllegalFlight {
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "company_id")
	@ApiModelProperty(value = "违规单位id")
	private Long companyId;
	
	@Column(name = "aircraft_id")
	@ApiModelProperty(value = "违规航空器id")
	private String aircraftId;
	
	@Column(name = "starttime")
	@ApiModelProperty(value = "违规开始时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.sql.Timestamp starttime;
	
	@Column(name = "endtime")
	@ApiModelProperty(value = "违规结束时间")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endtime;
	
	@Column(name = "reason")
	@ApiModelProperty(value = "违规原因")
	private String reason;
	
	@Column(name = "explain")
	@ApiModelProperty(value = "违规说明")
	private String explain;
	
	@Column(name = "createtime")
	@ApiModelProperty(value = "创建时间")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createtime;
	
	@Column(name = "creator")
	@ApiModelProperty(value = "创建人")
	private String creator;
	
	@Column(name = "updatetime")
	@ApiModelProperty(value = "更新时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatetime;
	
	@Column(name = "updator")
	@ApiModelProperty(value = "更新人")
	private String updator;
	
	@Column(name = "is_delete")
	@ApiModelProperty(value = "是否删除")
	private String isDelete;
	
	@Column(name = "name")
	private String name;
}
