/**
 * 
 */
package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.atosenet.ga.company.db.model.ga.company.Simulator;
import com.atosenet.ga.company.db.model.ga.company.vo.SimulatorVO;

/**
 * @author lvhao
 *
 */
public interface FlySimulateRepository extends PagingAndSortingRepository<Simulator, Long> , JpaSpecificationExecutor<Simulator> {

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: selectSimulatorByCompanyId 
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	@Query("select s from Simulator s where companyId = ?1")
	List<Simulator> selectSimulatorByCompanyId(long companyId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findsimulatebycertificateNum 
	 * @param certificateNum
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	Simulator findByCertificateNumAndCompanyId(@Param("certificateNum")String certificateNum, @Param("companyId")Long companyId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findsimulatebycertificateNums 
	 * @param certificateNum
	 * @param id
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	@Query(value="select * from simulator t where t.certificate_number=?1 and t.company_id = ?3 and  t.id != ?2",
			nativeQuery=true)
	Simulator findsimulatebycertificateNums(String certificateNum, Long id, Long companyId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findallLists 
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	@Query(value="select count(*) from simulator WHERE  FIND_IN_SET(company_id,?1)",
			nativeQuery=true)
	String findallLists(String companyId);

	/*@Query("select new com.atosenet.ga.company.db.model.ga.company.vo.SimulatorVO(t.id as id, t.equipmentName as equipmentName, t.equipmentType as equipmentType,t.equipmentModel as equipmentModel, t.equipmentStatus as equipmentStatus, t1.name as companyName, t2.name as superName, t3.name as manageName) from Simulator t, GaCompany t1, Org t2, Org t3 where t.companyId = t1.companyId and t.superId = t2.id and t.manageId = t3.id and t.equipmentName = ?1 and t.equipmentType = ?2 and t.companyId = ?3")
	Page<SimulatorVO> findByEquipmentNameAndEquipmentType (@Param("name") String name, @Param("type") String type, @Param("companyId") long companyId, Pageable pageable);
	*/
}
