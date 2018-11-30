package com.atosenet.ga.company.db.model.ga.company;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 航空器部件维修执照
 * @author gfound
 *
 */
@Entity
@Table(name="aircraft_parts_repair_license")
@Data
public class AircraftPartsRepairLicense {

	@Id
	private long id;
	/*执照颁发人的ID*/
	@Column(name="issue_user_id")
	
	private long issueUserId;
	/*维修执照的专业信息，有名称、代码、有效性、签发日期和截止日期组成的json数组*/
	
	@ApiModelProperty("维修执照的专业信息，有名称、代码、有效性、签发日期和截止日期组成的json数组")
	@Column(name="repair_special_info")
	private String repairSpecialInfo;
	
	@Transient
	@ApiModelProperty(value="执照项目签署记录")
	private List<AircraftPartsRepairLicenseProjectCheck> aprlpc;//执照项目签署记录
	
	@Transient
	@ApiModelProperty(value="专业续签记录")
	private List<AircraftPartsRepairLicenseSpecialCheck> aprlsc;//专业续签记录
}
