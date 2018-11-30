package com.atosenet.ga.company.db.model.ga.employe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@Table(name = "intelligence_license")
public class IntelligenceLicense {
	
	/** 情报执照信息ID */
	@Id
	@Column(name = "id")
	public long id;
	
	/** 情报执照颁发单位 */
	@Column(name = "issue_unit")
	@ApiModelProperty("情报执照颁发单位")
	public java.lang.String issueUnit;
	
	/** 情报执照适用地区（华北，东北，华东，中南，西南，西北，新疆） */
	@Column(name = "license_area")
	@ApiModelProperty("情报执照适用地区（华北，东北，华东，中南，西南，西北，新疆）")
	public java.lang.String licenseArea;
}
