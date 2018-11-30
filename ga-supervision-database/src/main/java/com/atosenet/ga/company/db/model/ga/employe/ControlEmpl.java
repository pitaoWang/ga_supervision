/**
 * 
 */
package com.atosenet.ga.company.db.model.ga.employe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author lvhao
 *
 */
@Entity
@Data
@Table(name = "controller_man")
public class ControlEmpl {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long controlId;
	
	/**
	 * 人员基础信息id
	 */
	@Column(name="employee_id")
	private Long employeeId;
	
	/**
	 * 管制人员特殊技能
	 */
	@Column(name="skill")
	private String skill;
	
	/**
	 * 英语资格通信等级
	 */
	@Column(name="english_level")
	private String englishLevel;
	
	/**
	 * 英语无线电路空通信资格
	 */
	@Column(name="commu_status")
	private String commuStatus;
	
	/**
	 * 特殊技能水平
	 */
	@Column(name="skill_level")
	private String skillLevel;

}
