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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "airport_map")
public class AirportMap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "airport_id")
	private Long airportId;

	@Column(name = "info_map")
	private String infoMap;// 机场信息图

	@Column(name = "plane_map")
	private String planeMap;// 机场平面图

	@Column(name = "oil_map")
	private String oilMap;// 放油图路劲

	@Column(name = "parking_map1")
	private String parkingMap1;// 停机位图1路劲

	@Column(name = "parking_map2")
	private String parkingMap2;// 停机位图2路劲

	@Column(name = "meter_leave_map")
	private String meterLeaveMap;// 标准仪表离场图

	@Column(name = "meter_enter_map")
	private String meterEnterMap;// 标准仪表进场图

	@Column(name = "meter_approach_map")
	private String meterApproachMap;// 标准仪表进近图

	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/** 更新时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	//0-正常，审核通过，1-删除，2-待企业管理员审核， 3-待监管人员审核 4-驳回
	private short flag;

}
