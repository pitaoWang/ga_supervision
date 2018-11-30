//package com.atosenet.ga.company.db.repos.sys;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//
//
//import com.atosenet.ga.company.db.model.sys.Role;
//
//
//@RepositoryRestResource(collectionResourceRel = "role", path = "role")
//public interface RoleRepository extends PagingAndSortingRepository<Role, Long>{
//	@Query("select t from Role t where roletype=?1")
//	List<Role> selectByRoletype(int roletype);
//	
//	@Query("select t from Role t where roletype>?1")
//	List<Role> selectAll(int roletype);
//	
//	@Query("select t from Role t where rolename=?1")
//	Role selectByRoleName(String rolename);
//}
