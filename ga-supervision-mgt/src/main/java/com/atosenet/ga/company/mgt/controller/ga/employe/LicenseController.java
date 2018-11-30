package com.atosenet.ga.company.mgt.controller.ga.employe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.core.common.model.response.BaseResponse;
import com.atosenet.ga.company.db.model.ga.company.AircraftPartsRepairLicenseProjectCheck;
import com.atosenet.ga.company.db.model.ga.company.AircraftPartsRepairLicenseSpecialCheck;
import com.atosenet.ga.company.db.model.ga.company.AircraftRepairLicenseModelCheck;
import com.atosenet.ga.company.db.model.ga.company.AircraftRepairLicenseSpecialCheck;
import com.atosenet.ga.company.db.model.ga.employe.PilotLicense;
import com.atosenet.ga.company.db.model.ga.employe.PilotLicenseCheckRecord;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.company.AircraftPartsRepairLicenseProjectCheckService;
import com.atosenet.ga.company.mgt.service.ga.company.AircraftPartsRepairLicenseSpecialCheckService;
import com.atosenet.ga.company.mgt.service.ga.company.AircraftRepairLicenseModelCheckService;
import com.atosenet.ga.company.mgt.service.ga.company.AircraftRepairLicenseSpecialCheckService;
import com.atosenet.ga.company.mgt.service.ga.employe.PilotLicenseCheckService;
import com.atosenet.ga.company.mgt.service.ga.employe.PilotLicenseService;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/license")
@Api(tags="飞行执照的维护及所有签注维护")
public class LicenseController {
	@Autowired
	PilotLicenseService pilotLicenseService;
	
	@Autowired
	PilotLicenseCheckService pilotLicenseCheckService ;
	
	@Autowired
	AircraftRepairLicenseModelCheckService arlmcs;
	
	@Autowired
	AircraftRepairLicenseSpecialCheckService arlscs;
	
	@Autowired
	AircraftPartsRepairLicenseProjectCheckService aprlpcs;
	
	@Autowired
	AircraftPartsRepairLicenseSpecialCheckService aprlscs;
	
	/**
	 * 新增飞行员执照信息
	 * @param PersonQulificationInfo
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "创建飞行人员执照信息")
	@ApiImplicitParam(
			value="实体类",name="license",dataType="PilotLicense")
	public DataResponse<PilotLicense> saveQulificationInfo(@RequestBody PilotLicense license)
	{
		long start = System.currentTimeMillis();
		
		DataResponse<PilotLicense> response = new DataResponse<PilotLicense>();
		
		try 
		{
			PilotLicense result = pilotLicenseService.sava(license);
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
	
	/*
	 * 查询所有飞行执照
	 */
	@RequestMapping(value = "/pilotLicense/searchAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有飞行执照")
	public DataResponse<List<PilotLicense>> getAllPilotLicense(){
		DataResponse<List<PilotLicense>> response = new DataResponse<>();
		try {
			List<PilotLicense> pl=	this.pilotLicenseService.getAll();
			response.setData(pl);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	/*
	 * 根据id删除飞行执照
	 */
	@RequestMapping(value = "/delete/pilotLicense/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除飞行执照")
	@ApiImplicitParam(
			value="飞行执照id",name="id",dataType="String",paramType="path")
	public BaseResponse delPilotLicenseById(@PathVariable String id){
		BaseResponse response = new BaseResponse();
		try {
			this.pilotLicenseService.deleteById(id);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	
	
	/**
	 * 
	 * @param PersonQulificationInfo
	 * @return
	 */
	@RequestMapping(value = "/check/save", method = RequestMethod.POST)
    @ApiOperation(value = "新增/编辑执照签注信息")
	@ApiImplicitParam(
			value="实体类",name="record",dataType="PilotLicenseCheckRecord")
	public DataResponse<PilotLicenseCheckRecord> saveLicenseCheck(@RequestBody PilotLicenseCheckRecord record)
	{
		long start = System.currentTimeMillis();
		
		DataResponse<PilotLicenseCheckRecord> response = new DataResponse<PilotLicenseCheckRecord>();
		
		try 
		{
			PilotLicenseCheckRecord result = pilotLicenseCheckService.sava(record);
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
	 * 
	 * @param PersonQulificationInfo
	 * @return
	 */
	@RequestMapping(value = "/check/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除执照签注信息")
	public DataResponse<Long> saveLicenseCheck(@PathVariable("id") Long recordId)
	{
		long start = System.currentTimeMillis();
		
		DataResponse<Long> response = new DataResponse<Long>();
		
		try 
		{
			Long result = pilotLicenseCheckService.delete(recordId);
			if(result > 0)
			{
				response.setData(result);
				response.setResultCode(Constants.OK);
			}
			else
			{
				response.setData(result);
				response.setResultCode(Constants.NOT_FOUND);
			}
			
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
	
	@RequestMapping(value="/check/deletebatch/{ids}",method=RequestMethod.DELETE)
	@ApiOperation(value="通过id批量删除签注记录")
	@ApiIgnore
	public BaseResponse deleteBatch(@PathVariable("ids") List<Long> ids){
		BaseResponse response = new BaseResponse();
	       try {
			this.pilotLicenseCheckService.deleteBatch(ids);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/check/search/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据查询执照签注记录")
	@ApiImplicitParam(
			value="签注id",name="id",dataType="String",paramType="path")
	public DataResponse<PilotLicenseCheckRecord> getById(@PathVariable String id){
		DataResponse<PilotLicenseCheckRecord> response = new DataResponse<>();
		try {
			PilotLicenseCheckRecord pc=	this.pilotLicenseCheckService.findById(Long.parseLong(id));
			response.setData(pc);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@RequestMapping(value = "/check/searchAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有执照签注记录")
	public DataResponse<List<PilotLicenseCheckRecord>> getAll(){
		DataResponse<List<PilotLicenseCheckRecord>> response = new DataResponse<>();
		try {
			List<PilotLicenseCheckRecord> pc=	this.pilotLicenseCheckService.findAll();
			response.setData(pc);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	/**
	 * 根据licenseId查询飞行执照的签注记录
	 * @param PersonQulificationInfo
	 * @return
	 */
	@RequestMapping(value = "/search/{licenseId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询飞行员等所有执照签注记录")
	@ApiIgnore
	@ApiImplicitParam(
			value="执照id",name="licenseId",dataType="String",paramType="path")
	public DataResponse<List<PilotLicenseCheckRecord>> queryQulificationInfo4Employee(@PathVariable("licenseId") String licenseId)
	{
		long start = System.currentTimeMillis();
		
		DataResponse<List<PilotLicenseCheckRecord>> response = new DataResponse<List<PilotLicenseCheckRecord>>();
		
		try 
		{
			List<PilotLicenseCheckRecord> result = pilotLicenseCheckService.findByLicenseId(licenseId);
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
	
	@RequestMapping(value="/serach/pageById",method = RequestMethod.GET)
	@ApiOperation(value="根据飞行员ID分页获取获取机型签注")
	@ApiImplicitParams({@ApiImplicitParam(value="员工ID",name="id",dataType="String",paramType="query"),
		@ApiImplicitParam(value = "页面大小", name = "pageSize", dataType = "int", paramType = "query"),
		@ApiImplicitParam(value = "当前页", name = "page", dataType = "int", paramType = "query")
		})
	public Page<PilotLicenseCheckRecord> getPageByid(@RequestParam(value="id", required=true) String id, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize){
		Pageable pageable = new PageRequest(page, pageSize);
		return this.pilotLicenseCheckService.findByPage(Long.parseLong(id),pageable);
	}
	
	/**
	 * 航空器维修执照机型签发 查询所有
	 * aircraft_repair_license_model_check
	 */
	@RequestMapping(value = "/search/aircraftRepairLicenseModelCheck", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有航空器维修执照机型签发")
	public DataResponse<List<AircraftRepairLicenseModelCheck>>  findAllarlmc(){
		DataResponse<List<AircraftRepairLicenseModelCheck>> response = new DataResponse<>();
		
		try {
			List<AircraftRepairLicenseModelCheck> all = this.arlmcs.findAll();
			response.setData(all);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	/**
	 * 航空器维修执照机型签发 增加/修改
	 */
	@RequestMapping(value = "/save/aircraftRepairLicenseModelCheck", method = RequestMethod.POST)
    @ApiOperation(value = "查询所有航空器维修执照机型签发")
	@ApiImplicitParam(
			value="实体类",name="arlmcs",dataType="AircraftRepairLicenseModelCheck")
	public DataResponse<AircraftRepairLicenseModelCheck>  saveArlmc(@RequestBody AircraftRepairLicenseModelCheck arlmcs){
		DataResponse<AircraftRepairLicenseModelCheck> response = new DataResponse<>();
		
		try {
			 AircraftRepairLicenseModelCheck data = this.arlmcs.save(arlmcs);
			response.setData(data);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	/**
	 * 航空器维修执照机型签发 删除
	 */
	@RequestMapping(value = "/delete/aircraftRepairLicenseModelCheck/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "航空器维修执照机型签发 删除")
	public DataResponse<Long>  delArlmc(@PathVariable("id") Long id){
		DataResponse<Long> response = new DataResponse<>();
		
		try {
			 this.arlmcs.delete(id);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	
	
	/**
	 * aircraft_repair_license_special_check
	 * 航空器维修执照续签  查询所有
	 */
	@RequestMapping(value = "/search/aircraftRepairLicenseSpecialCheck", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有航空器维修执照续签")
	public DataResponse<List<AircraftRepairLicenseSpecialCheck>>  findAllarlsc(){
		DataResponse<List<AircraftRepairLicenseSpecialCheck>> response = new DataResponse<>();
		try {
			List<AircraftRepairLicenseSpecialCheck> all = this.arlscs.findAll();
			response.setData(all);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	
	/**
	 * 航空器维修执照续签 增加/修改
	 */
	@RequestMapping(value = "/save/aircraftRepairLicenseSpecialCheck", method = RequestMethod.POST)
    @ApiOperation(value = "航空器维修执照续签增加/修改")
	@ApiImplicitParam(
			value="实体类",name="arlscs",dataType="AircraftRepairLicenseSpecialCheck")
	public DataResponse<AircraftRepairLicenseSpecialCheck>  saveArlscs(@RequestBody AircraftRepairLicenseSpecialCheck arlscs){
		DataResponse<AircraftRepairLicenseSpecialCheck> response = new DataResponse<>();
		
		try {
			AircraftRepairLicenseSpecialCheck data = this.arlscs.save(arlscs);
			response.setData(data);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	/**
	 * 航空器维修执照续签 删除
	 */
	@RequestMapping(value = "/delete/aircraftRepairLicenseSpecialCheck/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "航空器维修执照续签 删除")
	public DataResponse<Long>  delArlscs(@PathVariable("id") Long id){
		DataResponse<Long> response = new DataResponse<>();
		
		try {
			 this.arlscs.delete(id);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	
	
	/**
	 * AircraftPartsRepairLicenseProjectCheck
	 * 航空器部件维修签署项目
	 */
	@RequestMapping(value = "/search/aircraftPartsRepairLicenseProjectCheck", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有航空器部件维修签署项目")
	public DataResponse<List<AircraftPartsRepairLicenseProjectCheck>>  findAllaprlpc(){
		DataResponse<List<AircraftPartsRepairLicenseProjectCheck>> response = new DataResponse<>();
		try {
			List<AircraftPartsRepairLicenseProjectCheck> all = this.aprlpcs.findAll();
			response.setData(all);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	/**
	 * 航空器部件维修签署项目 增加/修改
	 */
	@RequestMapping(value = "/save/aircraftPartsRepairLicenseProjectCheck", method = RequestMethod.POST)
    @ApiOperation(value = "航空器部件维修签署项目 增加/修改")
	@ApiImplicitParam(
			value="实体类",name="arlscs",dataType="AircraftPartsRepairLicenseProjectCheck")
	public DataResponse<AircraftPartsRepairLicenseProjectCheck>  saveAprlpc(@RequestBody AircraftPartsRepairLicenseProjectCheck aprlpc){
		DataResponse<AircraftPartsRepairLicenseProjectCheck> response = new DataResponse<>();
		
		try {
			AircraftPartsRepairLicenseProjectCheck data = this.aprlpcs.save(aprlpc);
			response.setData(data);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	/**
	 * 航空器部件维修签署项目 删除
	 */
	@RequestMapping(value = "/delete/aircraftPartsRepairLicenseProjectCheck/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "航空器部件维修签署项目 删除")
	public DataResponse<Long>  delAprlpc(@PathVariable("id") Long id){
		DataResponse<Long> response = new DataResponse<>();
		
		try {
			 this.aprlpcs.delete(id);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	
	
	
	/**
	 * AircraftPartsRepairLicenseSpecialCheck
	 * 航空器部件维修专业续签
	 */
	@RequestMapping(value = "/search/aircraftPartsRepairLicenseSpecialCheck", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有航空器部件维修专业续签")
	public DataResponse<List<AircraftPartsRepairLicenseSpecialCheck>>  findAllaprlsc(){
		DataResponse<List<AircraftPartsRepairLicenseSpecialCheck>> response = new DataResponse<>();
		try {
			List<AircraftPartsRepairLicenseSpecialCheck> all = this.aprlscs.findAll();
			response.setData(all);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	/**
	 * 航空器部件维修专业续签增加/修改
	 */
	@RequestMapping(value = "/save/aircraftPartsRepairLicenseSpecialCheck", method = RequestMethod.POST)
    @ApiOperation(value = "航空器部件维修专业续签增加/修改")
	@ApiImplicitParam(
			value="实体类",name="aprlsc",dataType="AircraftPartsRepairLicenseSpecialCheck")
	public DataResponse<AircraftPartsRepairLicenseSpecialCheck>  saveAprlsc(@RequestBody AircraftPartsRepairLicenseSpecialCheck aprlsc){
		DataResponse<AircraftPartsRepairLicenseSpecialCheck> response = new DataResponse<>();
		
		try {
			AircraftPartsRepairLicenseSpecialCheck data = this.aprlscs.save(aprlsc);
			response.setData(data);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	/**
	 * 航空器部件维修专业续签 删除
	 */
	@RequestMapping(value = "/delete/aircraftPartsRepairLicenseSpecialCheck/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "航空器部件维修专业续签 删除")
	public DataResponse<Long>  delAprlsc(@PathVariable("id") Long id){
		DataResponse<Long> response = new DataResponse<>();
		
		try {
			 this.aprlscs.delete(id);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	
	
}
