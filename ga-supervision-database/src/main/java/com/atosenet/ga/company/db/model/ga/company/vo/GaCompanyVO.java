package com.atosenet.ga.company.db.model.ga.company.vo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Entity
@Data
public class GaCompanyVO {

	/** 通用航空企业ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="company_id")
	private int companyId;
	
	/** 组织ID */
	@Column(name="orgid")
	private int orgId;
	/** 通航企业名称 */
	@Length(max = 100)
	@Column(name="name")
	private String name;
	/** 企业法人ID */
	@Column(name="userid")
	private int userId;
	/** 对应的管理局ID */
	@Column(name="authority_id")
	private Integer authorityId;
	/** 对应监管局ID */
	@Column(name="supervision_id")
	private Integer supervisionId;

}