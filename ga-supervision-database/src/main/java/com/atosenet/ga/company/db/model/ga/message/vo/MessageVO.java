package com.atosenet.ga.company.db.model.ga.message.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageVO {
	
	private Long id;
	
	@ApiModelProperty(value="人员ID")
	private Long employeeId;
	
	@ApiModelProperty(value="执照编号")
	private String licenseno;
	
	@ApiModelProperty(value="执照类型")
	private String licensetype;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="到期时间")
	private Date expiredate;
	
	private String userName;
}
