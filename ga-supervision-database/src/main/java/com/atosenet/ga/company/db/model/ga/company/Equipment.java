/**
 * 
 */
package com.atosenet.ga.company.db.model.ga.company;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lvhao
 *
 */
@Entity
@Data
@Table(name="equipment")
public class Equipment {
	
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	/**
	 * 组织id
	 */
	@Column(name="company_id")
	private Long companyId;
	
	/**
	 * 航空器注册号
	 */
	@Column(name="reg_number")
	@ApiModelProperty(value="航空器注册号")
	private String regNumber;
	
	/**
	 * 企业name
	 */
	@Transient
	@ApiModelProperty(value="企业name")
	private String companyName;
	/**
	 * 设备类型
	 */
	@Column(name="ep_type")
	@ApiModelProperty(value="设备类型")
	private String epType;
	
	/**
	 * 设备号
	 */
	@Column(name="ep_num")
	@ApiModelProperty(value=" 设备号")
	private String epNum;
}
