/**
 * 
 */
package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.atosenet.ga.company.db.model.ga.company.Document;

/**
 * @author lvhao
 *
 */
public interface DocRepository extends PagingAndSortingRepository<Document, Long>{
	@Query("select d from Document d where companyId = ?1")
	List<Document> findByCompanyid(Long companyid);
	
	/**
	 * 
	 * (non-Javadoc)
	 * <p>Title: findAll</p> 
	 * <p>Description:分页获取企业资料文件列表 </p> 
	 * @param pageable
	 * @return 
	 * @see org.springframework.data.repository.PagingAndSortingRepository#findAll(org.springframework.data.domain.Pageable)
	 *
	 */
	Page<Document> findAll(Pageable pageable);
	
	/**
	 * 
	 * TODO(根据公司id和文件名字和版本号查询企业资料信息) 
	 *
	 * @Title: findByCompanyIdAndDatafileNameAndVersionNumber 
	 * @param companyId
	 * @param dataName
	 * @param version
	 * @return  
	 * @throws
	 *
	 */
	Document findByCompanyIdAndDataFileNameAndVersionNumber(long companyId,String dataName,String version);
	
//	/**
//	 * 
//	 * (non-Javadoc)
//	 * <p>Title: save</p> 
//	 * <p>Description:保存企业资料信息 </p> 
//	 * @param doc
//	 * @return 
//	 * @see org.springframework.data.repository.CrudRepository#save(S)
//	 *
//	 */
//	Document save(Document doc);
	
	/**
	 * 
	 * TODO(根据id删除企业资料信息) 
	 *
	 * @Title: delete 
	 * @param id  
	 * @throws
	 *
	 */
	void delete(long id);
}
