/**  
 * 
 * @Title: BaseClassroom.java 
 * @Package com.atosenet.ga.company.db.model.ga.company  
 * @author dgw   
 * @date 2018年8月7日 上午11:58:19 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.model.ga.company;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * TODO(基地教室)
 * 
 * @ClassName: BaseClassroom 
 * @author dgw 
 * @date 2018年8月7日 上午11:58:19 
 *  
 */
/*@Entity
@Data
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table(name = "base_classroom")*/
public class BaseClassroom {
	/**基地教室id**/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "classroom_id")
	@ApiModelProperty(value = "基地教室id")
	private long classroomId;
	
	/**运行基地id**/
	@Column(name="base_id")
	@ApiModelProperty(value = "运行基地id")
	public long baseId;
	

	/*@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="base_id", referencedColumnName="base_id" ,insertable=false, updatable=false)
	private OperationBase operationBase;
	
	 public OperationBase getOperationBase() {
	    return operationBase;
	 }
	 public void setOperationBase(OperationBase operationBase) {
	        this.operationBase = operationBase;
	 }*/
	
	/**教室类型**/
	@Column(name = "classroom_type")
	@ApiModelProperty(value = "教室类型(1-教室   2-讲评室)")
	private String classroomType;
	
	/**地址**/
	@Column(name = "address")
	@ApiModelProperty(value = "教室地址")
	private String classAddress;
	
	/**学员最大容量**/
	@ApiModelProperty(value = "学员最大容量")
	@Column(name = "maximum_students")
	private String maximumStudents;
}
