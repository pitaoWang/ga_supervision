/**
 * 
 */
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

import com.atosenet.ga.company.db.utils.ExcelData;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lvhao
 *
 */
@Entity
@Data
@Table(name="simulator")
public class Simulator implements ExcelData{
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	/**
	 * 公司id
	 */
	@Column(name="company_id")
	@ApiModelProperty(value="公司id")
	private Long companyId;
	
	/**
	 * 监管局id
	 */
	@Column(name="super_id")
	@ApiModelProperty(value="监管局id")
	private Long superId;
	
	/**
	 * 管理局id
	 */
	@Column(name="manager_id")
	@ApiModelProperty(value="管理局id")
	private Long manageId;
	
	/**
	 * 设备名称
	 */
	@Column(name="equipment_name")
	@ApiModelProperty(value="设备名称")
	private String equipmentName;
	
	/**
	 * 合格证编号
	 */
	@Column(name="certificate_number")
	@ApiModelProperty(value="合格证编号")
	private String certificateNum;
	
	/**
	 * 合格证有效期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="certificate_validity_period")
	@ApiModelProperty(value="合格证有效期")
	private Date certificateValidityPeriod;
	
	/**
	 *合格证扫描件
	 */
	@Column(name="certificate_file")
	@ApiModelProperty(value="合格证扫描件")
	private String certificateFile;
	
	/**
	 * 设备类型
	 */
	@Column(name="equipment_type")
	@ApiModelProperty(value="设备类型：飞行训练器、飞行模拟器")
	private String equipmentType;
	
	/**
	 * 设备型号
	 */
	@Column(name="equipment_model")
	@ApiModelProperty(value="设备型号")
	private String equipmentModel;
	
	/**
	 * 生产序列号
	 */
	@Column(name="production_sn")
	@ApiModelProperty(value="生产序列号")
	private String productionSn;
	
	/**
	 * 等级
	 */
	@Column(name="level")
	@ApiModelProperty(value="等级：飞行训练器等级（1至7级）、飞行模拟器等级（A~D级）")
	private String level;
	
	/**
	 * 设备安装地点
	 */
	@Column(name="installation_site")
	@ApiModelProperty(value="设备安装地点")
	private String installationSite;
	
	/**
	 * 设备制造厂家
	 */
	@Column(name="equipment_manufacturer")
	@ApiModelProperty(value="设备制造厂家")
	private String equipmentManufacturer;
	
	/**
	 * 设备制造日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="equipment_product_date")
	@ApiModelProperty(value="设备制造日期")
	private Date equipmentProductDate;
	
	/**
	 * 初始鉴定日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="identification_date")
	@ApiModelProperty(value="初始鉴定日期")
	private Date identificationDate;
	
	/**
	 * 模拟器名称
	 */
	@Column(name="name")
	@ApiModelProperty(value="模拟器名称")
	private String name;
	
	/**
	 * 引进日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="come_date")
	@ApiModelProperty(value="引进日期")
	private Date comeDate;
	
	/**
	 * 基地
	 */
	@Column(name="base_id")
	@ApiModelProperty(value="基地")
	private Long baseId;
	
	/**
	 * 基地名称
	 */
	@Column(name="base_name")
	@ApiModelProperty(value="基地名称")
	private String baseName;
	
	/**
	 * 次数
	 */
	@Column(name="count")
	@ApiModelProperty(value="次数")
	private Long count;
	
	/**
	 * 视景系统制造厂家
	 */
	@Column(name="vision_system_manufacturer")
	@ApiModelProperty(value="视景系统制造厂家")
	private String visionSystemManufacturer;
	
	/**
	 * 视景系统显示类型
	 */
	@Column(name="vision_system_type")
	@ApiModelProperty(value="视景系统显示类型")
	private String visionSystemType;
	
	/**
	 *视景系统型号
	 */
	@Column(name="vision_system_model")
	@ApiModelProperty(value="视景系统型号")
	private String visionSystemModel;
	
	/**
	 * 运行系统型号
	 */
	@Column(name="sport_system_model")
	@ApiModelProperty(value="运行系统型号")
	private String sportSystemModel;
	
	/**
	 * 运动系统制造厂家
	 */
	@Column(name="sprot_system_manufacturer")
	@ApiModelProperty(value="运动系统制造厂家")
	private String sprotSystemManufacturer;
	
	/**
	 * 运动系统自由度
	 */
	@Column(name="sprot_system_free")
	@ApiModelProperty(value="运动系统自由度")
	private String sprotSystemFree;
	
	/**
	 * 附加设备和功能
	 */
	@Column(name="additional_features")
	@ApiModelProperty(value="附加设备和功能")
	private String additionalFeatures;
	
	/**
	 * 设备照片
	 */
	@Column(name="photos")
	@ApiModelProperty(value="设备照片")
	private String photos;
	
	/**
	 * 模拟航空器型号
	 */
	@Column(name="aircraft_model")
	@ApiModelProperty(value="模拟航空器型号")
	private String aircraftModel;
	
	/**
	 * 空气动力数据修订号
	 */
	@Column(name="air_amendment_nuber")
	@ApiModelProperty(value="空气动力数据修订号")
	private String airAmendmentNumber;
	
	/**
	 * 空气动力数据出处
	 */
	@Column(name="air_data_source")
	@ApiModelProperty(value="空气动力数据出处")
	private String airDataSource;
	
	/**
	 *飞行操纵数据修订号 
	 */
	@Column(name="flight_amendment_nuber")
	@ApiModelProperty(value="飞行操纵数据修订号 ")
	private String flightAmendmentNuber;
	
	/**
	 * 飞行器数据出处
	 */
	@Column(name="flight_data_source")
	@ApiModelProperty(value="飞行器数据出处 ")
	private String flightDataSource; 
	
	/**
	 * 飞行管理系统标识
	 */
	@Column(name="flight_manager_flag")
	@ApiModelProperty(value="飞行管理系统标识 ")
	private String flightManagerFlag;
	
	/**
	 * 飞行管理系统修订版本
	 */
	@Column(name="system_version")
	@ApiModelProperty(value="飞行管理系统修订版本")
	private String systemVersion;
	
	/**
	 * 飞行模拟设备计算机标识
	 */
	@Column(name="computer_flag")
	@ApiModelProperty(value="飞行模拟设备计算机标识")
	private String computerFlag;
	
	/**
	 * 训练附加要求
	 */
	@Column(name="training_requirements")
	@ApiModelProperty(value="训练附加要求")
	private String trainingRequirements;
	
	/**
	 * 鉴定测试指南
	 */
	@Column(name="test_guide")
	@ApiModelProperty(value="鉴定测试指南")
	private String testGuide;
	
	/**
	 * 模拟航空器制造厂家
	 */
	@Column(name="aircraft_manufacturer")
	@ApiModelProperty(value="模拟航空器制造厂家")
	private String aircraftManufacturer;
	
	/**
	 * 使用说明
	 */
	@Column(name="user_guide")
	@ApiModelProperty(value="使用说明")
	private String userGuide;
	
	/**
	 * 运行总时间
	 */
	@Column(name="total_time")
	@ApiModelProperty(value="运行总时间")
	private Double totalTime;
	
	/**
	 *设备状态
	 */
	@Column(name="equipment_status")
	@ApiModelProperty(value="设备状态")
	private String equipmentStatus;
	
	/**
	 * 创建时间
	 */
	@Column(name="createtime")
	private Date createtime;
	
	/**
	 * 修改时间
	 */
	@Column(name="updatetime")
	private Date updatetime;
	
	/**
	 * 监管局
	 */
	@Transient
	@ApiModelProperty(value="监管局")
	private String superName;
	
	/**
	 * 管理局
	 */
	@Transient
	@ApiModelProperty(value="管理局")
	private String manageName;

	/**
	 * (non-Javadoc)
	 * <p>Title: getFieldTitles</p> 
	 * <p>Description:字段中文名 </p> 
	 * @return 
	 * @see com.atosenet.ga.company.db.utils.ExcelData#getFieldTitles()
	 * 
	 */
	@Override
	public String[] getFieldTitles() {
		// TODO Auto-generated method stub
		return new String[]{"模拟训练器id","所属企业ID","所属监管局ID","所属管理局ID","设备名称","合格证编号",
				"合格证有效期","合格证扫描件","设备型号","设备类型","生产序列号","等级","设备安装地点","设备制造厂家",
				"设备制作日期","初始鉴定日期","视景系统制造厂家","视景系统显示类型","视景系统型号","运动系统型号","运动系统制造厂家",
				"运动系统自由度","附加设备和功能","设备照片","模拟航空器型号","空气动力数据修订号","空气动力数据出处","飞行操纵数据修订号",
				"飞行操纵数据出处","飞行管理系统标识","飞行管理系统修订版本","飞行模拟设备计算机标识","训练附加要求","鉴定测试指南",
				"模拟航空器制造厂家","使用声明","运行总时间","设备状态","创建时间","修改时间","监管局","管理局"};
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: getFieldNames</p> 
	 * <p>Description: 字段名</p> 
	 * @return 
	 * @see com.atosenet.ga.company.db.utils.ExcelData#getFieldNames()
	 * 
	 */
	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return new String[]{"id","companyId","superId","manageId","equipmentName",
				"certificateNum","certificateValidityPeriod",
				"certificateFile","equipmentType","equipmentModel",
				"productionSn","level","installationSite","equipmentManufacturer",
				"equipmentProductDate","identificationDate","visionSystemManufacturer",
				"visionSystemType","visionSystemModel","sportSystemModel",
				"sprotSystemManufacturer","sprotSystemFree","additionalFeatures",
				"photos","aircraftModel","airAmendmentNumber","airDataSource",
				"flightAmendmentNuber","flightDataSource","flightManagerFlag",
				"systemVersion","computerFlag","trainingRequirements","testGuide",
				"aircraftManufacturer","userGuide","totalTime","equipmentStatus",
				"createtime","updatetime","superName","manageName"};
	}
	
}
