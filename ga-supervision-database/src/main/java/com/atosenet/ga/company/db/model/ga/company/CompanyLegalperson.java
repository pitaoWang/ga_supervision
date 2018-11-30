package com.atosenet.ga.company.db.model.ga.company;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "company_legalperson")
@Data
public class CompanyLegalperson implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "legalperson_id")
	private long id;// 法人ID

	@Column(name="cardid")
	private String cardid;// 法人身份证
	
	@Column(name="legalperson_name")
	private String legalpersonName;// 法人姓名
	
	@Column(name="gender")
	private String gender;// 性别
	
	@Column(name="telephone")
	private String telephone;// 联系电话
	
	@Column(name="email")
	private String email;// 电子邮箱
	
	@Column(name="company_address")
	private String companyAddress;// 企业地址
	

	@Column(name="company_id")
	private Integer companyId;// 公司id
}
