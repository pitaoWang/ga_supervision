//package com.atosenet.ga.company.db.repos.sys;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//
//import com.atosenet.ga.company.db.core.common.BaseEntity;
//import com.atosenet.ga.company.db.model.sys.Resource;
//
//@RepositoryRestResource(collectionResourceRel = "resource", path = "resource")
//public interface SysResourceRepository extends PagingAndSortingRepository<Resource, Long> {
//	@Query("select distinct m from Resource m, Role r, User u where m in elements (r.resources) and r in elements (u.roles) and m.delFlag=" + BaseEntity.DEL_FLAG_NORMAL_STR + " and r.delFlag=" + BaseEntity.DEL_FLAG_NORMAL_STR + " and u.delFlag=" + BaseEntity.DEL_FLAG_NORMAL_STR + " and u.id=?1 order by m.sort")
//	List<Resource> findByUserId(Long userId);
//}
