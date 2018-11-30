package com.atosenet.ga.company.db.repos.sys;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.sys.Test;

@RepositoryRestResource(collectionResourceRel = "test", path = "test")
public interface TestRepository extends PagingAndSortingRepository<Test, Long> {
	List<Test> findByLastName(@Param("name") String name);
	List<Test> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
	
//	public interface UserRepository extends JpaRepository<Test, Long> {
//
//		  @Query(value = "SELECT * FROM USERS WHERE EMAIL_ADDRESS = ?1", nativeQuery = true)
//		  Test findByEmailAddress(String emailAddress);
//		}
	
	void deleteByLastName(@Param("name") String name);

//	  @Modifying
//	  @Query("delete from User u where user.role.id = ?1")
//	  void deleteInBulkByRoleId(long roleId);
	  
	@Query("select t from Test t")
	List<Test> findList();
	
	@Modifying
	@Query("update Test t set t.firstName=?1 where id=?2")
	void updateFirstNameById(String firstName, Long id);
}
