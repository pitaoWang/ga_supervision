//package com.atosenet.ga.company.db.repos.sys;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//
//import com.atosenet.ga.company.db.core.common.BaseEntity;
//import com.atosenet.ga.company.db.model.sys.User;
//
//@RepositoryRestResource(collectionResourceRel = "user", path = "user")
//public interface UserRepository extends JpaRepository<User, Long> {
//	
//	@Query("select u from User u where u.delFlag=" + BaseEntity.DEL_FLAG_NORMAL_STR)
//	List<User> findList();
//	
//	User findByAccount(String account);
//	
//	@Query(value="select t1.id  as userid, t2.id  as orgid, t1.account,t1.username,t2.`name` as orgname, t4.alias as rolename from sys_user t1, sys_org t2,sys_user_role t3,sys_role t4 "
//			+ "where t1.org_id = t2.id  and t1.id  = t3.user_id and t3.role_id = t4.id and t2.id =:orgid LIMIT :page, :page_size",nativeQuery=true)
//	List<Object> listbyorgid(@Param("orgid") String orgid, @Param("page") int offset, @Param("page_size") int pageSize);
//	
//	@Modifying
//	@Query(value=" DELETE FROM employee WHERE userid = :userid ",nativeQuery=true)
//	void deleteemployeeByOrgId(@Param("userid")Long userid);
//	
//}
