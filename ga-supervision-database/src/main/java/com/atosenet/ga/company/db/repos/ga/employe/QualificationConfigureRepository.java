package com.atosenet.ga.company.db.repos.ga.employe;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.atosenet.ga.company.db.model.ga.employe.QualificationConfigure;

public interface QualificationConfigureRepository extends PagingAndSortingRepository<QualificationConfigure,Integer> {
	
	//根据企业id查询没有配置得消息提醒时间
	@Query(value = "select q from QualificationConfigure q where q.companyId=?1")
	QualificationConfigure findNoConfigureById(Long companyId);
}
