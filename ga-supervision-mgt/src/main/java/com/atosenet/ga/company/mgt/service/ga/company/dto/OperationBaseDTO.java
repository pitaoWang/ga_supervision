/**  
 * 
 * @Title: OperationBaseDTO.java 
 * @Package com.atosenet.ga.company.mgt.service.ga.company.dto  
 * @author dgw   
 * @date 2018年8月17日 下午4:53:34 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.service.ga.company.dto;

import java.util.List;

import com.atosenet.ga.company.db.model.ga.base.BaseClassroom;
import com.atosenet.ga.company.db.model.ga.company.OperationBase;
import com.atosenet.ga.company.db.model.ga.company.TakeCourse;

import lombok.Data;

/** 
 * TODO(运行基地dto)
 * 
 * @ClassName: OperationBaseDTO 
 * @author dgw 
 * @date 2018年8月17日 下午4:53:34 
 *  
 */
@Data
public class OperationBaseDTO {
	
	private OperationBase operationBase;
	
	private List<BaseClassroom> classrooms;
	
	private List<TakeCourse> courses;

}
