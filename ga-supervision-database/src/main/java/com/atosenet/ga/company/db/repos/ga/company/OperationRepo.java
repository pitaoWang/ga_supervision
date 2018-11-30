/**
 * 
 */
package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.atosenet.ga.company.db.model.ga.company.OperationBase;
import com.atosenet.ga.company.db.model.ga.company.vo.OperationBaseVO;

/**
 * @author lvhao
 *
 */

public interface OperationRepo extends PagingAndSortingRepository<OperationBase, Long>{
	@Query("select o from OperationBase o where companyId = ?1")
	List<OperationBase> findList(long id);
	
	/**
	 * 
	 * (non-Javadoc)
	 * <p>Title: findPageAll</p> 
	 * <p>Description:获取运行基地分页信息列表 ,包括基地基本信息、承担课程列表和基地教室列表 </p> 
	 * @return 
	 * @see org.springframework.data.repository.CrudRepository#findPageAll()
	 *SELECT DISTINCT t FROM Team t JOIN t.players p 
	 */
	/*@Query(value = "select new com.atosenet.ga.company.db.model.ga.company.vo.OperationBaseVO(o.baseId,o.companyId,"
			+ "o.baseName,"
			+ "o.baseType,"
			+ "o.provience,"
			+ "o.city,"
			+ "o.address,"
			+ "o.tel,"
			+ "o.createTime,"
			+ "t.takecourseId,"
			+ "t.courseGrade,"
			+ "t.courseType,"
			+ "t.syllabusId,"
			+ "t.effectiveDate,"
			+ "t.capacity,"
			+ "b.classroomId,"
			+ "b.classroomType,"
			+ "b.classAddress,"
			+ "b.maximumStudents) from OperationBase o,BaseClassroom b,TakeCourse t where b.baseId = o.baseId and o.baseId = t.baseId")
	Page<OperationBase> findPageAll(Pageable pageable);*/
	Page<OperationBase> findAll(Pageable pageable);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findList 
	 * @return  
	 * @throws
	 *
	 */
	@Query("select t from OperationBase t")
	List<OperationBase> findList();
	
	/**
	 * 
	 * (non-Javadoc)
	 * <p>Title: save</p> 
	 * <p>Description:保存运行基地信息 </p> 
	 * @param operationBase
	 * @return 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 *
	 */
	OperationBase save(OperationBase operationBase);
	
	/**
	 * 
	 * TODO(根据id删除运行基地信息) 
	 *
	 * @Title: delete 
	 * @param baseId  
	 * @throws
	 *
	 */
	void delete(long baseId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByBaseId 
	 * @param baseId
	 * @return  
	 * @throws
	 *
	 */
	OperationBase findByBaseId(Long baseId);
	
	
	@Query(value="select * from operating_base where operating_base.company_id =?1 \n-- #pageable\n",
			countQuery=" select count(*) from operating_base where operating_base.company_id =?1",
			 nativeQuery=true)
	Page<OperationBase> findByCompanyId(Long companyId,Pageable pageable);
	

	/**
	 * 根据企业id查询主基地
	 * @param companyId
	 * @return
	 */
	@Query(value = "select * from operating_base o right join ga_company ga on ga.company_id = o.company_id where o.company_id = ?1 and o.is_mainbase = '1'",nativeQuery=true)
	List<OperationBase> findMainBasesByCompanyId(Integer companyId);
}
