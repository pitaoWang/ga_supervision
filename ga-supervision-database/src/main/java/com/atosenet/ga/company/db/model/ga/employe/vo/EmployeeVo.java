/**
 * 
 */
package com.atosenet.ga.company.db.model.ga.employe.vo;
import javax.persistence.Entity;


import lombok.Data;

/**
 * @author my
 *
 */

@Data
public class EmployeeVo {
	public Long employeeId;
	//员工id
	public Long userId;
	//用户工号
	public java.lang.String workId;
	//企业id
	public Long companyId;
	//毕业院校
	public java.lang.String  school;
	//身份证号码
	public java.lang.String cardId;
	//手机号码
	public java.lang.String homeMobile;
	//用户姓名
	public java.lang.String userName;
	//年龄
	public java.lang.String  age;
	//电子邮箱
	public java.lang.String  email;
	//人员类型
	public java.lang.String jobTitle;
	//职务
	public java.lang.String techpos;
	//性别
	public java.lang.String gender;
	//所属运行基地
	public Long baseId;
	//出生日期
	public java.util.Date birthday;
	//紧急联系人
	public java.lang.String  urgencyLinkman;
	//有效通讯地址
	public java.lang.String homeAddress;
	//邮政编码
	public java.lang.String homePostcode;
	//国籍
	public java.lang.String nationality;
	//民族
	public java.lang.String ethnicity;
	//教育程度
	public java.lang.String education;
	//是否是局方委任代表
	public java.lang.String isagent;
	//隶属管理局
	public Long authorityId;
	//监管局
	public Long supervisionId;
	//出勤状态
	public java.lang.String turnOut;
	//合同状态
	public java.lang.String contract;
	//头像
	public java.lang.String photo;
	//身份证正面扫描件文件路径
	public java.lang.String scanCard;
	//身份证反面文件扫描路劲
	public String othersideCard;
	
	
	//飞行员id
	public Long pilotId;
	//所属飞行大队
	public String flightTeam;
	//飞行总时长
	public Long flySumTime;
	//违规次数
	public Integer flySumIllegal;
	
	//技术级别
	public java.lang.String techClass;
	//无线电通信资格证
	public java.lang.String wqc;
	//英语通信等级证
	public String  esCertificate;
	//汉语通信等级证
	public java.lang.String chCertificate;
	//飞行经历扫描件
	public java.lang.String scanning;
	//教员任命书扫描件
	public java.lang.String scannedTeacher;
	//教师资格证
	public java.lang.String teacherQualification;
	//教员证书有效期
	public java.util.Date validityTime;
	//教学类型
	public java.lang.String teachCategories;
	
	//管制人员id
	public Long controlId;
	//管制人员特殊技能
	public String skill;
	//英语资格通信等级
	public String englishLevel;
	//英语无线电路空通信资格
	public String commuStatus;
	//特殊技能水平
	public String skillLevel;
}
