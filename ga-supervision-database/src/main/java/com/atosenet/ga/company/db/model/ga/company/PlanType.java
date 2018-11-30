package com.atosenet.ga.company.db.model.ga.company;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * TODO(机型实体类)
 * 
 * @ClassName: PlanType 
 * @author dgw 
 * @date 2018年7月31日 下午4:23:03 
 *
 */

@Entity
@Data
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table(name = "plan_type")
public class PlanType {
	
	/**机型ID**/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long id;
	
	/**企业ID**/
	
	@Column(name = "company_id")
	public Integer companyId;
	

	@Transient
	public String companyName;
	
	/**机型编号**/
	@Column(name = "type_number")
	@ApiModelProperty(value="机型编号")
	private String  typeNumber;
	
	/**机型名称**/
	@Column(name = "type_name")
	@ApiModelProperty(value="机型名称")
	private String typeName;
	
	/**飞行器类型**/
	@Column(name = "aircraft_type")
	@ApiModelProperty(value="飞行器类型：飞机、直升机、自转旋翼机、倾转旋翼机、飞艇、气球、滑翔机、无人机")
	private String aircraftType;
	
	/**飞行器级别**/
	@Column(name = "aircraft_level")
	@ApiModelProperty(value="飞行器级别：固定翼、旋翼、滑翔机、其他")
	private String aircraftLevel;
	
	/**飞行器型别**/
	@Column(name = "aircraft_model")
	@ApiModelProperty(value="飞行器型别：5700千克以上的航空器、3180千克以上的航空器、涡轮喷气动力飞机、其他航空器")
	private String aircraftModel;
	
	/**制造厂家**/
	@Column(name = "manufacturer")
	@ApiModelProperty(value="制造厂家")
	private String manufacturer;
	
	/**最大起飞重量**/
	@Column(name = "max_fly_weight")
	@ApiModelProperty(value="最大起飞重量")
	private double maxFlyWeight;
	
	/**最大起飞速度**/
	@Column(name = "max_fly_speed")
	@ApiModelProperty(value="最大起飞速度")
	private double maxFlySpeed;
	
	/**油耗**/
	@ApiModelProperty(value="油耗")
	@Column(name = "fuel_consumption")
	private String fuelConsumption;
	
	/**理论驾驶员数量**/
	@Column(name = "driver_number")
	@ApiModelProperty(value="理论驾驶员数量")
	private String driverNumber;
	
	/**座位数量**/
	@Column(name = "site_number")
	@ApiModelProperty(value="座位数量")
	private String siteNumber;
	
	/**是否具有仪表飞行能力**/
	@Column(name = "meter_flag")
	@ApiModelProperty(value="是否具有仪表飞行能力：0否 1是")
	private int meterFlag;
	
	/**是否复杂飞机**/
	@Column(name = "complex_flag")
	@ApiModelProperty(value="是否复杂飞机：0否 1是")
	private int complexFlag;
	
	/**是否高性能**/
	@Column(name = "performance_flag")
	@ApiModelProperty(value="是否高性能：0否 1是")
	private int performanceFlag;
	
	/**是否实用类**/
	@Column(name = "practical_flag")
	@ApiModelProperty(value="是否实用类：0否 1是")
	private int practicalFlag;
	
	/**是否特技类**/
	@Column(name = "trick_flag")
	@ApiModelProperty(value="是否特技类：0否 1是")
	private int  trickFlag;
	
	/**是否有hobbs表**/
	@Column(name = "hobbs_flag")
	@ApiModelProperty(value="是否有hobbs表：0否 1是")
	private int hobbsFlag;
	
	/**是否存在FTD**/
	@Column(name = "ftd_flag")
	@ApiModelProperty(value="是否存在FTD：0否 1是")
	private int ftdFlag;
	
	/**是否存在FPS**/
	@Column(name = "fps_flag")
	@ApiModelProperty(value="是否存在FPS：0否 1是")
	private int fpsFlag;
	
	/**
	 * 最大飞行距离
	 */
	@Column(name = "max_fly")
	@ApiModelProperty(value="最大飞行距离")
	private Double maxFly;
	
	/**
	 * 最大飞行距离
	 */
	@Column(name = "width")
	@ApiModelProperty(value="宽度")
	private Double width;
	
	/**
	 * 最大飞行距离
	 */
	@Column(name = "length")
	@ApiModelProperty(value="长度")
	private Double length;
	
	
	/**创建时间**/
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "createtime")
	private Date createtime;
	
	/**修改时间**/
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "updatetime")
	private Date updatetime;
}
