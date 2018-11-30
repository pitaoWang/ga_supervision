package com.atosenet.ga.sys.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.atosenet.ga.sys.base.XbootBaseEntity;
import com.atosenet.ga.sys.common.constant.CommonConstant;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Exrickx
 */
@Data
@Entity
@Table(name = "t_user")
@TableName("t_user")
public class User extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    @Column(name="username", unique = true, nullable = false)
    private String username;

    @ApiModelProperty(value = "密码")
    @Column(name="password")
    private String password;

    @ApiModelProperty(value = "昵称")
    @Column(name="nick_name")
    private String nickName;

    @ApiModelProperty(value = "手机")
    @Column(name="mobile")
    private String mobile;

    @ApiModelProperty(value = "邮件")
    @Column(name="email")
    private String email;

    @ApiModelProperty(value = "地址")
    @Column(name="address")
    private String address;

    @ApiModelProperty(value = "0女 1男 2保密")
    @Column(name="sex")
    private Integer sex;

    @ApiModelProperty(value = "用户头像")
    @Column(name="avatar", length=1000)
    private String avatar = CommonConstant.USER_DEFAULT_AVATAR;

    @ApiModelProperty(value = "用户类型 0普通用户 1管理员")
    @Column(name="type")
    private Integer type = CommonConstant.USER_TYPE_NORMAL;

    @ApiModelProperty(value = "状态 默认0正常 -1拉黑")
    @Column(name="status")
    private Integer status;

    @ApiModelProperty(value = "描述/详情/备注")
    @Column(name="description")
    private String description;

    @ApiModelProperty(value = "所属部门id")
    @Column(name="department_id")
    private Long departmentId;
    
    @ApiModelProperty(value = "所属公司id")
    @Column(name="company_id")
    private Long companyId;
    
    @ApiModelProperty(value = "员工类型")
    @Column(name="jobTitle")
    private String jobTitle;
    
    @ApiModelProperty(value = "微信")
    @Column(name="wechat")
    private String wechat;
    @Column(name = "employee_id")
	public Long employeeId;
    @Transient
    @TableField(exist=false)
    @ApiModelProperty(value = "所属部门名称")
    private String departmentTitle;

    @Transient
    @TableField(exist=false)
    @ApiModelProperty(value = "用户拥有角色")
    private List<Role> roles;

    @Transient
    @TableField(exist=false)
    @ApiModelProperty(value = "用户拥有的权限")
    private List<Permission> permissions;
    
    /** 更新时间 */
	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date updateTime;
}
