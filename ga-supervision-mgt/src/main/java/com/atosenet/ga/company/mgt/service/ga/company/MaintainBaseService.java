package com.atosenet.ga.company.mgt.service.ga.company;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.GaMatianBaseRefere;
import com.atosenet.ga.company.db.model.ga.company.MaintainBase;
import com.atosenet.ga.company.db.repos.ga.company.GaMatianBaseRefereRepository;
import com.atosenet.ga.company.db.repos.ga.company.MaintainBaseRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class MaintainBaseService {
	@Autowired  MaintainBaseRepository MaintainBaseRepository;
	@Autowired  GaMatianBaseRefereRepository  GaMatianBaseRefereRepository;
	
	@Transactional(readOnly=false)
    public void save(MaintainBase MaintainBase){
		MaintainBaseRepository.save(MaintainBase);
//		MaintainBase.getMaintainbaseid();
//		GaMatianBaseRefere  gamatianbaserefere=new GaMatianBaseRefere();
//		gamatianbaserefere.setMaintainbaseid(MaintainBase.getMaintainbaseid());
//		GaMatianBaseRefereRepository.save(gamatianbaserefere);	
    }
	
	public List<Map<String, Object>> getMaintainBaseByCompanyId(long companyId){
		List<Map<String, Object>> list = new ArrayList<>();
		 List<Object[]> st = MaintainBaseRepository.findlistbycompanyid(companyId);
		  for(Object[] obj:st){
			 Map<String, Object> map = new HashMap<>();
			 map.put("maintainbaseid", obj[0]);
			 map.put("maintainbasename", obj[1]);
			 map.put("companyId", obj[2]);
			 list.add(map);
		 }
		return list;	 
    } 
	
	//删除维修基地
	@Transactional(readOnly=false)
    public void deleteMaintainBase(Long MaintainBase){
		MaintainBaseRepository.delete(MaintainBase);
		MaintainBaseRepository.deleteMaintainbasebycompanyid(MaintainBase);
    }
	@Transactional(readOnly=false)
    public List<MaintainBase> getlistbymainrightcompanyid(Long companyid){
		return MaintainBaseRepository.getlistbymainrightcompanyid(companyid);
    }
	
}
