package com.atosenet.ga.company.db.model.ga.company;

import java.io.Serializable;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

import lombok.Data;

@Entity
@Data
@Table(name = "maintainbase")
public class MaintainBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maintainbase_id")
	public long maintainbaseid;

	/** 归属企业ID */
	@OneToOne
	@JoinColumn(name = "company_id")
	@JsonBackReference
	private GaCompany companyId;
	
	/** 维修基地名称 */
	@Column(name = "maintainbase_name")
	public String maintainbasename;

	/** 所属的航管局 ID */
	@Column(name = "authority_id")
	public long authorityid;

	/** 所属的监管局ID */
	@Column(name = "supervise_id")
	public long superviseid;

	/** 联系电话 */
	@Column(name = "telephone")
	public String telephone;

	/** 维修基地地址省 */
	@Column(name = "province")
	public String province;

	/** 维修基地地址市 */
	@Column(name = "city")
	public String city;

	/** 维修基地详细地址 */
	@Column(name = "address")
	public String address;

	/** 维修基地邮编 */
	@Column(name = "postcode")
	public String postcode;

	/** 机场id */
	@Column(name = "airport_id")
	public long airportid;

	/** 创建时间 */
	@Column(name = "createtime")
	public Date createtime;

	/** 更新时间 */
	@Column(name = "updatetime")
	public Date updatetime;

	/** 0-正常，审核通过，1-删除，2-待审核，3-驳回 */
	@Column(name = "flag")
	public int flag;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "company_maintainbase", joinColumns = { @JoinColumn(name = "company_id") }, inverseJoinColumns = {
			@JoinColumn(name = "maintainbase_id") })
	@JsonIgnore
	private List<GaCompany> gaCompany = Lists.newArrayList();

	@Transient
	private Long gaCompanyId;
}