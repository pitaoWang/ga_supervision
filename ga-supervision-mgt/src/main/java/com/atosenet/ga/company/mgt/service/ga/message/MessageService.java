package com.atosenet.ga.company.mgt.service.ga.message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.CompanyQualification;
import com.atosenet.ga.company.db.model.ga.employe.QualificationConfigure;
import com.atosenet.ga.company.db.model.ga.message.vo.MessageDto;
import com.atosenet.ga.company.db.model.ga.message.vo.MessageVO;
import com.atosenet.ga.company.db.repos.ga.company.CompanyQualificationRepository;
import com.atosenet.ga.company.db.repos.ga.employe.QualificationConfigureRepository;
import com.atosenet.ga.company.db.repos.ga.message.MessageRepository;
import com.atosenet.ga.company.db.repos.ga.message.MessageDtoRepository;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private QualificationConfigureRepository qualConfigureRep;
	
	@Autowired
	private MessageDtoRepository messageDtoRepository;
	
	@Autowired
	private CompanyQualificationRepository comQualRep;
	
	/**
	 * 分页获取到期提醒信息
	 * 
	 * @param companyIds
	 * @param pageable
	 * @return
	 */
	public Page<MessageVO> findListByCompanyIds(String companyIds, Pageable pageable) {
		List<Long> list = Arrays.asList(companyIds.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");              
		 
		 Calendar c = Calendar.getInstance();         
		 Date mondays = c.getTime();       
		  
		 String preMondays = sdf.format(mondays);       
		 c.add(Calendar.DATE, - 7);         
		  
		 Date monday = c.getTime();       
		  
		 String preMonday = sdf.format(monday);       
		System.out.println(preMonday);
		System.out.println(preMondays);		
		return messageRepository.findRecordByCompanyIds(list,mondays,monday,pageable);
	}
	
	public Page<MessageDto> findListByCompanyIds1(String companyIds, Pageable pageable) {
		List<Long> list = Arrays.asList(companyIds.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
		
		//没有配置得企业id
		List<Long> listArray = new ArrayList<Long>();
		for(Long id : list){
			//查询没有配置消息提醒得企业id
			QualificationConfigure listQual = qualConfigureRep.findNoConfigureById(id);
			//如果为空则添加到listArray
			if(null==listQual){
				listArray.add(id);				
			}
			
		}
		//如果listArray为空
		if(listArray.size()==0){
			return messageDtoRepository.findRecordByCompanyIds2(list,pageable);
		}
		
		return messageDtoRepository.findRecordByCompanyIds1(list,listArray,pageable);
	}
	
	/**
	 * 企业资质消息提醒
	 * @param companyIds
	 * @param pageable
	 * @return
	 */
	public Page<CompanyQualification> findRecordByCompanyId(String companyIds, Pageable pageable) {
		List<Long> list = Arrays.asList(companyIds.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
		
		//没有配置得企业id
		List<Long> listArray = new ArrayList<Long>();
		for(Long id : list){
			//查询没有配置消息提醒得企业id
			QualificationConfigure listQual = qualConfigureRep.findNoConfigureById(id);
			//如果为空则添加到listArray
			if(null==listQual){
				listArray.add(id);				
			}
			
		}
		//
		if(listArray.size()==0){
			return comQualRep.findRecordByCompanyId1(list,pageable);
		}
		return comQualRep.findRecordByCompanyId(list,listArray,pageable);
	}
	
}
