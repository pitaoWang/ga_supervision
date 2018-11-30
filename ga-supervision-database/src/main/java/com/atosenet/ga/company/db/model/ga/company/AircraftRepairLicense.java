package com.atosenet.ga.company.db.model.ga.company;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@Table(name = "aircraft_repair_license")
public class AircraftRepairLicense {

	@Id
	private long id;
	/*
	 * 航空器维修执照颁发人
	 */
	@Column(name = "issue_user_id")
	private long issueUserId;
	/*
	 * 航空器维修一级专业名称1
	 */
	/*@Column(name = "primary_special_name")
	private String primarySpecialName;*/
	/*
	 * 航空器维修一级专业代码
	 */
	/*@Column(name = "primary_specia_code")
	private String primarySpecialCode;*/
	/*
	 * 航空器维修一级专业名称2
	 */
	/*@Column(name = "primary_special_name1")
	private String primarySpecialName1;*/
	/*
	 * 航空器维修一级专业代码2
	 */
	/*@Column(name = "primary_specia_code1")
	private String primarySpecialCode1;*/
	/*
	 * 航空器维修一级专业2签发日期
	 */
//	@Temporal(TemporalType.TIMESTAMP)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@JsonFormat(pattern = "yyyy-MM-dd")
	/*@Column(name = "primary_specia_issue_date")
	private Date primarySpecialIssueDate;*/
	/*
	 * 航空器维修一级专业2截止日期
	 */
//	@Temporal(TemporalType.TIMESTAMP)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@JsonFormat(pattern = "yyyy-MM-dd")
	/*@Column(name = "primary_specia_expire_date")
	private Date primarySpecialExpireDate;*/
	/*
	 * 航空器维修二级专业信息
	 */
	@Column(name = "second_special_info")
	@ApiModelProperty(value="维修执照的专业信息，有名称、代码、有效性、签发日期和截止日期组成的json数组")
	private String secondSpecialInfo;
	
	@Transient
	@ApiModelProperty(value="航空器维修执照机型签发")
	private List<AircraftRepairLicenseModelCheck> arlmc;
	@Transient
	@ApiModelProperty(value="航空器维修执照续签")
	private List<AircraftRepairLicenseSpecialCheck> arlsc;
	
}
