/**
 * 
 */
package com.atosenet.ga.company.db.model.ga.company;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

/**
 * @author lvhao
 *
 */
@Entity
@Data
@Table(name="engine")
public class Engine {
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	/**
	 * 模拟训练器id
	 */
	@Column(name="simulator_id")
	private Long simulatorId;
	
	/**
	 * 发动机类型
	 */
	@Column(name="engine_type")
	private String engineType;
	
	/**
	 * 数据修订号
	 */
	@Column(name="data_revision_number")
	private String dataRevisionNum;
	
	/**
	 * 数据出处
	 */
	@Column(name="data_source")
	private String dataSource;
	
	/**
	 * 创建时间
	 */
	@Column(name="createtime")
	private Date createTime;
	
	/**
	 * 修改时间
	 */
	@Column(name="updatetime")
	private Date updateTime;

}
