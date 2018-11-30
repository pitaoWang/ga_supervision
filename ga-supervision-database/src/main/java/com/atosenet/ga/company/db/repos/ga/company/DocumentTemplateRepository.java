/**  
 * 
 * @Title: DocumentTemplateRepository.java 
 * @Package com.atosenet.ga.company.db.repos.ga.company  
 * @author dgw   
 * @date 2018年8月9日 上午9:51:27 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.atosenet.ga.company.db.model.ga.company.DocumentTemplate;

/** 
 * TODO(文档模板数据接口)
 * 
 * @ClassName: DocumentTemplateRepository 
 * @author dgw 
 * @date 2018年8月9日 上午9:51:27 
 *  
 */
public interface DocumentTemplateRepository extends PagingAndSortingRepository<DocumentTemplate,Long> {
	
	/**
	 * 
	 * (non-Javadoc)
	 * <p>Title: findAll</p> 
	 * <p>Description:获取文档模板分页列表 </p> 
	 * @param pageable
	 * @return 
	 * @see org.springframework.data.repository.PagingAndSortingRepository#findAll(org.springframework.data.domain.Pageable)
	 *
	 */
	Page<DocumentTemplate> findAll(Pageable pageable);
	
	/**
	 * 
	 * (non-Javadoc)
	 * <p>Title: save</p> 
	 * <p>Description:保存文档模板信息 </p> 
	 * @param docTemp
	 * @return 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 *
	 */
	DocumentTemplate save(DocumentTemplate docTemp);
	
	/**
	 * 
	 * TODO(根据公司id和模板名字和版本号查询(用于判别重复文档)) 
	 *
	 * @Title: findByCompanyIdAndTemplateNameAndVersionNumber 
	 * @param companyId
	 * @param tempName
	 * @param version
	 * @return  
	 * @throws
	 *
	 */
	DocumentTemplate findByCompanyIdAndTemplateNameAndVersionNumber(long companyId,String tempName,String version);
	
	/**
	 * 
	 * TODO(根据id删除文档模板信息) 
	 *
	 * @Title: delete 
	 * @param id  
	 * @throws
	 *
	 */
	void delete(long id);
	
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
	DocumentTemplate findByTemplateId(long tempId);
	
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
	List<DocumentTemplate> findByCompanyId(long companyId);
}
