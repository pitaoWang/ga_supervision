package com.atosenet.ga.company.db.model.ga.company;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@Table(name = "mmis_base_engine_history")
public class MmisBaseEngineHistory {
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column(name = "creator")
	@ApiModelProperty(value = "创建者")
	private String creator;
	
	@Column(name = "creator_name")
	@ApiModelProperty(value = "创建者姓名")
	private String creatorName;
	
	@Column(name = "create_time")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	
	@Column(name = "revisor")
	@ApiModelProperty(value = "修改者")
	private String revisor;
	
	@Column(name = "revisor_name")
	@ApiModelProperty(value = "修改者姓名")
	private String revisorName;
	
	@Column(name = "revise_time")
	@ApiModelProperty(value = "修改时间")
	private Date reviseTime;
	
	@Column(name = "company_id")
	@ApiModelProperty(value = "公司id")
	private String companyId;
	
	@Column(name = "status")
	@ApiModelProperty(value = "数据业务状态")
	private String status;
	
	@Column(name = "is_delete")
	@ApiModelProperty(value = "是否删除 1-删除 0-未删除")
	private int isDelete;
	
	@Column(name = "aircraft_id")
	@ApiModelProperty(value = "航空器id")
	private String aircraftId;
	
	@Column(name = "aircraft_reg_no")
	@ApiModelProperty(value = "航空器注册号")
	private String aircraftRegNo;
	
	@Column(name = "aircraft_serial")
	@ApiModelProperty(value = "航空器序列号")
	private String aircraftSerial;
	
	@Column(name = "aircraft_type")
	@ApiModelProperty(value = "航空器类型")
	private String aircraftType;
	
	@Column(name = "aircraft_model")
	@ApiModelProperty(value = "航空器型号")
	private String aircraftModel;
	
	@Column(name = "engine_id")
	@ApiModelProperty(value = "发动机id")
	private String engineId;
	
	@Column(name = "engine_model")
	@ApiModelProperty(value = "发动机型号")
	private String engineModel;
	
	@Column(name = "engine_serial")
	@ApiModelProperty(value = "发动机序列号")
	private String engineSerial;
	
	@Column(name = "complete_operson")
	@ApiModelProperty(value = "完成人")
	private String completeOperson;
	
	@Column(name = "install_date")
	@ApiModelProperty(value = "拆装时间")
	private Date installDate;
	
	@Column(name = "install_status")
	@ApiModelProperty("拆装状态 1-安装 2-拆卸")
	private String installStatus;
}
