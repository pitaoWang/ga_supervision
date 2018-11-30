/**  
 * 
 * @Title: SkilledCheckRecord.java 
 * @Package com.atosenet.ga.company.db.repos.ga.employe  
 * @author xyx   
 * @date 2018年8月8日 上午11:07:11 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.model.ga.employe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/** 
 * TODO(定期记录)
 * 
 * @ClassName: SkilledCheckRecord 
 * @author xyx 
 * @date 2018年8月8日 上午11:07:11 
 *  
 */
@Entity
@Data
@Table(name = "periodical_check_record")
public class PeriodicalCheckRecord {
	/** 定期检查记录id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long id;
	/** 飞行员id */
	@Column(name = "pilot_id")
	public Long pilotId;
	/** 定期检查记录标单号*/
	@Column(name = "label_no")
	public java.lang.String labelNo;
	/** 检查单位名称*/
	@Column(name = "check_unit")
	public java.lang.String checkUnit;
	/** 定期检查设备类型*/
	@Column(name = "check_equipment_type")
	public java.lang.String type;
	/** 设备型号*/
	@Column(name = "equipment_model")
	public java.lang.String equipmentModel;
	/**检查日期**/
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "check_date")
	public java.util.Date checkDate;
	/** 检查时间*/
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "check_time")
	public java.util.Date checkTime;
	
	/** 检查人*/
	@Column(name = "check_person")
	public java.lang.String checkPerson;
	/** 检查结果*/
	@Column(name = "check_result")
	public java.lang.String checkResult;
	/**下次检查期限**/
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "next_check_term")
	public java.util.Date nextTerm;
	/** 定期检查工作实践单*/
	@Column(name = "check_practice_list")
	public java.lang.String checkList;
}
