/**  
 * 
 * @Title: DocumentTemplates.java 
 * @Package com.atosenet.ga.company.db.model.ga.company  
 * @author dgw   
 * @date 2018年8月9日 上午9:36:32 
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
 * TODO(文档模板model)
 * 
 * @ClassName: DocumentTemplates 
 * @author dgw 
 * @date 2018年8月9日 上午9:36:32 
 *  
 */
@Entity
@Data
@Table(name = "document_template")
public class DocumentTemplate {
	
	/**模板id**/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "template_id")
	private long templateId;
	
	/**企业id**/
	@Column(name = "company_id")
	private long companyId;
	
	/**模板名称**/
	@Column(name = "template_name")
	private String templateName;
	
	/**上传人**/
	@Column(name = "uploading_person")
	private String uploadingPerson;
	
	/**创建时间**/
	@Column(name = "create_time")
	private Date createTime;
	
	/**更新时间**/
	@Column(name = "update_time")
	private Date updateTime;
	
	/**版本号**/
	@Column(name = "version_number")
	private String versionNumber;
	
	/**文件上传路径**/
	@Column(name = "upload_path")
	private String uploadPath;
	
	/**模板类型**/
	@Column(name ="template_type")
	private String templateType;
}
