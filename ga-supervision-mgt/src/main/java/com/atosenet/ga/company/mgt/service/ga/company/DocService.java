/**
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

import com.atosenet.ga.company.db.model.ga.company.Document;
import com.atosenet.ga.company.db.repos.ga.company.DocRepository;

/**
 * @author lvhao
 *
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class DocService {
	@Autowired
	private DocRepository docRepository;

	/** 
	 * TODO(获取文件列表) 
	 *
	 * @Title: findByCompanyId 
	 * @param companyid
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly=false)
	public List<Document> findByCompanyId(Long companyid) {
		// TODO Auto-generated method stub
		return this.docRepository.findByCompanyid(companyid);
	}
	
	/**
	 * 
	 * TODO(分页获取企业资料文件列表) 
	 *
	 * @Title: findPageAll 
	 * @param pageable
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly = true)
	public Page<Document> findPageAll(Pageable pageable){
		return docRepository.findAll(pageable);
	}
	
	/**
	 * 
	 * TODO(根据企业id和资料名称和版本号查询资料信息) 
	 *
	 * @Title: findByCompanyIdAndDatafileNameAndVersionNumber 
	 * @param companyId
	 * @param dataName
	 * @param version
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly = true)
	public Document findByCompanyIdAndDatafileNameAndVersionNumber(long companyId,String dataName,String version){
		return  docRepository.findByCompanyIdAndDataFileNameAndVersionNumber(companyId, dataName, version);
	}
	
	/**
	 * 
	 * TODO(保存企业资料信息) 
	 *
	 * @Title: save 
	 * @param doc
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly = false)
	public Document save(Document doc){
		return docRepository.save(doc);
	}
	
	/**
	 * 
	 * TODO(根据企业资料id删除企业资料信息) 
	 *
	 * @Title: delete 
	 * @param id  
	 * @throws
	 *
	 */
	@Transactional(readOnly = false)
	public void  delete(long id){
		docRepository.delete(id);
	}

}
