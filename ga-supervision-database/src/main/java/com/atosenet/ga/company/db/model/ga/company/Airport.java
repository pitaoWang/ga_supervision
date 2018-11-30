package com.atosenet.ga.company.db.model.ga.company;

import java.io.Serializable;
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

import lombok.Data;

/** 
 * 机场信息
 *  */
@Entity
@Data
@Table(name="airport")
public class Airport implements Serializable{
	private static final long serialVersionUID = 1L;

	  /** 机场ID */
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   @Column(name="airport_id")
	   private long airportId;
	   
	   /** 机场名称 */
	   @Column(name="airport_name")
	   private String airportName;
	   
	   /** 归属企业ID */
	   @Column(name="company_id")
	   private int companyId;
	   /** 机场等级 */
	   @Column(name="grade")
	   private String grade;
	   
	   /** 是否水上机场 */
	   @Column(name="iswater")
	   private Integer iswater;
	   
	   /** 所在省 */
	   @Column(name="province")
	   private String province;
	   
	   /** 所在城市 */
	   @Column(name="city")
	   private String city;
	   
	   @Column(name="type")
	   private String airporttype;/*机场类型*/
	 
	   @Column(name="isavailable")
	   private short  isavailable;/* 是否可用0可用,1不可用*/
		@Column(name = "fire_grade")
		private String fireGrade;/* 消防等级*/

//		@Temporal(TemporalType.TIMESTAMP)
//		@DateTimeFormat(pattern = "yyyy-MM-dd")
//		@JsonFormat(pattern = "yyyy-MM-dd")
	    @Column(name = "license_begindate")
	    private java.sql.Date licenseBegindate;/*许可证起始日期*/
	    
//		@Temporal(TemporalType.TIMESTAMP)
//		@DateTimeFormat(pattern = "yyyy-MM-dd")
//		@JsonFormat(pattern = "yyyy-MM-dd")
	    @Column(name = "license_enddate")
	    private java.sql.Date licenseEnddate;/*许可证截止日期*/
	   
	   /** 机场基准点经度 */
	   @Column(name="longitude")
	   private String longitude;
	   
	   /** 机场基准点纬度 */
	   @Column(name="latitude")
	   private String latitude;
	   
	   /** 机场使用性质 */
	   @Column(name="property")
	   private String property;
	   
	   /** 机场标高 */
	   @Column(name="height")
	   private float height;
	   
	   /** 机场运营公司编号 */
	   @Column(name="ent_number")
	   private String entNumber;
	   
	   /** 机场管理机构名称 */
	   @Column(name="org_name")
	   private String orgName;
	   
	   /** ICAO编码 */
	   @Column(name="icao_number")
	   private String icaoNumber;
	   
	   /** IATA编码 */
	   @Column(name="iatanumber")
	   private String iatanumber;
	   
	   /** 机场地址 */
	   @Column(name="address")
	   private String address;
	   
	   /** 机场服务电话 */
	   @Column(name="service_tel")
	   private String serviceTel;
	   
	   /** 站调联系电话 */
	   @Column(name="tel")
	   private String tel;
	   
	   /** 隶属监管局ID */
	   @Column(name="supervise_orgid")
	   private long superviseOrgid;
	   /** 所属的管理局ID */
		@Column(name = "authority_id")
		private Long authorityId;
	   /** 隶属于战区 */
	   @Column(name="war_zone")
	   private String warZone;
	   
	   /** 塔台主频率 */
	   @Column(name="tower_main_frequency")
	   private String towerMainFrequency;
	   
	   /** 塔台备频率 */
	   @Column(name="tower_reserve_frequency")
	   private String towerReserveFrequency;
	   
	   /** 进近主频率 */
	   @Column(name="main_frequency")
	   private String mainFrequency;
	   
	   /** 进近备频率 */
	   @Column(name="reserve_frequency")
	   private String reserveFrequency;
	   
	   /** 仪表着陆设备是否具备 */
	   @Column(name="islanding_device")
	   private Integer islandingDevice;
	   
	   
	   /** 导航台NDB频率 */
	   @Column(name="ndb_frequency")
	   private String ndbFrequency;
	   
	   /** 导航台VOR/DME频率 */
	   @Column(name="vor_dem_frequency")
	   private String vorDemFrequency;
	   
	   /** 创建时间 */
	   @Temporal(TemporalType.TIMESTAMP)
	   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	   private Date createtime;
	   
	   /** 更新时间 */
	   @Temporal(TemporalType.TIMESTAMP)
	   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	   private Date updatetime;
	   /** 0-正常，审核通过，1-删除，2-待审核，3-驳回 */
	   private int flag;
		/**
		 * 用于接收使用机场的公司Id
		 */
		@Transient
		private Long gaCompanyId;
}
