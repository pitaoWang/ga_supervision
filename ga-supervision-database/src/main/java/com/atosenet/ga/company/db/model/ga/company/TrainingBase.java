/**  
 * 
 * @Title: TrainingBase.java 
 * @Package com.atosenet.ga.company.db.model.ga.company  
 * @author dgw   
 * @date 2018年8月8日 下午3:05:10 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.model.ga.company;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/** 
 * TODO(训练基地model)
 * 
 * @ClassName: TrainingBase 
 * @author dgw 
 * @date 2018年8月8日 下午3:05:10 
 *  
 */
@Entity
@Data
@Table(name = "training_base")
public class TrainingBase {
	 /**训练基地id**/
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "trainbase_id")
	 private long trainbaseId;
	 
	 /**所属公司id**/
	 @Column(name = "company_id")
	 private long companyId;
	 
	 /**训练基地名称**/
	 @Column(name = "trainbase_name")
	 private String trainbaseName;
	 
	 /**所在省**/
	 @Column(name = "trainbase_province")
	 private String trainbaseProvince;
	 
	 /**所在市**/
	 @Column(name = "trainbase_city")
	 private String trainbaseCity;
	 
	 /**详细地址**/
	 @Column(name = "trainbase_detailed_adress")
	 private String trainbaseDetailedAdress;
	 
	 /**基地电话**/
	 @Column(name = "trainbase_tel")
	 private String trainbaseTel;
	 
	 /**创建时间**/
	 @Column(name = "create_time")
	 private Date createTime;
	 
	 /**更新时间**/
	 @Column(name = "update_time")
	 private Date updateTime;
}
