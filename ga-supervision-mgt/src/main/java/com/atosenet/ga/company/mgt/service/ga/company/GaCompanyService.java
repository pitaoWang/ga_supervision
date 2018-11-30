package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.Airport;
import com.atosenet.ga.company.db.model.ga.company.CompanyQualification;
import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.model.ga.company.LandingPoint;
import com.atosenet.ga.company.db.model.ga.company.MaintainBase;
import com.atosenet.ga.company.db.model.ga.company.OperationBase;
import com.atosenet.ga.company.db.model.sys.Org;
import com.atosenet.ga.company.db.repos.ga.company.AirportRepository;
import com.atosenet.ga.company.db.repos.ga.company.CompanyQualificationRepository;
import com.atosenet.ga.company.db.repos.ga.company.FlyBaseRepository;
import com.atosenet.ga.company.db.repos.ga.company.GaCompanyRepository;
import com.atosenet.ga.company.db.repos.ga.company.MaintainBaseRepository;
import com.atosenet.ga.company.db.repos.ga.company.OperationRepo;
import com.atosenet.ga.company.db.repos.sys.OrgRepository;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GaCompanyService {

	@Autowired
	GaCompanyRepository gaCompanyRepository;

	@Autowired
	AirportRepository airportRepository;

	@Autowired
	FlyBaseRepository flyBaseRepository;

	@Autowired
	MaintainBaseRepository maintainBaseRepository;

	@Autowired
	OperationRepo operationRepo;

	@Autowired
	OrgRepository orgRepository;

	@Autowired
	CompanyQualificationRepository cqRep;

	/*
	 * 添加公司信息
	 */
	@Transactional(readOnly = false)
	public void save(GaCompany gaCompany, HttpServletRequest request) {
		gaCompany.setFlag((short) 4);
		gaCompanyRepository.save(gaCompany);
	}

	// 保存公司信息(对外接口)
	@Transactional(readOnly = false)
	public GaCompany saveCompanyInfo(GaCompany gaCompany) {
		return gaCompanyRepository.save(gaCompany);
	}

	/*
	 * 添加公司信息
	 */
	@Transactional(readOnly = false)
	public void savecompany(GaCompany gaCompany) {
		gaCompanyRepository.save(gaCompany);
	}

	/*
	 * 获取公司详细信息
	 */
	@Transactional(readOnly = false)
	public GaCompany get(int id) {
		return gaCompanyRepository.findOne(id);
	}

	/*
	 * 获取公司列表
	 */
	@Transactional(readOnly = false)
	public List<GaCompany> findAll() {
		return gaCompanyRepository.findList();
	}

	/*
	 * 获取公司分页列表
	 */
	@Transactional(readOnly = false)
	public Page<GaCompany> findPageAll(Pageable pageable) {
		return gaCompanyRepository.findAll(pageable);
	}

	/*
	 * 获取公司列表,by对应的管理局ID
	 */
	@Transactional(readOnly = false)
	public List<GaCompany> findByAuthorityIdList(Long id) {
		return gaCompanyRepository.findByAuthorityIdList(id);
	}

	/*
	 * 获取公司列表,by对应的监管局ID
	 */
	@Transactional(readOnly = false)
	public List<GaCompany> findBySupervisionIdList(Long id) {
		return gaCompanyRepository.findBySupervisionIdList(id);
	}

	/*
	 * 获取组织名称，by组织类型
	 */
	@Transactional(readOnly = false)
	public List<Org> findOrgByOrgType(long type) {
		return orgRepository.findByOrgType(type);
	}

	/*
	 * 获取公司，根据组织ID
	 */
	@Transactional(readOnly = false)
	public GaCompany findOrgByOrgid(long org) {
		return gaCompanyRepository.findByOrgId(org);
	}

	/*
	 * 根据管理局ID获取所有监管企业的数量和对应的企业类型；bydgw
	 */
	/*
	 * @Transactional(readOnly=false) public List<Object> getGroupInfo(Long id)
	 * { return gaCompanyRepository.getGroupInfo(id); }
	 */

	/*
	 * 删除公司详细
	 */
	@Transactional(readOnly = false)
	public void delete(int id) {
		gaCompanyRepository.delete(id);
	}

	public Page<GaCompany> selectBySupervisionId(Long supervisionId, Pageable pageable) {
		return gaCompanyRepository.selectBySupervisionId(supervisionId, pageable);
	}

	public Page<GaCompany> selectBySupervisionIdAndName(Long supervisionId, Pageable pageable, String keyword) {
		return gaCompanyRepository.selectBySupervisionIdAndname(supervisionId, pageable, keyword);
	}

	public Page<GaCompany> selectByauthorityId(Long authorityId, Pageable pageable) {
		return gaCompanyRepository.selectByAuthorityId(authorityId, pageable);
	}

	public Page<GaCompany> selectBauthorityIdAndName(Long authorityId, Pageable pageable, String keyword) {
		return gaCompanyRepository.selectByAuthorityIdAndname(authorityId, pageable, keyword);
	}

	public GaCompany getbyname(String name) {
		return gaCompanyRepository.getbyname(name);
	}

	/**
	 * 更新企业拥有的机场 飞行基地 维修基地 起降点
	 * 
	 * @param flybaseid
	 * @param airport
	 * @param maintainid
	 * @param landingid
	 * @param companyid
	 */
	@Transactional(readOnly = false)
	public void savebase(String flybaseid, String airport, String maintainid, String landingid, String companyid,
			HttpServletRequest request) {
		// 机场
		List<Airport> entities = new ArrayList<>();
		if (!"".equals(airport)) {
			String[] airports = airport.split(",");
			for (String airpot : airports) {
				Airport air = new Airport();
				air.setAirportId(Long.parseLong(airpot));
				entities.add(air);
			}
		}

		// List<FlyBase> flybases=new ArrayList<>();
		// //飞行基地
		// if(!"".equals(flybaseid)) {
		// String[] flybse = flybaseid.split(",");
		// for(String fly:flybse){
		// FlyBase flybase = new FlyBase();
		// flybase.setFlybaseid(Long.parseLong(fly));
		// flybases.add(flybase);
		// }
		// }

		ArrayList<MaintainBase> mainarra = new ArrayList<>();
		if (!"".equals(maintainid)) {
			// 维修基地
			String[] mainta = maintainid.split(",");
			for (String mai : mainta) {
				MaintainBase mainbase = new MaintainBase();
				mainbase.setMaintainbaseid(Long.parseLong(mai));
				mainarra.add(mainbase);
			}
		}

		ArrayList<LandingPoint> LandingPoints = new ArrayList<>();
		if (!"".equals(landingid)) {
			// 起降点
			String[] landing = landingid.split(",");
			for (String la : landing) {
				LandingPoint landingpoint = new LandingPoint();
				landingpoint.setId(Long.parseLong(la));
				LandingPoints.add(landingpoint);
			}
		}

		// 更新数据
		GaCompany dey = gaCompanyRepository.findOne(Integer.parseInt(companyid));
		dey.setFlag((short) 4);
		gaCompanyRepository.save(dey);

	}

	public List<Object> getcountbytype(long listbysupid) {
		int typea = gaCompanyRepository.getcountbytype1(listbysupid);
		int typeb = gaCompanyRepository.getcountbytype2(listbysupid);
		int typec = gaCompanyRepository.getcountbytype3(listbysupid);
		int typed = gaCompanyRepository.getcountbytype4(listbysupid);
		List<Object> list = new ArrayList<>();
		list.add(typea);
		list.add(typeb);
		list.add(typec);
		list.add(typed);
		return list;
	}

	public List<Object> getcountbytypeandauthorityId(long authorityId) {
		int typea = gaCompanyRepository.getcountbytype1AndauthorityId(authorityId);
		int typeb = gaCompanyRepository.getcountbytype2AndauthorityId(authorityId);
		int typec = gaCompanyRepository.getcountbytype3AndauthorityId(authorityId);
		int typed = gaCompanyRepository.getcountbytype4AndauthorityId(authorityId);
		List<Object> list = new ArrayList<>();
		list.add(typea);
		list.add(typeb);
		list.add(typec);
		list.add(typed);
		return list;
	}

	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 *
	 * @Title: findGorgByName @param name @throws
	 *
	 */
	public Org findGorgByName(String name) {
		// TODO Auto-generated method stub
		return this.orgRepository.selectByNameAndOrgTypeone(name, 2);
	}

	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 *
	 * @Title: findSorgByName @param name @return @throws
	 *
	 */
	public Org findSorgByName(String name) {
		// TODO Auto-generated method stub
		return this.orgRepository.selectByNameAndOrgTypeone(name, 1);
	}

	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 *
	 * @Title: updateOne @param title @param id @throws
	 *
	 */
	public void updateOne(String title, Long id) {
		// TODO Auto-generated method stub
		gaCompanyRepository.updateOne(title, id);
	}

	/**
	 * 
	 * TODO(根据监管局id 法定代表人查询公司信息)
	 *
	 * @Title: selectBySupervisionIdAndLegalPerson @param supervision_id @param
	 *         pageable @param legalPerson @return @throws
	 *
	 */
	public Page<GaCompany> selectBySupervisionIdAndLegalPerson(Long supervision_id, Pageable pageable,
			String legalPerson) {
		return gaCompanyRepository.selectBySupervisionIdAndLegalPerson(supervision_id, pageable, legalPerson);
	}

	/**
	 * 
	 * TODO(根据监管局id 企业名称 和法人代表查询企业信息)
	 *
	 * @Title: selectBySupervisionIdAndCompanyNameAndLegalPerson @param
	 *         supervision_id @param pageable @param companyName @param
	 *         legalPerson @return @throws
	 *
	 */
	public Page<GaCompany> selectBySupervisionIdAndCompanyNameAndLegalPerson(Long supervision_id, Pageable pageable,
			String companyName, String legalPerson) {
		return gaCompanyRepository.selectBySupervisionIdAndCompanyNameAndLegalPerson(supervision_id, pageable,
				companyName, legalPerson);
	}

	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 *
	 * @Title: gets @param parseInt @return @throws
	 *
	 */
	public List<GaCompany> gets(String id) {

		return gaCompanyRepository.findByIdInss(id);
	}

	/**
	 * 根据企业ids查询企业信息
	 * 
	 * @param companyIds
	 * @param pageable
	 * @return
	 */
	public Page<GaCompany> selectByCompanyIds(String[] companyIds, Pageable pageable) {
		Page<GaCompany> pg = gaCompanyRepository.selectByCompanyIds(companyIds, pageable);
		System.out.println(pg.getNumberOfElements() + "....");

		return pg;
	}

	public Page<GaCompany> selectByCompanyIds1(String[] companyIds, Pageable pageable) {
		Page<GaCompany> pg = gaCompanyRepository.selectByCompanyIds(companyIds, pageable);
		System.out.println(pg.getNumberOfElements() + "....");
		for (GaCompany ga : pg) {
			List<OperationBase> listGc = operationRepo.findMainBasesByCompanyId(ga.getCompanyId());
			List<Object> listCq = cqRep.findTypeByCompanyId(ga.getCompanyId());
			String str = "";
			String str1 = "";
			for (int i = 0; i < listGc.size(); i++) {
				str = str + listGc.get(i).getBaseName() + ",";
			}
			for (int j = 0; j < listCq.size(); j++) {
				if (listCq.get(j) != null) {

					str1 = str1 + listCq.get(j).toString() + ",";
				}
			}

			if (str.length() != 0) {
				ga.setMainBase(str.substring(0, str.length() - 1));

			} else {
				ga.setMainBase(str);
			}
			if (str1.length() != 0) {
				ga.setBusinessScope(str1.substring(0, str1.length() - 1));
			} else {
				ga.setBusinessScope(str1);
			}

		}
		return pg;
	}

	/**
	 * 根据企业id查询企业信息
	 * 
	 * @param companyId
	 * @param pageable
	 * @return
	 */
	public Page<GaCompany> selectByCompanyIdsAndId(Long companyId, Pageable pageable) {
		return gaCompanyRepository.selectByCompanyIdsAndId(companyId, pageable);
	}

	public Page<GaCompany> selectByCompanyIdsAndId1(Long companyId, Pageable pageable) {
		Page<GaCompany> pg = gaCompanyRepository.selectByCompanyIdsAndId(companyId, pageable);
		System.out.println(pg.getNumberOfElements() + "....");
		for (GaCompany ga : pg) {
			List<OperationBase> listGc = operationRepo.findMainBasesByCompanyId(ga.getCompanyId());
			List<Object> listCq = cqRep.findTypeByCompanyId(ga.getCompanyId());
			String str = "";
			String str1 = "";
			for (int i = 0; i < listGc.size(); i++) {
				str = str + listGc.get(i).getBaseName() + ",";
			}
			for (int j = 0; j < listCq.size(); j++) {
				if (listCq.get(j) != null) {

					str1 = str1 + listCq.get(j).toString() + ",";
				}
			}
			if (str.length() != 0) {
				ga.setMainBase(str.substring(0, str.length() - 1));

			} else {
				ga.setMainBase(str);
			}
			if (str1.length() != 0) {
				ga.setBusinessScope(str1.substring(0, str1.length() - 1));
			} else {
				ga.setBusinessScope(str1);
			}

		}
		return pg;
	}

	/**
	 * 根据法人代表
	 * 
	 * @param companyIds
	 * @param legalPerson
	 * @param pageable
	 * @return
	 */
	public Page<GaCompany> selectByCompanyIdsAndLegalPerson(String[] companyIds, String legalPerson,
			Pageable pageable) {
		return gaCompanyRepository.selectByCompanyIdsAndLegalPerson(companyIds, legalPerson, pageable);
	}

	public Page<GaCompany> selectByCompanyIdsAndLegalPerson1(String[] companyIds, String legalPerson,
			Pageable pageable) {
		Page<GaCompany> pg = gaCompanyRepository.selectByCompanyIdsAndLegalPerson(companyIds, legalPerson, pageable);
		System.out.println(pg.getNumberOfElements() + "....");
		for (GaCompany ga : pg) {
			List<OperationBase> listGc = operationRepo.findMainBasesByCompanyId(ga.getCompanyId());
			List<Object> listCq = cqRep.findTypeByCompanyId(ga.getCompanyId());
			String str = "";
			String str1 = "";
			for (int i = 0; i < listGc.size(); i++) {
				str = str + listGc.get(i).getBaseName() + ",";
			}
			for (int j = 0; j < listCq.size(); j++) {
				if (listCq.get(j) != null) {

					str1 = str1 + listCq.get(j).toString() + ",";
				}
			}

			if (str.length() != 0) {
				ga.setMainBase(str.substring(0, str.length() - 1));

			} else {
				ga.setMainBase(str);
			}
			if (str1.length() != 0) {
				ga.setBusinessScope(str1.substring(0, str1.length() - 1));
			} else {
				ga.setBusinessScope(str1);
			}

		}
		return pg;
	}

	/**
	 * 根据企业和法人代表查询企业信息
	 * 
	 * @param companyIds
	 * @param companyId
	 * @param legalPerson
	 * @param pageable
	 * @return
	 */
	public Page<GaCompany> selectAllByCompanyIds(String[] companyIds, Long companyId, String legalPerson,
			Pageable pageable) {
		return gaCompanyRepository.selectAllByCompanyIds(companyIds, companyId, legalPerson, pageable);
	}

	public Page<GaCompany> selectAllByCompanyIds1(String[] companyIds, Long companyId, String legalPerson,
			Pageable pageable) {
		Page<GaCompany> pg = gaCompanyRepository.selectAllByCompanyIds(companyIds, companyId, legalPerson, pageable);
		System.out.println(pg.getNumberOfElements() + "....");
		for (GaCompany ga : pg) {
			List<OperationBase> listGc = operationRepo.findMainBasesByCompanyId(ga.getCompanyId());
			List<Object> listCq = cqRep.findTypeByCompanyId(ga.getCompanyId());
			String str = "";
			String str1 = "";
			for (int i = 0; i < listGc.size(); i++) {
				str = str + listGc.get(i).getBaseName() + ",";
			}
			for (int j = 0; j < listCq.size(); j++) {
				if (listCq.get(j) != null) {

					str1 = str1 + listCq.get(j).toString() + ",";
				}
			}
		
			if (str.length() != 0) {
				ga.setMainBase(str.substring(0, str.length() - 1));

			} else {
				ga.setMainBase(str);
			}
			if (str1.length() != 0) {
				ga.setBusinessScope(str1.substring(0, str1.length() - 1));
			} else {
				ga.setBusinessScope(str1);
			}

		}
		return pg;
	}

	/**
	 * 根据企业ids查询下拉框企业名称
	 * 
	 * @param companyIds
	 * @return
	 */
	public List<GaCompany> selectByCompanyIds1(String[] companyIds) {
		return gaCompanyRepository.selectByCompanyIds1(companyIds);
	}

	/**
	 * 根据监管局ids查询飞行记录时间
	 * 
	 * @param supervisionIds
	 * @return
	 */
	public List<Object> staticFlightTimeBySupervisionIds(String[] supervisionIds) {
		return gaCompanyRepository.staticFlightTimeBySupervisionIds(supervisionIds);
	}

	/**
	 * 根据监管局id查询飞行记录时间
	 * 
	 * @param supervisionId
	 * @return
	 */
	public List<Object> staticFlightTimeBySupervisionId(String supervisionId) {
		return gaCompanyRepository.staticFlightTimeBySupervisionId(supervisionId);
	}

	/**
	 * 根据监管局ids和日期统计企业飞行时间
	 * 
	 * @param supervisionIds
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<Object> staticFlightTimeBySupIdsAndDate(String[] supervisionIds, String beginDate, String endDate) {
		return gaCompanyRepository.staticFlightTimeBySupIdsAndDate(supervisionIds, beginDate, endDate);
	}

	/**
	 * 根据监管局ids和工作类别统计企业飞行时间
	 * 
	 * @param supervisionIds
	 * @param flightNature
	 * @return
	 */
	public List<Object> staticFlightTimeBySupIdsAndNature(String[] supervisionIds, Integer flightNature) {
		return gaCompanyRepository.staticFlightTimeBySupIdsAndNature(supervisionIds, flightNature);
	}

	/**
	 * 根据日期和工作类别统计企业飞行时间
	 * 
	 * @param supervisionIds
	 * @param beginDate
	 * @param endDate
	 * @param flightNature
	 * @return
	 */
	public List<Object> staticFlightTimeBySupIdsAndDateAndNature(String[] supervisionIds, String beginDate,
			String endDate, Integer flightNature) {
		return gaCompanyRepository.staticFlightTimeBySupIdsAndDateAndNature(supervisionIds, beginDate, endDate,
				flightNature);
	}

	/**
	 * 根据日期和监管局id统计企业飞行时间
	 * 
	 * @param supervisionId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<Object> staticFlightTimeByDateAndSupId(String supervisionId, String beginDate, String endDate) {
		return gaCompanyRepository.staticFlightTimeByDateAndSupId(supervisionId, beginDate, endDate);
	}

	/**
	 * 根据工作类别和监管局统计飞行时间
	 * 
	 * @param supervisionId
	 * @param flightNature
	 * @return
	 */
	public List<Object> staticFlightTimeByNatureAndSupId(String supervisionId, Integer flightNature) {
		return gaCompanyRepository.staticFlightTimeByNatureAndSupId(supervisionId, flightNature);
	}

	/**
	 * 根据所有条件统计企业飞行时间
	 * 
	 * @param supervisionId
	 * @param beginDate
	 * @param endDate
	 * @param flightNature
	 * @return
	 */
	public List<Object> staticFlightTimeByAll(String supervisionId, String beginDate, String endDate,
			Integer flightNature) {
		return gaCompanyRepository.staticFlightTimeByAll(supervisionId, beginDate, endDate, flightNature);
	}

	/**
	 * 根据日期和企业id统计企业飞行时长
	 * 
	 * @param beginDate
	 * @param endDate
	 * @param companyId
	 * @return
	 */
	public List<Object> staticFlightCountsByDateAndCompanyId(Date beginDate, Date endDate, String[] companyIds,
			String[] flightNatures) {
		List<Object> listObjs = new ArrayList<Object>();
		for (int i = 0; i < companyIds.length; i++) {
			List<Object> listObj = gaCompanyRepository.staticFlightCountsByDateAndCompanyId(beginDate, endDate,
					companyIds[i], flightNatures);
			listObjs.add(listObj);
		}
		return listObjs;
	}

	/**
	 * 根据监管局ids统计企业经营类别
	 * 
	 * @param authId
	 * @return
	 */
	public Object staticBusinessTypeBySuperIds(String[] superIds) {
		List<String> obj = gaCompanyRepository.staticBusinessTypeBySuperIds(superIds);
		System.out.println(obj);
		List<String> strList = new ArrayList<String>();
		for (String str : obj) {
			String[] strArray = str.split(",");
			for (int i = 0; i < strArray.length; i++) {
				strList.add(strArray[i]);
			}
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("甲", Collections.frequency(strList, "1"));
		map.put("乙", Collections.frequency(strList, "2"));
		map.put("丙", Collections.frequency(strList, "3"));
		map.put("丁", Collections.frequency(strList, "4"));
		return map;
	}

	/**
	 * 根据监管局ids初始化统计飞行时间
	 * 
	 * @param supervisionIds
	 * @param beginDate
	 * @param endDate
	 * @param FlightNatures
	 * @return
	 */
	public List<Object> staticFlight(String[] supervisionIds, String beginDate, String endDate,
			String[] FlightNatures) {
		return gaCompanyRepository.staticFlight(supervisionIds, beginDate, endDate, FlightNatures);
	}

	/**
	 * 根据监管局ids查询所有企业
	 * 
	 * @param supervisionIds
	 * @return
	 */
	public List<Object> selectCompanysBySupervisionIds(String[] supervisionIds) {
		return gaCompanyRepository.selectCompanysBySupervisionIds(supervisionIds);
	}

	/**
	 * 根据企业ids查询企业名称
	 * 
	 * @param companyIds
	 * @return
	 */
	public List<Object> selectCompanyNamesByCompanyIds(String[] companyIds) {
		List<Object> listObj = new ArrayList<Object>();
		for (int i = 0; i < companyIds.length; i++) {
			List<Object> obj = gaCompanyRepository.selectCompanyNamesByCompanyId(companyIds[i]);
			listObj.add(obj);
		}
		return listObj;
	}

	/**
	 * 根据部门id查询名称
	 * 
	 * @param id
	 * @return
	 */
	public String selectSupvisionNameBySupId(String id) {
		String str = "";
		if (id != null || id != "") {
			str = gaCompanyRepository.selectSupvisionNameBySupId(id);
		}
		return str;
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: staticFlights 
	 * @param companyId
	 * @param beginDate
	 * @param endDate
	 * @param flightNatures
	 * @return  
	 * @throws
	 *
	 */
	public List<Object> staticFlights(String companyId, String beginDate, String endDate) {
		// TODO Auto-generated method stub
		return gaCompanyRepository.staticFlights(companyId, beginDate, endDate);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: staticFlightsv 
	 * @param companyId
	 * @param beginDate
	 * @param endDate
	 * @return  
	 * @throws
	 *
	 */
	public List<Object> staticFlightsv(String companyId, String beginDate, String endDate) {
		// TODO Auto-generated method stub
		return gaCompanyRepository.staticFlightsv(companyId, beginDate, endDate);
	}
}
