package com.atosenet.ga.sys.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.atosenet.ga.sys.common.constant.CommonConstant;
import com.atosenet.ga.sys.common.utils.SnowFlakeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Exrickx
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public abstract class XbootBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ApiModelProperty(value = "唯一标识")
    private String id = String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId());

    @ApiModelProperty(value = "创建者")
    @Column(name="create_by")
    private String createBy;
//
    @CreatedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    @Column(name="create_time")
    private Date createTime;
//
    @ApiModelProperty(value = "更新者")
    @Column(name="update_by")
    private String updateBy;

/*    @LastModifiedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    @Column(name="create_time")
    private Date updateTime;*/

    @ApiModelProperty(value = "删除标志 默认0")
    @Column(name="del_flag")
    private Integer delFlag = CommonConstant.STATUS_NORMAL;
}
