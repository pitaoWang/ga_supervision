//package com.atosenet.ga.company.db.repos.sys;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//
//import com.atosenet.ga.company.db.core.common.BaseEntity;
//import com.atosenet.ga.company.db.model.sys.Resource;
//
//
//@RepositoryRestResource(collectionResourceRel = "res", path = "res")
//public interface ResourceRepository extends JpaRepository<Resource, Long>{
//	List<Resource> findByDelFlagAndParentIsNull(@Param("delFlag") int delFlag);
//	
//	@Query("select  m from Resource m where m.delFlag=" + BaseEntity.DEL_FLAG_NORMAL_STR + " and m.parent.id=:parentId order by m.sort")
//	List<Resource> findByParentId(@Param("parentId") Long parentId);
//
//	
//}
