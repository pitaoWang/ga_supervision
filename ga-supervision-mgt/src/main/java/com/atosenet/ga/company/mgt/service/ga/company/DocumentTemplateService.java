/**  
 * 
 * @Title: DocumentTemplateService.java 
 * @Package com.atosenet.ga.company.mgt.service.ga.company  
 * @author dgw   
 * @date 2018年8月9日 上午9:57:08 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.DocumentTemplate;
import com.atosenet.ga.company.db.repos.ga.company.DocumentTemplateRepository;

/** 
 * TODO(文档模板服务层)
 * 
 * @ClassName: DocumentTemplateService 
 * @author dgw 
 * @date 2018年8月9日 上午9:57:08 
 *  
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class DocumentTemplateService {
	
	@Autowired
	DocumentTemplateRepository documentTemp;
	
	/**
	 * 
	 * TODO(获取文档模板分页列表) 
	 *
	 * @Title: findPageAll 
	 * @param pageable
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly = true)
	public Page<DocumentTemplate> findPageAll(Pageable pageable){
		return documentTemp.findAll(pageable);
	}
	
	
	
	/**
	 * 
	 * TODO(保存文档模板信息) 
	 *
	 * @Title: save 
	 * @param docTemp
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly = false)
	public DocumentTemplate save(DocumentTemplate docTemp){
		return documentTemp.save(docTemp);
	}
	
	/**
	 * 
	 * TODO(根据公司id模板名字和版本号查询) 
	 *
	 * @Title: findByCompanyIdAndTemplateNameAndVersionNumber 
	 * @param companyId
	 * @param tempName
	 * @param version
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly = true)
	public DocumentTemplate findByCompanyIdAndTemplateNameAndVersionNumber(long companyId,String tempName,String version){
		return documentTemp.findByCompanyIdAndTemplateNameAndVersionNumber(companyId, tempName, version);
	}
	
	/**
	 * 
	 * TODO(根据id删除文档模板信息) 
	 *
	 * @Title: delete 
	 * @param id  
	 * @throws
	 *
	 */
	@Transactional(readOnly = false)
	public void delete(long id){
		documentTemp.delete(id);
	}
	
	/**
	 * 
	 * TODO(根据文档模板id查询文档信息) 
	 *
	 * @Title: findByTemplateId 
	 * @param tempId
	 * @return  
	 * @throws
	 *
	 */
	public DocumentTemplate findByTemplateId(long tempId){
		return documentTemp.findByTemplateId(tempId);
	}
	
	/**
	 * 
	 * TODO(根据企业id查询资料模板) 
	 *
	 * @Title: findByCompanyId 
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	public List<DocumentTemplate> findByCompanyId(long companyId){
		return documentTemp.findByCompanyId(companyId);
	}
}
