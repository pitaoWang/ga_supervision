package com.atosenet.ga.company.mgt.controller.ga.employe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.core.common.model.response.BaseResponse;
import com.atosenet.ga.company.db.model.ga.company.AircraftRepairLicense;
import com.atosenet.ga.company.db.model.ga.employe.PersonQulificationInfo;
import com.atosenet.ga.company.db.model.ga.employe.PilotLicense;
import com.atosenet.ga.company.db.model.ga.employe.PilotLicenseCheckRecord;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.company.AircraftRepairLicenseService;
import com.atosenet.ga.company.mgt.service.ga.employe.PersonQulificationInfoService;
import com.atosenet.ga.company.mgt.service.ga.employe.PilotLicenseService;
import com.atosenet.ga.company.mgt.service.ga.employe.dto.AircraftRepairLicenseDTO;
import com.atosenet.ga.company.mgt.service.ga.employe.dto.ControlLicenseDTO;
import com.atosenet.ga.company.mgt.service.ga.employe.dto.PersonQulificationInfoDTO;
import com.atosenet.ga.company.mgt.service.ga.employe.dto.QualificationExtDTO;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/qulification")
@Api(tags="人员资质的维护")
public class QulificationController {
	@Autowired
	PersonQulificationInfoService personQulificationInfoService;
	@Autowired
	AircraftRepairLicenseService apr;
	@Autowired
	PilotLicenseService ps;
	
	/**
	 * 新增飞行员资质
	 * @param PersonQulificationInfo
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "创建人员资质信息")
	public DataResponse<PersonQulificationInfo> saveQulificationInfo(@RequestBody PersonQulificationInfo info)
	{
		long start = System.currentTimeMillis();
		
		DataResponse<PersonQulificationInfo> response = new DataResponse<PersonQulificationInfo>();
		
		try 
		{
			PersonQulificationInfo result = personQulificationInfoService.save(info);
			response.setData(result);
			response.setResultCode(Constants.OK);
			
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		finally 
		{
			System.out.print("time = "+(System.currentTimeMillis() - start));
		}
		
		return response;
	}
	
	@RequestMapping(value = "/save/politlicen", method = RequestMethod.POST)
    @ApiOperation(value = "创建飞行资质信息")
	@ApiIgnore
	@ApiImplicitParam(
			value="飞行资质的添加",name="PersonQulificationInfoDTO",dataType="PersonQulificationInfoDTO")
	public DataResponse<PersonQulificationInfo> saveQulificationInfoPolitlicenr(@RequestBody PersonQulificationInfoDTO PersonQulificationInfoDTO)
	{

		PersonQulificationInfo info=PersonQulificationInfoDTO.getPersonQulificationInfo();
		PilotLicense PilotLicense=PersonQulificationInfoDTO.getPilotLicense();
		List<PilotLicenseCheckRecord> licenseChecks=PersonQulificationInfoDTO.getLicenseChecks();
		DataResponse<PersonQulificationInfo> response = new DataResponse<PersonQulificationInfo>();
		long start = System.currentTimeMillis();
		try 
		{
			PersonQulificationInfo result = personQulificationInfoService.save(info,PilotLicense,licenseChecks);
			response.setData(result);
			response.setResultCode(Constants.OK);
			
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		finally 
		{
			System.out.print("time = "+(System.currentTimeMillis() - start));
		}
		
		return response;
	}
	
	/**
	 * 根据employeeId查询人员资质列表
	 * @param PersonQulificationInfo
	 * @return
	 */ 
	@RequestMapping(value = "/search/findByEmployeeId/{employeeId}", method = RequestMethod.GET)
	@ApiOperation(value = "根据人员系统id查询人员执照")
	@ApiImplicitParam(
			value="人员系统id",name="employeeId",dataType="Long",paramType="path")
	public List<PersonQulificationInfo> findByEmployeeId(@PathVariable("employeeId") Long employeeId){
		
		 List<PersonQulificationInfo> datas = personQulificationInfoService.findByEmployeeId(employeeId);
		 return datas;
	}
	
	/**
	 * 根据employeeId和护照id和护照类型查个人资质信息查询人员资质信息
	 * @param PersonQulificationInfo
	 * @return
	 */
	@RequestMapping(value = "/search/getinfoby", method = RequestMethod.GET)
	@ApiOperation(value = "根据飞行执照id查询执照详情")
	@ApiIgnore
	@ApiImplicitParam(
			value="执照id",name="licenseId",dataType="string",paramType="query")
	public DataResponse<PersonQulificationInfoDTO> queryQulificationInfo1Employee(@RequestParam("licenseId") String licenseId)
	{
		long start = System.currentTimeMillis();
		
		DataResponse<PersonQulificationInfoDTO> response = new DataResponse<PersonQulificationInfoDTO>();
		
		try 
		{
			PersonQulificationInfoDTO result = personQulificationInfoService.getPersonQulificationInfoById1(licenseId);
			response.setData(result);
			
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		finally 
		{
			System.out.print("time = "+(System.currentTimeMillis() - start));
		}
		
		return response;
	}
	
	/**
	 * 根据航空器维修执照id查询 信息
	 */
	@RequestMapping(value = "/search/getByaricraftrepairId", method = RequestMethod.GET)
	@ApiOperation(value = "根据航空器维修执照id查询 信息")
	@ApiIgnore
	@ApiImplicitParam(
			value="执照id",name="licenseId",dataType="Long",paramType="query")
	public DataResponse<AircraftRepairLicenseDTO> getbyId(Long licenseId){
		DataResponse<AircraftRepairLicenseDTO> response = new DataResponse<>();
		AircraftRepairLicenseDTO result = new AircraftRepairLicenseDTO();
		try {
			PersonQulificationInfo data = personQulificationInfoService.getId(licenseId);
			AircraftRepairLicense adata = this.apr.getId(licenseId);
			result.setPersonQulificationInfo(data);
			result.setAircraftrepair(adata);
			response.setResultCode(Constants.OK);
			response.setData(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	/**
	 * 根据管制执照id  查询基础执照信息  签注信息
	 */
	@RequestMapping(value="/search/getbycontrolid", method=RequestMethod.GET)
	@ApiOperation(value = "根据管制执照id查询执照详情")
	@ApiIgnore
	@ApiImplicitParam(
			value="执照id",name="licenseId",dataType="string",paramType="query")
	public DataResponse<ControlLicenseDTO> getDataByid(@RequestParam("licenseId") String licenseId){
		DataResponse<ControlLicenseDTO> response = new DataResponse<>();
		
		try {
			ControlLicenseDTO result=this.personQulificationInfoService.getControlLicenseInfo(licenseId);
			response.setData(result);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;	
	}
	
	
	
	/**
	 * 
	 * @param PersonQulificationInfo
	 * @return
	 */
	@RequestMapping(value = "/save/license", method = RequestMethod.POST)
    @ApiOperation(value = "创建各种类型人员资质信息")
	@ApiIgnore
	@ApiImplicitParam(
			value="各种类型人员资质实体对象",name="info",dataType="QualificationExtDTO")
	public DataResponse<PersonQulificationInfo> saveLicense(@RequestBody QualificationExtDTO info)
	{
		long start = System.currentTimeMillis();
		
		DataResponse<PersonQulificationInfo> response = new DataResponse<PersonQulificationInfo>();
		
		try 
		{
			personQulificationInfoService.saveLicense(info);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		finally 
		{
			System.out.print("time = "+(System.currentTimeMillis() - start));
		}
		
		return response;
	}
	

	/**
	 * 查询人员资质信息以及执照信息
	 * 
	 * 
	 */
	//@RequestParam("id") String id,@RequestParam("licensetype") String licensetype
	@RequestMapping(value = "/search/license", method = RequestMethod.GET)
    @ApiOperation(value = "查各种人员资质信息以及执照信息")
	@ApiIgnore
	@ApiImplicitParams({
		@ApiImplicitParam(
				value="执照id",name="id",dataType="string",paramType="query"),
		@ApiImplicitParam(
				value="执照类型",name="licensetype",dataType="string",paramType="query")
	})
	public DataResponse<QualificationExtDTO> searchLicenseByIdAndType(@RequestParam("id") String id,@RequestParam("licensetype") String licensetype)
	{
		long start = System.currentTimeMillis();
		System.out.println("根据id和类型查询人员资质信息以及执照信息=====");
		DataResponse<QualificationExtDTO> response = new DataResponse<QualificationExtDTO>();
		
		try 
		{
			QualificationExtDTO result=personQulificationInfoService.searchLicenseByIdAndType(id,licensetype);
			response.setData(result);
			
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		finally 
		{
			System.out.print("time = "+(System.currentTimeMillis() - start));
		}
		
		return response;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ApiOperation(value = "删除员工当前资质信息")
	@ApiIgnore
	public void deleteById(@RequestBody PersonQulificationInfo info) {
		personQulificationInfoService.deleteLicense(info);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "根据资质id删除当前资质信息")
	@ApiImplicitParams({
		@ApiImplicitParam(
				value="资质id",name="id",dataType="string",paramType="path"),
	})
	public BaseResponse deleteByquaId(@PathVariable String id) {
		BaseResponse response = new BaseResponse();
		try {
			personQulificationInfoService.delete(Long.parseLong(id));
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	
	
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiOperation(value = "根据资质id获取资质详情")
	public PersonQulificationInfo getById(@PathVariable("id") Long id) {
		
		return personQulificationInfoService.getId(id);
	}
	
}

