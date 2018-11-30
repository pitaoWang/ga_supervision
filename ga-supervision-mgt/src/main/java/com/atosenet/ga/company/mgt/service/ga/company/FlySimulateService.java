/**
 * 
 */
package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.Aircraft;
import com.atosenet.ga.company.db.model.ga.company.Course;
import com.atosenet.ga.company.db.model.ga.company.Engine;
import com.atosenet.ga.company.db.model.ga.company.Simulator;
import com.atosenet.ga.company.db.model.ga.company.SimulatorRecord;
import com.atosenet.ga.company.db.model.ga.company.vo.SimulatorVO;
import com.atosenet.ga.company.db.repos.ga.company.CourseRepository;
import com.atosenet.ga.company.db.repos.ga.company.EngineRespository;
import com.atosenet.ga.company.db.repos.ga.company.FlySimulateRepository;
import com.atosenet.ga.company.db.repos.ga.company.SimulatorRecordRepository;
import com.atosenet.ga.company.db.repos.ga.company.SimulatorVORepository;
import com.atosenet.ga.company.mgt.service.ga.company.dto.SimulatorDTO;

/**
 * @author lvhao
 *
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class FlySimulateService {
	@Autowired
	private CourseRepository cRepository;
	@Autowired
	private EngineRespository engineRespository;
	@Autowired
	private FlySimulateRepository FSRepository;
	@Autowired
	private SimulatorRecordRepository srRrpository;
	@Autowired
	private SimulatorVORepository simulatorVORepository;
	
	@Autowired
	private EngineRespository engineRepository;
	
	@Autowired
	private SimulatorRecordRepository SRRepository; 
	
	@Autowired
	private CourseRepository courseRepository;

	/** 
	 * TODO(根据公司id查询模拟飞行器训练设备) 
	 *
	 * @Title: selectSimulatorByCompanyId 
	 * @param parseLong
	 * @return  
	 * @throws
	 *
	 */
		public List<Simulator> selectSimulatorByCompanyId(long companyId) {
		
		return this.FSRepository.selectSimulatorByCompanyId(companyId);
	}

	/** 
	 * TODO(保存模拟飞行器训练设备) 
	 *
	 * @Title: save 
	 * @param simulator
	 * @param request  
	 * @throws
	 *
	 */
	public void save(SimulatorDTO info) {
		// TODO Auto-generated method stub
		Simulator simulator = FSRepository.save(info.getSimulator());
		for(int i = 0;i<info.getSimulatorRecord().size();i++){
			info.getSimulatorRecord().get(i).setSimulatorId(simulator.getId());
			srRrpository.save(info.getSimulatorRecord().get(i));
		}
		for(int i = 0;i<info.getCourse().size();i++){
			info.getCourse().get(i).setSimulatorId(simulator.getId());
			cRepository.save(info.getCourse().get(i));
		}
		for(int i = 0;i<info.getEngine().size();i++){
			info.getEngine().get(i).setSimulatorId(simulator.getId());
			engineRespository.save(info.getEngine().get(i));
		}
	}

	/** 
	 * TODO(删除模拟训练飞行器  级联删除 发动机  鉴定记录 适用课程) 
	 *
	 * @Title: delete 
	 * @param id  
	 * @throws
	 *
	 */
	@Transactional
	public void delete(long id) {
		//根据id查询 发动机
		List<Long> ids = this.engineRepository.selectBySimulatorId(id);
		for(Long eId : ids){
			this.engineRepository.delete(eId);
		}
		//查询鉴定记录
		List<Long> cids = this.SRRepository.selectBySimulatorId(id);
		for(Long cid: cids){
			this.SRRepository.delete(cid);
		}

		//查询适用课程
		List<Long> courseIds = this.courseRepository.selectBySimulatorId(id);
		for(Long courseId: courseIds){
			this.courseRepository.delete(courseId);
		}
		this.FSRepository.delete(id);
		
	}
	
	/**
	 * 
	* getSimulatorById:(这里用一句话描述这个方法的作用). <br/> 
	* 
	* @author Administrator 
	* @param id
	* @return 
	* @since JDK 1.8
	 */
	public SimulatorDTO getSimulatorById(long id) {
		SimulatorDTO simulatorDTO = new SimulatorDTO();
		 Simulator simulator = FSRepository.findOne(id);
		 List<Engine> engine = engineRepository.findBySimulatorId(simulator.getId());
		 List<Course> course = courseRepository.findBySimulatorId(simulator.getId());
		 List<SimulatorRecord> simulatorRecord = SRRepository.findBySimulatorId(simulator.getId());
		 simulatorDTO.setCourse(course);
		 simulatorDTO.setEngine(engine);
		 simulatorDTO.setSimulator(simulator);
		 simulatorDTO.setSimulatorRecord(simulatorRecord);
		 return simulatorDTO;
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: selectPage 
	 * @param pageable
	 * @return  
	 * @throws
	 *
	 */
	public Page<Simulator> selectPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return this.FSRepository.findAll(pageable);
	}
	
	
	public Page<SimulatorVO> getList(String name, String type,String companyId, Pageable pageable) {
		return this.simulatorVORepository.findByEquipmentNameAndEquipmentType(name, type,companyId, pageable);
	}

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
	public Simulator findsimulatebycertificateNum(String certificateNum, Long companyId) {
		// TODO Auto-generated method stub
		return FSRepository.findByCertificateNumAndCompanyId(certificateNum,companyId);
	}

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
	public Simulator findsimulatebycertificateNums(String certificateNum, Long id, Long companyId) {
		// TODO Auto-generated method stub
		return FSRepository.findsimulatebycertificateNums(certificateNum,id,companyId);
	}

	/**
	 * @param productionSn 
	 * @param equipmentModel  
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: getLists 
	 * @param name
	 * @param type
	 * @param valueOf
	 * @return  
	 * @throws
	 *
	 */
	public List<SimulatorVO> getLists(String name, String type, String companyId) {
		// TODO Auto-generated method stub
		return simulatorVORepository.findByEquipmentNameAndEquipmentTypes(name, type, companyId);
	}

}
