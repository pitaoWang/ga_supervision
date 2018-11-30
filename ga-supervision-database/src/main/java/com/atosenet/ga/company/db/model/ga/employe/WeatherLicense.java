package com.atosenet.ga.company.db.model.ga.employe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@Table(name = "weather_license")
public class WeatherLicense {
	/** 主键ID */
	@Id
	@Column(name = "id")
	public long id;
	
	/** 气象观测资格有效性 */
	@Column(name = "obser_effect")
	@ApiModelProperty("气象观测资格有效性")
	public java.lang.String obsereffect;
	
	/** 气象预测资格有效性 */
	@Column(name = "forecast_effect")
	@ApiModelProperty("气象预测资格有效性")
	public java.lang.String forecasteffect;
	
	/** 自动气象观测设备保障资格有效性 */
	@Column(name = "obser_equip_effect")
	@ApiModelProperty("自动气象观测设备保障资格有效性")
	public java.lang.String obserequipeffect;
	
	/** 气象雷达设备保障资格有效性 */
	@Column(name = "radar_equip_effect")
	@ApiModelProperty("气象雷达设备保障资格有效性")
	public java.lang.String radarequipeffect;
	
	/** 气象信息系统设备保障资格有效性 */
	@Column(name = "info_equip_effect")
	@ApiModelProperty("气象信息系统设备保障资格有效性")
	public java.lang.String infoequipeffect;
	
	/** 气象执照适用地区（华北，东北，华东，中南，西南，西北，新疆） */
	@Column(name = "license_area")
	@ApiModelProperty("气象执照适用地区（华北，东北，华东，中南，西南，西北，新疆）")
	public java.lang.String licensearea;
	
	/**
	 * 专业类型
	 */
	@Column(name="profession_type")
	@ApiModelProperty("专业类型")
	public String majorType;

}
