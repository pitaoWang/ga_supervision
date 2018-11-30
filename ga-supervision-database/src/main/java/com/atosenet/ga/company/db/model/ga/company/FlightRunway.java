package com.atosenet.ga.company.db.model.ga.company;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
/**
 * 跑道信息
 * @Description:TODO
 * @author:瞿旺伟
 * @time:2017年8月31日 下午8:01:57
 */
@Entity
@Table(name="flight_runway")
@Data
public class FlightRunway implements Serializable{
	private static final long serialVersionUID = 1L;
	  /** 跑道ID */
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id;
	   /** 机场ID */
	   @ManyToOne
	   @JoinColumn(name="airdrome_id")
	   private Airport airdromeId;
	   
	   /** 跑道编号 */
	   @Column(name="runway_no")
	   private String runwayNo;
	   
	   /** 跑道运行类别 */
	   @Column(name="runway_type")
	   private String runwayType;
	   
	   /** 跑道长度 */
	   @Column(name="length")
	   private String runwayLength;
	   
	   /** 中心点经度 */
	   private String longitude;
	   
	   /** 中心点纬度 */
	   private String latitude;
	   
	   /** 中心点高度 */
	   private String height;
	   
	   /** 跑道仪表着陆设备是否具备 */
	   @Column(name="islanding_device")
	   private Integer islandingDevice;
	   
	   /** ILS频率 */
	   private String ils;
	   
	   /** 助航灯光是否具备 */
	   private Integer islamplight;
	   
	   /** 是否可供固定翼飞机起降 */
	   @Column(name="isfixed_aerofoil")
	   private Integer isfixedAerofoil;
	   
	   /** 是否可供直升机起降 */
	   private Integer ishelicopter;
	   
	   @Column(name="isavailable")
	   private short  isavailable;/* 是否可用0不可用,1可用*/
	   
	   @Column(name="direction")
	   private String  direction;/* 跑道朝向，127度*/
	   
	   /** 创建时间 */
	   @Temporal(TemporalType.TIMESTAMP)
	   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	   private Date createtime;
	   
	   @Temporal(TemporalType.TIMESTAMP)
	   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	   private Date updatetime;
	   
	   /** 0-正常，审核通过，1-删除，2-待审核，3-驳回 */
	   private Integer flag; 
}
