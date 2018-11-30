/**
 * 
 */
package com.atosenet.ga.company.db.model.ga.company;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lvhao
 *
 */
@Entity
@Data
@Table(name="company_datafile")
public class Document implements Serializable{
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="datafile_id")
	@ApiModelProperty(value="文件id")
	private Long datafileId;
	
	/**
	 * 公司id
	 */
	@Column(name="company_id")
	@ApiModelProperty(value="公司id")
	private Long companyId;
	
	/**
	 * 文件名
	 */
	@Column(name="datafile_name")
	@ApiModelProperty(value="文件名")
	private String dataFileName;
	
	/**
	 * 文件类型
	 */
	@Column(name="datafile_type")
	
	@ApiModelProperty(value="资料文件类型 (1-维修工程管理手册  2-培训大纲 3-航空器维修方案 4-航空器检查大纲 5-可靠性方案"
			+ "6-最低设备清单 7-运行规范  8-运行手册  9-飞行训练管理手册  10-训练大纲 11-航空理论 12-法规政策 13-视频资料)")
	private String dataFileType;
	
	/**
	 * 上传人
	 */
	@Column(name="uploading_person")
	@ApiModelProperty(value="上传人")
	private String upPerson;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	@ApiModelProperty(value="创建时间")
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	@Column(name="update_time")
	@ApiModelProperty(value="更新时间")
	private Date upTime;
	
	/**
	 * 版本号
	 */
	@Column(name="version_number")
	@ApiModelProperty(value="版本号")
	private String versionNumber;
	
	/**
	 * 上传路径
	 */
	@Column(name="upload_path")
	@ApiModelProperty(value="上传路径")
	private String uploadPath;
	
}
