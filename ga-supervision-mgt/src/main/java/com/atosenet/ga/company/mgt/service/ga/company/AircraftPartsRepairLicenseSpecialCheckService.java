package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.AircraftPartsRepairLicenseProjectCheck;
import com.atosenet.ga.company.db.model.ga.company.AircraftPartsRepairLicenseSpecialCheck;
import com.atosenet.ga.company.db.repos.ga.company.AircraftPartsRepairLicenseSpecialCheckRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class AircraftPartsRepairLicenseSpecialCheckService {

	@Autowired
	private AircraftPartsRepairLicenseSpecialCheckRepository apr;
	/**
	 * 保存专业续签记录
	 */
	@Transactional(readOnly=false)
	public AircraftPartsRepairLicenseSpecialCheck save(AircraftPartsRepairLicenseSpecialCheck a)
	{
		return apr.save(a);
	}

	public AircraftPartsRepairLicenseSpecialCheck getId(long id)
	{
		return apr.findOne(id);
	}

	public List<AircraftPartsRepairLicenseSpecialCheck> findAll()
	{
		return apr.findList();
	}
	/**
	 * 根据资质ID查询
	 * @param id
	 * @return
	 */
	public List<AircraftPartsRepairLicenseSpecialCheck> findByLicenseId(long id)
	{
		return apr.findListBylicenseId(id);
	}
	public void delete(long id)
	{
		apr.delete(id);
	}
	public void deleteByLicense(long id)
	{
		apr.deleteByLicenseId(id);
	}
}
