package com.atosenet.ga.company.db.model.ga.company;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name = "t_focus_company")
@Data
public class FocusCompany {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@ApiModelProperty(value = "主键id")
	private long id;

	@Column(name="sup_id")
	@ApiModelProperty(value = "监管局/管理局ID")
	private Long superId;
	
	@Column(name="company_ids")
	@ApiModelProperty(value = "企业ID")
	private String companyIds;

}
