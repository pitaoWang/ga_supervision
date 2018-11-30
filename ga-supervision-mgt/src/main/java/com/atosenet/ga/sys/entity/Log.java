package com.atosenet.ga.sys.entity;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.atosenet.ga.sys.base.XbootBaseEntity;
import com.atosenet.ga.sys.common.utils.ObjectUtil;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Exrickx
 */
@Data
@Entity
@Table(name = "t_log")
@TableName("t_log")
public class Log extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "方法操作名称")
    @Column(name="name")
    private String name;

    @ApiModelProperty(value = "请求路径")
    @Column(name="request_url")
    private String requestUrl;

    @ApiModelProperty(value = "请求类型")
    @Column(name="request_type")
    private String requestType;

    @ApiModelProperty(value = "请求参数")
    @Column(name="request_param")
    private String requestParam;

    @ApiModelProperty(value = "请求用户")
    @Column(name="username")
    private String username;

    @ApiModelProperty(value = "ip")
    @Column(name="ip")
    private String ip;

    @ApiModelProperty(value = "ip信息")
    @Column(name="ip_info")
    private String ipInfo;

    @ApiModelProperty(value = "花费时间")
    @Column(name="cost_time")
    private Integer costTime;
    
    /** 更新时间 */
	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date updateTime;

    /**
     * 转换请求参数为Json
     * @param paramMap
     */
    public void setMapToParams(Map<String, String[]> paramMap) {

        this.requestParam = ObjectUtil.mapToString(paramMap);
    }

}
