package com.atosenet.ga.company.mgt.service.ga.mmis;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atosenet.ga.company.db.model.ga.mmis.Evaluate;
import com.atosenet.ga.company.db.repos.ga.mmis.EvaluateRepository;

@Service
public class EvaluateService {

	@Autowired
	private EvaluateRepository evaluateRepository;
	
	/**
	 * 根据条件分页获取当前管理局下的适航指令/服务通告评估记录
	 * 
	 * @param no
	 * @param asInformNo
	 * @param executeWay
	 * @param status
	 * @param mid
	 * @param pageable
	 * @return
	 */
	public Page<Evaluate> findEvaluateListByPage(String no, String asInformNo, String executeWay, String status, String companyIds, Pageable pageable) {
		
		List<Long> listIds = Arrays.asList(companyIds.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
		return evaluateRepository.findEvaluateListByPage(no, asInformNo, executeWay, status, listIds, pageable);
	}
	
	/**
	 * 根据ID获取适航指令/服务通告评估记录详情
	 * 
	 * @param id
	 * @return
	 */
	public Evaluate findById(String id) {
		return evaluateRepository.findOne(id);
	}
}
