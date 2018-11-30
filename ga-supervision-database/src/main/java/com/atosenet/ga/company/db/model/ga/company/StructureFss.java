package com.atosenet.ga.company.db.model.ga.company;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author dgw
 *
 */
@Entity
@Data
@Table(name = "structure_fss")
public class StructureFss {
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "code")
	@ApiModelProperty(value = "飞行服务站编号")
	private String code;
	
	@Column(name = "name")
	@ApiModelProperty(value = "飞行服务站名称")
	private String name;
	
	@Column(name = "type")
	@ApiModelProperty(value = "飞行服务站类型  一类、二类、三类")
	private String type;
	
	@Column(name = "faxNo")
	@ApiModelProperty(value = "飞行服务站传真号")
	private String faxNo;
	
	@Column(name = "administrationId")
	@ApiModelProperty(value = "飞行服务站所属管理局")
	private Long administrationId;
	
	@Column(name = "supervisionBureauId")
	@ApiModelProperty(value = "飞行服务站所属监管局")
	private Long supervisionBureauId;
	
	@Column(name = "file")
	@ApiModelProperty(value = "附件")
	private String file;
	
	@Column(name = "atmbId")
	private Integer atmbId;
	
	@Column(name = "contacts")
	@ApiModelProperty(value = "联系人")
	private String contacts;
	
	@Column(name = "contactsTel")
	@ApiModelProperty(value = "联系方式")
	private String contactsTel;
	
	@Column(name = "address")
	@ApiModelProperty(value = "飞行服务站地址")
	private String address;
	
	@Column(name = "airspaceShape")
	@ApiModelProperty(value = "管辖空域形状。可以为圆形，多边形。省市行政边界")
	private String airspaceShape;
	
	@Column(name = "pointsDecimal")
	@ApiModelProperty(value = "空域形状经纬度点的集合，存小数点格式，多个用,分割")
	private String pointsDecimal;
	
	@Column(name = "pointsDFM")
	@ApiModelProperty(value = "空域形状经纬度点的集合，存度分秒格式，多个用,分割")
	private String pointsDFM;
	
	@Column(name = "radius")
	@ApiModelProperty(value = "半径。空域形状为圆形有值")
	private String radius;
	
	@Column(name = "airspaceGis")
	@ApiModelProperty(value = "管辖空域GIS对象，JSON格式")
	private String airspaceGis;
	
	@Column(name = "createTime")
	@ApiModelProperty(value = "创建时间")
	private String createTime;
	
	@Column(name = "createUser")
	@ApiModelProperty(value = "创建人")
	private String createUser;
}
