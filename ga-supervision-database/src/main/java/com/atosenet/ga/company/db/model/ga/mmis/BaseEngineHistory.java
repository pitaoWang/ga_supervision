package com.atosenet.ga.company.db.model.ga.mmis;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 发动机拆装历史实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="mmis_base_engine_history")
@Data
public class BaseEngineHistory {
	
	@Id
	@Column(name = "id")
    private String id;

	/** 创建人 */
	@Column(name = "creator")
	@ApiModelProperty(value="创建人")
    private String creator;

	@Column(name = "creator_name")
	@ApiModelProperty(value="创建者姓名")
    private String creatorName;
	
	@Column(name = "create_time")
	@ApiModelProperty(value="创建时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

	@Column(name = "revisor")
	@ApiModelProperty(value="修改人")
    private String revisor;

	@Column(name = "revisor_name")
	@ApiModelProperty(value="修改人姓名")
    private String revisorName;

	@Column(name = "revisor_time")
	@ApiModelProperty(value="修改时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reviseTime;

	@Column(name = "company_id")
	@ApiModelProperty(value="公司id")
    private String companyId;

	@Column(name = "status")
	@ApiModelProperty(value="数据业务状态")
    private String status;

	@Column(name = "is_delete")
	@ApiModelProperty(value="是否删除,1:删除,0:未删除")
    private int isDelete;

	@Column(name = "aircraft_id")
	@ApiModelProperty(value="所在航空器id")
    private String aircraftId;

	@Column(name = "aircraft_reg_no")
	@ApiModelProperty(value="所在航空器注册号")
    private String aircraftRegNo;

	@Column(name = "aircraft_serial")
	@ApiModelProperty(value="所在航空器序号")
    private String aircraftSerial;

	@Column(name = "aircraft_type")
	@ApiModelProperty(value="航空器类型")
    private String aircraftType;

	@Column(name = "aircraft_model")
	@ApiModelProperty(value="航空器型号")
    private String aircraftModel;
	
	@Column(name = "engine_id")
	@ApiModelProperty(value="发动机ID")
    private String engineId;

	@Column(name = "engine_model")
	@ApiModelProperty(value="发动机型号")
    private String engineModel;

	@Column(name = "engine_serial")
	@ApiModelProperty(value="发动机序列号")
    private String engineSerial;

	@Column(name = "complete_operson")
	@ApiModelProperty(value="完成人")
    private String completeOperson;

	@Column(name = "install_date")
	@ApiModelProperty(value="拆装时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date installDate;

	@Column(name = "install_status")
	@ApiModelProperty(value="拆卸状态  1 安装  2 拆卸")
    private String installStatus;

}