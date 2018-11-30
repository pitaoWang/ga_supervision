package com.atosenet.ga.company.db.model.ga.company;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "company_maintainbase")
@NoArgsConstructor
@AllArgsConstructor
public class GaMatianBaseRefere implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long id;

	/** 企业id */
	@Column(name = "company_id")
	public String companyid;

	/** 维修基地id */
	@Column(name = "maintainbase_id")
	public long maintainbaseid;

	

//	//公司拥有的所有的机场
//		@ManyToMany(fetch = FetchType.LAZY)
//		@JoinTable(name = "ga_company", joinColumns = { @JoinColumn(name = "maintainbase_id") }, inverseJoinColumns = { @JoinColumn(name = "company_id") })
//		private List<GaCompany> gacompany = Lists.newArrayList();
}