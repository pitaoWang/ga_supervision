package com.atosenet.ga.company.db.model.ga.company;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;
/**
 * 技术级别
 * @Description:TODO
 * @author
 * @time:2017年8月31日 下午8:01:57
 */
@Entity
@Table(name="company_techgrade")
@Data
public class CompanyTechGrade implements Serializable{
	private static final long serialVersionUID = 1L;
	  /** ID */
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id;

	   /**岗位code */
	   @Column(name="pos_code")
	   private String poscode;
	   
	   /** 岗位类别 */
	   @Column(name="pos_class")
	   private String posclass;
	   
	   /** 技术级别 */
	   @Column(name="tech_grade")
	   private String techgrade;
	   
	
}
