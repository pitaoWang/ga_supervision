package com.atosenet.ga.sys.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.FocusCompany;
import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.repos.ga.company.GaCompanyRepository;
import com.atosenet.ga.sys.dao.FocusCompanyDao;
import com.google.common.base.Joiner;

@Service
@Transactional
public class FocusCompanyService {

	@Autowired
	private GaCompanyRepository gaCompanyRepository;
	
	@Autowired
	private FocusCompanyDao focusCompanyDao;
	
	private static final String M_TYPE = "1";
	
	private static final String S_TYPE = "2";
	
	/**
	 * 获取监管局或管理局下的企业   返回企业ID 列表  转换成逗号分隔的字符串
	 * 
	 * @param mangeId
	 * @param type
	 * @return
	 */
	public String getCompanyIdsByManageId(Long mangeId, String type) {
		String ids = "";
		if(M_TYPE.equals(type)) {
			List<GaCompany> comapnayList = gaCompanyRepository.findByAuthorityIdList(mangeId);
			List<Integer> idList = comapnayList.stream().map(GaCompany::getCompanyId).collect(Collectors.toList());
			ids = Joiner.on(",").join(idList);
		} else if(S_TYPE.equals(type)) {
			List<GaCompany> comapnayList = gaCompanyRepository.findBySupervisionIdList(mangeId);
			List<Integer> idList = comapnayList.stream().map(GaCompany::getCompanyId).collect(Collectors.toList());
			ids = Joiner.on(",").join(idList);
		}
		return ids;
	}
	
	/**
	 * 根据管理局或监管局ID获取企业列表
	 * 
	 * @param mangeId
	 * @param type
	 * @param pageable
	 * @return
	 */
	public Page<GaCompany> getCompanyListByManageIdAndPage(Long manageId, String type, Pageable pageable) {
		Page<GaCompany> comapnayList = null;
		if(M_TYPE.equals(type)) {
			comapnayList = gaCompanyRepository.selectByAuthorityId(manageId, pageable);
		} else if(S_TYPE.equals(type)) {
			comapnayList = gaCompanyRepository.selectBySupervisionId(manageId, pageable);
		}
		return comapnayList;
	}
	
	/**
	 * 根据管理局或监管局ID获取企业列表
	 * 
	 * @param manageId
	 * @param type
	 * @param pageable
	 * @return
	 */
	public List<GaCompany> getCompanyListByManageId(Long manageId, String type) {
		List<GaCompany> comapnayList = null;
		if(M_TYPE.equals(type)) {
			comapnayList = gaCompanyRepository.findByAuthorityIdList(manageId);
		} else if(S_TYPE.equals(type)) {
			comapnayList = gaCompanyRepository.findBySupervisionIdList(manageId);
		}
		if(focusCompanyDao.findCompanyIdsBySuperId(manageId)!=null){
			String ids[] = focusCompanyDao.findCompanyIdsBySuperId(manageId).split(",");
			List<Integer> idv = new ArrayList<>();
			for(int i = 0;i<ids.length;i++){
				idv.add(Integer.valueOf(ids[i]));
			}
			
			for(int i = 0 ;i<comapnayList.size();i++){
				
					if(!idv.contains(comapnayList.get(i).getCompanyId())){
						
						comapnayList.get(i).set_checked(false);
					}
				
			}
		}
		
		
		return comapnayList;
	}
	
	
	/**
	 * 
	 * @param superId
	 * @return
	 */
	public String getCompanyIdsBySuperId(Long superId) {
		return focusCompanyDao.findCompanyIdsBySuperId(superId);
	}
	
	/**
	 * 
	 * @param manageId
	 */
	public void deleteByManageId(Long manageId) {
		focusCompanyDao.deleteBySupserId(manageId);
	}
	
	/**
	 * 
	 * @param focusCompany
	 */
	public FocusCompany saveFocusCompany(FocusCompany focusCompany) {
		return focusCompanyDao.save(focusCompany);
	}
	
}
