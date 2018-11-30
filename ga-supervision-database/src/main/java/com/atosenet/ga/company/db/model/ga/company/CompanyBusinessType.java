package com.atosenet.ga.company.db.model.ga.company;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

/**
 * @author 熊江涛
 *
 */
@Entity
@Table(name = "company_business_type")
@Data
public class CompanyBusinessType implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -3725302499230139686L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
//	@OneToOne
//	@JoinColumn(name = "company_id")
//	@JsonIgnore
	@Column(name="company_Id")
	private int companyId;
	
	@Column(name="authority_id")
	private long authorityId;
	
	@Column(name="supervision_id")
	private long supervisionId;
	@Column(name="business_type")
	private short businessType;
	
}
