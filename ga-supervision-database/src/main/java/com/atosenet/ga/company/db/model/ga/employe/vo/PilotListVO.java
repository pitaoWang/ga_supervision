package com.atosenet.ga.company.db.model.ga.employe.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class PilotListVO {
	@Id
	@Column(name = "C_FLIGHT_PERSON_ID")
	public Long employeeId;
	
	@Transient
	public Integer companyId;
	
//	@Column(name="company_name")
//	public String companyName;
	
	@Column(name = "username")
	public String username;
	
	@Column(name = "flight_team")
	public String flightteam;
	
	@Transient
	public String givenname;
	
	/** 入职时间 */
	@Transient
	public Date joinDate;
	
	@Transient
	public String cardid;
	
	@Transient
	public String homeMobile;
	
	@Transient
	public String businessTel;
	
	//pilot
	@Transient
	public Long pilotId;
	
	@Column(name = "tech_class")
	public String techClass;
	
	@Column(name="total")
	public Long flySumTime;
	
	@Transient
	public Long flySumIllegal;
	
	@Transient
	public Integer flag;
	
	/*@Column(name="total")
	public Integer total;*/
	
	//执照信息
	
//	@Column(name = "license_id")
//	public String licenseId;
//	
//	@Column(name = "license_type")
//	public String licenseType;
//	
//	@Column(name = "archives_name")
//	public String archivesName;
	
}