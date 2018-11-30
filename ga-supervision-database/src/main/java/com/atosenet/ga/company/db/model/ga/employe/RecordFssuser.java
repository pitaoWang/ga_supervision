package com.atosenet.ga.company.db.model.ga.employe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.atosenet.ga.company.db.utils.ExcelData;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@Table(name = "record_fssuser")
public class RecordFssuser implements ExcelData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fssUserId")
	private Integer fssUserId;
	
	/**
	 * 编号
	 */
	@Column(name = "code")
	@ApiModelProperty(value="编号")
	private String code;
	
	/**
	 * 所属飞行服务站
	 */
	@Column(name = "fssId")
	@ApiModelProperty(value="所属飞行服务站")
	private Integer fssId;
	
	/**
	 * 所属监督管理局
	 */
	@Column(name = "administrationId")
	@ApiModelProperty(value="所属监督管理局")
	private Integer administrationId;
	
	/**
	 * 所属空管局
	 */
	@Column(name = "atmbId")
	@ApiModelProperty(value="所属空管局")
	private Integer atmbId;
	
	/**
	 * 头像
	 */
	@Column(name = "headImg")
	@ApiModelProperty(value="头像")
	private String headImg;
	
	/**
	 * 姓名
	 */
	@Column(name = "name")
	@ApiModelProperty(value="姓名")
	private String name;
	
	
	@Transient
	private String fssName;
	
	/**
	 * 性别
	 */
	@Column(name = "gender")
	@ApiModelProperty(value="性别")
	private String gender;
	
	/**
	 * 年龄
	 */
	@Column(name = "age")
	@ApiModelProperty(value="年龄")
	private String age;
	
	/***
	 * 身份证号
	 */
	@Column(name = "idcard")
	@ApiModelProperty(value="身份证号")
	private String idcard;
	
	
	/**
	 *身份证正面扫描件
	 */
	@Column(name = "idcardScanningImg1")
	@ApiModelProperty(value="身份证正面扫描件")
	private String idcardScanningImg1;
	
	/**
	 * 身份证反面扫描件
	 */
	@Column(name = "idcardScanningImg2")
	@ApiModelProperty(value="身份证反面扫描件")
	private String idcardScanningImg2;
	
	/**
	 * 生日
	 */
	@Column(name = "birthday")
	@ApiModelProperty(value="生日")
	private String birthday;
	
	/**
	 * 国籍
	 */
	@Column(name = "nationality")
	@ApiModelProperty(value="国籍")
	private String nationality;
	
	/**
	 * 民族
	 */
	@Column(name = "nation")
	@ApiModelProperty(value="民族")
	private String nation;
	
	/**
	 * 学历
	 */
	@Column(name = "education")
	@ApiModelProperty(value="学历")
	private String education;
	
	
	/**
	 * 毕业院校
	 */
	@Column(name = "graduateSchool")
	@ApiModelProperty(value="毕业院校")
	private String graduateSchool;
	
	
	/**
	 * 通讯地址
	 */
	@Column(name = "address")
	@ApiModelProperty(value="通讯地址")
	private String address;
	
	/**
	 * 邮政编码
	 */
	@Column(name = "postcode")
	@ApiModelProperty(value="邮政编码")
	private String postcode;
	
	
	/**
	 * 手机号码
	 */
	@Column(name = "tel")
	@ApiModelProperty(value="手机号码")
	private String tel;
	
	/**
	 * 邮箱
	 */
	@Column(name = "email")
	@ApiModelProperty(value="邮箱")
	private String email;
	
	
	/**
	 * 紧急联系人
	 */
	@Column(name = "emergencyContact")
	@ApiModelProperty(value="紧急联系人")
	private String emergencyContact;
	
	/**
	 * 紧急联系人电话
	 */
	@Column(name = "emergencyContactTel")
	@ApiModelProperty(value="紧急联系人电话")
	private String emergencyContactTel;
	
	/**
	 * 职位
	 */
	@Column(name = "position")
	@ApiModelProperty(value="职位")
	private String position;
	
	/**
	 * 出勤状态
	 */
	@Column(name = "attendanceStatus")
	@ApiModelProperty(value="出勤状态")
	private String attendanceStatus;
	
	/**
	 * 合同状态
	 */
	@Column(name = "contractStatus")
	@ApiModelProperty(value="合同状态")
	private String contractStatus;
	
	
	@Column(name = "createUser")
	private String createUser;
	
	@Column(name = "createTime")
	private String createTime;
	
	@Column(name = "updateUser")
	private String updateUser;
	
	@Column(name = "updateTime")
	private String updateTime;
	@Transient
	@ApiModelProperty(value="分页总条数")
	public Long total;
	/**
	 * (non-Javadoc)
	 * <p>Title: getFieldTitles</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.atosenet.ga.company.db.utils.ExcelData#getFieldTitles()
	 * 
	 */
	@Override
	public String[] getFieldTitles() {
		String[] Titles = {"飞行服务站","姓名","性别","年龄","身份证号","出生年月","国籍","民族",
				"学历","毕业院校","有效通讯地址","邮政编号","手机号码","电子邮箱","紧急联系人","紧急联系人电话",
				"职位","出勤状态","合同状态"};
		return Titles;
	}
	/**
	 * (non-Javadoc)
	 * <p>Title: getFieldNames</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.atosenet.ga.company.db.utils.ExcelData#getFieldNames()
	 * 
	 */
	@Override
	public String[] getFieldNames() {
		String[] Name = {"fssName","name","gender","age","idcard","birthday",
				"nationality","nation","education","graduateSchool","address","postcode",
				"tel","email","emergencyContact","emergencyContactTel","position","attendanceStatus",
				"contractStatus"};
		return Name;
	}
	
	
	

}
