package com.atosenet.ga.sys.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.atosenet.ga.sys.base.XbootBaseEntity;
import com.atosenet.ga.sys.common.constant.CommonConstant;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Exrickx
 */
@Data
@Entity
@Table(name = "t_quartz_job")
@TableName("t_quartz_job")
public class QuartzJob extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "任务类名")
    @Column(name="job_class_name")
    private String jobClassName;

    @ApiModelProperty(value = "cron表达式")
    @Column(name="cron_expression")
    private String cronExpression;

    @ApiModelProperty(value = "参数")
    @Column(name="parameter")
    private String parameter;

    @ApiModelProperty(value = "备注")
    @Column(name="description")
    private String description;

    @ApiModelProperty(value = "状态 0正常 -1停止")
    @Column(name="status")
    private Integer status = CommonConstant.STATUS_NORMAL;
    
    /** 更新时间 */
	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date updateTime;
}
