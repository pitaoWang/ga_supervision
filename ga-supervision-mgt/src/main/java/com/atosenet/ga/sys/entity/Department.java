package com.atosenet.ga.sys.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.atosenet.ga.sys.base.XbootBaseEntity;
import com.atosenet.ga.sys.common.constant.CommonConstant;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Exrick
 */
@Data
@Entity
@Table(name = "t_department")
@TableName("t_department")
public class Department{

    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
    
    @ApiModelProperty(value = "部门名称")
    @Column(name="title")
    private String title;

    @ApiModelProperty(value = "父id")
    @Column(name="parent_id")
    private long parentId;

    @ApiModelProperty(value = "是否为父节点(含子节点) 默认false")
    @Column(name="is_parent")
    private Boolean isParent = false;

    @ApiModelProperty(value = "排序值")
    @Column(name="sort_order", precision = 10, scale = 2)
    private BigDecimal sortOrder;

    @ApiModelProperty(value = "是否启用 0启用 -1禁用")
    @Column(name="status")
    private Integer status = CommonConstant.STATUS_NORMAL;
    
    @ApiModelProperty(value = "类型")
    @Column(name="type")
    private String type;
    
    @Transient
    @TableField(exist=false)
    @ApiModelProperty(value = "父节点名称")
    private String parentTitle;
}