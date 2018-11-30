package com.atosenet.ga.company.db.model.ga.employe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "qualification_configure")
public class QualificationConfigure {
	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	//企业id
	@Column(name = "company_id")
	private Long companyId;
	
	//企业名称
	@Column(name = "company_name")
	private String companyName;
	
	//企业时间段
	@Column(name = "time_span1")
	private String timeSpan1;
	
	//个人
	@Column(name = "time_span2")
	private String timeSpan2;
	
	//资质类型
	/*@Column(name = "qualification_type")
	private String qualificationType;*/
	
	//短信接收号码
	@Column(name = "short_message_no")
	private String shortMessageNo;
	
	//邮件接收地址
	@Column(name = "mailbox_no")
	private String mailboxNo;
}
