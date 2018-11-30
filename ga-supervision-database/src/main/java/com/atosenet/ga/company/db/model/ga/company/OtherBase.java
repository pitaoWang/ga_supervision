/**  
 * 
 * @Title: OtherBase.java 
 * @Package com.atosenet.ga.company.db.model.ga.company  
 * @author dgw   
 * @date 2018年8月21日 下午5:57:32 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.model.ga.company;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 
 * TODO(其他基地model)
 * 
 * @ClassName: OtherBase 
 * @author dgw 
 * @date 2018年8月21日 下午5:57:32 
 *  
 */
@Entity
@Data
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table(name = "other_base")
public class OtherBase {
	
	/**基地id**/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "base_id")
	@ApiModelProperty(value="基地id")
	private Long baseId;
	
	@Column(name = "company_id")
	@ApiModelProperty(value="企业id")
	private Long companyId;
	
	@Column(name = "base_name")
	@ApiModelProperty(value="基地名称")
	private String baseName;
	
	@Column(name = "base_type")
	@ApiModelProperty(value="基地类型(1-飞行基地  2-维修基地 3-训练基地)")
	private String baseType;
	
	@Column(name = "base_tel")
	@ApiModelProperty(value="基地电话")
	private String baseTel;
	
	@Column(name = "base_province")
	@ApiModelProperty(value="所在省")
	private String baseProvince;
	
	@Column(name = "base_city")
	@ApiModelProperty(value="所在市")
	private String baseCity;
	
	@Column(name = "base_address")
	@ApiModelProperty(value="基地详细地址")
	private String baseAddress;
}
