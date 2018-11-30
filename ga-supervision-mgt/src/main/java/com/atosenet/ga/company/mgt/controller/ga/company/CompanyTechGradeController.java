package com.atosenet.ga.company.mgt.controller.ga.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.atosenet.ga.company.db.repos.ga.company.CompanyTechGradeRepository;

import com.atosenet.ga.company.db.model.ga.company.CompanyTechGrade;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/app/api/companytechgrade")
@CrossOrigin("*")
@ApiIgnore
public class CompanyTechGradeController {
	@Autowired
	CompanyTechGradeRepository CompanyTechGradeRepository;
	
	@RequestMapping(value = { "list", "" }, method = RequestMethod.GET)
	@ApiOperation(value = "获取所有技术级别信息")
	public List<CompanyTechGrade> getAllTest() {
		return CompanyTechGradeRepository.findList();
	}
}
