package com.atosenet.ga.company.db.repos.sys;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.sys.Org;



@RepositoryRestResource(collectionResourceRel = "orgrepos", path = "orgrepos")
public interface OrgRepository extends PagingAndSortingRepository<Org, Long> {
	
	@Query("select t from Org t where orgtype = ?1")
	List<Org> findByOrgType(long id);
	
	@Query("select t from Org t")
	List<Org> findList();
	
	@Query("select t from Org t where name = ?1 and orgtype=?2")
	List<Org> selectByNameAndOrgType(String name,long orgtype);
	
	@Query("select t from Org t where name = ?1 and orgtype=?2")
	Org selectByNameAndOrgTypeone(String name,long orgtype);
	
	@Query("select t from Org t where name = ?1")

	List<Org> selectByName(String name);

	<T> List<T> findByParentidsContaining(@Param("id") long id, Class<T> clz);
	
	<T> List<T> findById(@Param("id") long id, Class<T> clz);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByAuthorityId 
	 * @param authorityId
	 * @return  
	 * @throws
	 *
	 */
	Org findById(Long authorityId);

	

	


}
