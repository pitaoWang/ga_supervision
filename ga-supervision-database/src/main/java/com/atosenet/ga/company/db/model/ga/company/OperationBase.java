/**
 * 
 */
package com.atosenet.ga.company.db.model.ga.company;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lvhao
 *
 */

@Entity
@Data
@Table(name = "operating_base")
public class OperationBase implements Serializable{
	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="base_id")
	@ApiModelProperty(value="运行基地id")
	private long baseId;
	
	/**
	 * 所属企业
	 */
	@Column(name="company_id")
	@ApiModelProperty(value="所属企业id")
	private long companyId;
	
	/**
	 * 机场
	 */
	@Column(name="fly_id")
	@ApiModelProperty(value="机场Id")
	private Long flyId;
	
	/**
	 * 机场名字
	 */
	@Column(name="fly_name")
	@ApiModelProperty(value="机场")
	private String flyName;
	
	/**
	 * 基地名称
	 */
	@Column(name= "base_name")
	@ApiModelProperty(value="基地名称")
	private String baseName;
	
	/**
	 * 基地类型
	 */
	@Column(name="base_type")
	@ApiModelProperty(value="基地类型(1-运行基地 2-飞行基地 3-维修基地 4训练基地)")
	private String baseType;
	
	/**
	 * 所在省
	 */
	@Column(name= "flybase_province")
	@ApiModelProperty(value="所在省")
	private String provience;
	
	/**
	 * 所在市
	 */
	@Column(name="flybase_city")
	@ApiModelProperty(value="所在市")
	private String city;
	
	/**
	 * 详细地址
	 */
	@Column(name="flybase_detailed_adress")
	@ApiModelProperty(value="详细地址")
	private String address;
	
	/**
	 * 基地电话
	 */
	@Column(name="flybase_tel")
	@ApiModelProperty(value="基地电话")
	private String tel;
	
	/**
	 * 创建时间
	 */
	/*@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")*/
	@Column(name="create_time")
	@ApiModelProperty(value="创建时间")
	private Date createTime;
	
	@Column(name = "is_mainbase")
	@ApiModelProperty(value = "是否主基地 1-是 2否")
	private Integer isMainbase;
	

}
