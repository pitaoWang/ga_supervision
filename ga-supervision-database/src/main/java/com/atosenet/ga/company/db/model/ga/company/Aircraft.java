package com.atosenet.ga.company.db.model.ga.company;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.atosenet.ga.company.db.utils.ExcelData;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 航空器
 * @Description:TODO
 * @author:瞿旺伟
 * @time:2017年8月31日 下午8:59:49
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name="aircraft")
public class Aircraft implements Serializable,ExcelData{

   private static final long serialVersionUID = 1L;
	
   /** 航空器ID */
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="aircraft_id")
   @ApiModelProperty(value="航空器ID")
   private Long aircraftId;
   
   /**机型id**/
   @Column(name = "plan_type_id")
   @ApiModelProperty(value="机型id")
   private Long planTypeId;
  
   /**删除**/
   @Column(name = "del_flag")
   @ApiModelProperty(value="机型id")
   private int delflag;
   /** 归属企业ID */
  
   @Column(name = "company_id")
   @ApiModelProperty(value="归属企业ID")
   private Integer companyId;
   
   /**注册号**/
   @Column(name = "reg_number")
   @ApiModelProperty(value="注册号")
   private String regNumber;
   
   /**监管局id**/
   @Column(name = "super_id")
   @ApiModelProperty(value="监管局id")
   private Long superId;
   
   /**管理局id**/
   @Column(name = "manager_id")
   @ApiModelProperty(value="管理局id")
   private Long managerId;
   
   /** 归属企业名称 */
  
   @Column(name="company_name")
   @ApiModelProperty(value="归属企业名称")
   private String companyName;
   
   /** 航空器状态 */
   @Column(name="aircraft_status")
   @ApiModelProperty(value="航空器状态：在用、大修、报停、报废、维护")
   private String aircraftStatus;
 
   /** 航空器拥有形式 */
   @Column(name="aircraft_model")
   @ApiModelProperty(value="航空器拥有形式：执管、租赁、借用")
   private String aircraftModel;

   
   /**引进时间**/
   @Column(name = "introduction_time")
   @ApiModelProperty(value="引进时间")
   private Date introductionTime;
   /**国际登记证编号**/  
   @Column(name="registernumber")
   @ApiModelProperty(value="国际登记证编号")
   private String registernumber; 
   /** 国籍登记证扫描件目录 */
   @Column(name="scan_catalogue")
   @ApiModelProperty(value="国籍登记证扫描件目录")
   private String scanCatalogue;
   
   /** 适航证编号 */
   @Column(name="airworthiness_certificate_number")
   @ApiModelProperty(value="适航证编号")
   private String airworthinessCertificateNumber;
   
   /** 适航证 */
   @Column(name="airworthiness_certificate")
   @ApiModelProperty(value="适航证")
   private String airworthinessCertificate;

   /** 航空器照片  */
   @Column(name="aircraft_catalogue")
   @ApiModelProperty(value="航空器照片")
   private String aircraftCatalogue;
   
   /** 电台执照编号 */
   @Column(name="radio_licence_number")
   @ApiModelProperty(value="电台执照编号")
   private String radioLicenceNumber;
   
 
   /** 电台执照扫描件目录 */
   @Column(name="radio_licence_catalogue")
   @ApiModelProperty(value="电台执照扫描件目录")
   private String radioLicenceCatalogue;
 
   /**电台执照失效期**/
   @Column(name = "radio_licence_invalid_date")
   @ApiModelProperty(value="电台执照失效期")
   private Date radioLicenceInvalidDate;
   
   /** 航空器出厂日期 */
   @Column(name="production_date")
   @ApiModelProperty(value="航空器出厂日期")
   private Date productionDate;
   
   /** 出厂序列号 */
   @Column(name="airframeid")
   @ApiModelProperty(value="出厂序列号")
   private String airframeid;
   
   /** "所属机型"*/
	@Transient
	@ApiModelProperty(value="所属机型")
	public java.lang.String planTypeName;
	
	/** "所属机型"*/
	@Transient
	@ApiModelProperty(value="所属机型编号")
	public java.lang.String typeNumber;
	/** 所属地区管理局*/
	@Transient
	@ApiModelProperty(value="所属地区管理局")
	public String authorityName;
	/** 所属监管局*/
	@Transient
	@ApiModelProperty(value="所属监管局")
	public String supervisionName;
   /** 飞行总时长，单位为妙，每次录入飞行记录时自动更新该记录 */
   @Column(name="fly_sum_time")
   @ApiModelProperty(value="飞行总时长")
   private long flySumTime;
   /** 起落次数 */
   @Column(name="up_down_times")
   @ApiModelProperty(value="起落次数")
   private long upDownTimes;
   /** 产权人 */
   @Column(name="property_owner")
   @ApiModelProperty(value="产权人")
   private String propertyOwner;
   
   /** 运行区域 */
   @Column(name="operate_area")
   @ApiModelProperty(value="运行区域")
   private String operateArea;
   
   /** 运行种类 */
   @Column(name="operate_type")
   @ApiModelProperty(value="运行种类")
   private String operateType;
   
   /** 基地 */
   @Column(name="base_id")
   @ApiModelProperty(value="基地")
   private Long baseId;
   
   /** 基地名称 */
   @Column(name="base_name")
   @ApiModelProperty(value="基地名称")
   private String baseName;
   /** 创建时间 */
   @Column(name = "createtime")
   @CreatedDate
   private Date createtime;
   
   /** 更新时间 */
   @Column(name = "updatetime")
   @LastModifiedDate
   private Date updatetime;
   
   /** 0-正常，审核通过，1-删除，2-待审核，3-驳回 */
   @Transient
   private int flag;
   @Transient
   @ApiModelProperty(value="分页总条数")
	public Long total;
/**
 * (non-Javadoc)
 * <p>Title: getFieldTitles</p> 
 * <p>Description: </p> 
 * @return 
 * @see com.atosenet.ga.company.db.utils.ExcelData#getFieldTitles()
 * 
 */
@Override
public String[] getFieldTitles() {
	String[] Titles = {"机型编号","所属单位","航空器注册号","航空器序列号","航空器状态","航空器拥有形式",
			"出厂时间","产权人","航空器引进时间","国籍登记证编号","适航证编号","电台执照编号","电台执照失效日期","飞行时间",
			"起落次数","运行区域","运行种类"};
	return Titles;
}

/**
 * (non-Javadoc)
 * <p>Title: getFieldNames</p> 
 * <p>Description: </p> 
 * @return 
 * @see com.atosenet.ga.company.db.utils.ExcelData#getFieldNames()
 * 
 */
@Override
public String[] getFieldNames() {
	String[] Name = {"typeNumber","companyName","regNumber","airframeid",
			"aircraftStatus","aircraftModel","productionDate","propertyOwner","introductionTime","registernumber",
			"airworthinessCertificateNumber","radioLicenceNumber","radioLicenceInvalidDate","flySumTime","upDownTimes"
			,"operateArea","operateType"};
	return Name;
}

}