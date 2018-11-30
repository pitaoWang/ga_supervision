/**
 * 
 */
package com.atosenet.ga.company.mgt.controller.ga.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.atosenet.ga.company.db.model.ga.company.Aircraft;
import com.atosenet.ga.company.db.model.ga.company.AircraftFlySumView;
import com.atosenet.ga.company.db.model.ga.company.Equipment;
import com.atosenet.ga.company.db.model.ga.company.PlanType;
import com.atosenet.ga.company.db.model.ga.employe.vo.FlightImplementRecordAndAircraftDTO;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.company.EquipmentService;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author lvhao
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/eim/api/equipment")
@Api(tags="机载设备")

public class EquipmentController {
	
	@Autowired
	private EquipmentService eService;
	
	/*//根据企业id查询 机载设备
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "根据企业ID获取机载设备列表")
	public DataResponse<List<Equipment>> selectEquipmentByOrgId(@PathVariable String id){
		DataResponse<List<Equipment>> response = new DataResponse<>();
		List<Equipment> datas = null;
		try {
			datas = this.eService.selectByOrgId(Long.parseLong(id));
			response.setData(datas);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}*/
	//根据企业id查询 机载设备
	@RequestMapping(value = "/search/findByCompany", method = RequestMethod.GET)	
	@ApiOperation(value = "根据企业ID获取机载设备列表")
	@ApiImplicitParams({
		@ApiImplicitParam(value="公司ID",name="companyId",dataType="String",paramType="query")
})
	public Page<Equipment> findViewListByCompany(
			@RequestParam(value = "currentPage", required = false) String currentPage,
			@RequestParam(value = "pageSize", required = false) String pageSize,
			@RequestParam(value = "companyId", required = false) String companyId) throws ParseException {
		Pageable pageable = new PageRequest(Integer.parseInt(currentPage)-1, Integer.parseInt(pageSize));
		Page<Equipment> st =null;
		
		try {
			st = eService.findViewListByCompany(companyId,pageable);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
		}
		return st;
		
	}
	
	//根据企业id查询 机载设备
		@RequestMapping(value = "/search/findByCompanys", method = RequestMethod.GET)	
		@ApiOperation(value = "根据企业ID获取机载设备列表")
		@ApiIgnore
		@ApiImplicitParams({
			@ApiImplicitParam(value="公司ID",name="companyId",dataType="String",paramType="query")
	})
		public Page<Equipment> findViewListByCompanys(
				@RequestParam(value = "currentPage", required = false) String currentPage,
				@RequestParam(value = "pageSize", required = false) String pageSize,
				@RequestParam(value = "companyId", required = false) String companyId,
				@RequestParam(value = "epNum", required = false) String epNum,
				@RequestParam(value = "epType", required = false) String epType,
				@RequestParam(value = "regNumber", required = false) String regNumber) throws ParseException {
			Pageable pageable = new PageRequest(Integer.parseInt(currentPage)-1, Integer.parseInt(pageSize));
			Page<Equipment> st =null;
			
			try {
				st = eService.findViewListByCompanys(companyId,epNum,epType,regNumber,pageable);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				
			}
			return st;
			
		}
	
	//新增机载设备
	@RequestMapping(method=RequestMethod.POST)
	@ApiOperation(value = "新增/编辑机载设备")
	public DataResponse<Equipment> save(@RequestBody Equipment equipment){
		DataResponse<Equipment> response = new DataResponse<>();
		try {
			this.eService.save(equipment);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	//删除机载设备
	@RequestMapping(value="/one/{id}", method=RequestMethod.DELETE)
	@ApiOperation(value = "删除机载设备")
	@ApiIgnore
	public DataResponse<Equipment> delete(@PathVariable String id){
		DataResponse<Equipment> response = new DataResponse<>();
		try {
			this.eService.delete(Long.parseLong(id));
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	//删除机载设备
		@RequestMapping(value="{list}", method=RequestMethod.DELETE)
		@ApiOperation(value = "批量删除机载设备")
		public DataResponse<Equipment> deletes(@RequestBody List<Equipment> list){
			DataResponse<Equipment> response = new DataResponse<>();
			try {
				this.eService.deletes(list);
				response.setResultCode(Constants.OK);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
				response.setMessage(e.getMessage());
			}
			return response;
		}
		
	
	@RequestMapping(value = "/search/findEquipmentbyepNum", method = RequestMethod.GET)
	@ApiOperation(value = "根据设备号查看设备")
	@ApiIgnore
	@ApiImplicitParams({
		@ApiImplicitParam(value="设备号",name="epNum",dataType="String"),
		@ApiImplicitParam(value="公司id",name="companyId",dataType="Long")
	})
	public DataResponse<Equipment> findByRegNumber(@RequestParam("epNum") String epNum,@RequestParam("companyId") Long companyId){
		long start = System.currentTimeMillis();	
		DataResponse<Equipment> response = new DataResponse<Equipment>();
		Equipment equipment = new Equipment();
		try{
			equipment =  eService.findByEpNumAndCompanyId(epNum,companyId);
			response.setData(equipment);
			response.setResultCode(Constants.OK);
		}catch(Exception e){
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}finally{
			System.out.print("time = "+(System.currentTimeMillis()-start));
		}
		return response;
	}
	
	@RequestMapping(value = "/search/findEquipmentbyepNums", method = RequestMethod.GET)
	@ApiOperation(value = "根据设备号查看设备")
	@ApiIgnore
	@ApiImplicitParams({
		@ApiImplicitParam(value="设备号",name="epNum",dataType="String"),
		@ApiImplicitParam(value="设备id",name="id",dataType="Long"),
		@ApiImplicitParam(value="公司id",name="companyId",dataType="Long")
	})
	public DataResponse<Equipment> findByRegNumbers(@RequestParam("epNum") String epNum,@RequestParam("id") Long id,@RequestParam("companyId") Long companyId){
		long start = System.currentTimeMillis();	
		DataResponse<Equipment> response = new DataResponse<Equipment>();
		Equipment equipment = new Equipment();
		try{
			equipment =  eService.findByEpNumAndId(epNum,id,companyId);
			response.setData(equipment);
			response.setResultCode(Constants.OK);
		}catch(Exception e){
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}finally{
			System.out.print("time = "+(System.currentTimeMillis()-start));
		}
		return response;
	}
	
    @RequestMapping(value = "/search/findbyid", method = RequestMethod.GET)	
	@ApiOperation(value = "根据ID查询机载设备信息")
	@ApiImplicitParams({
		@ApiImplicitParam(value="机载设备id",name="id",dataType="String",paramType="query")
	})
	public DataResponse<List<Equipment>> findByManagerId(@RequestParam("id")String id){
		long start = System.currentTimeMillis();	
		DataResponse<List<Equipment>> response = new DataResponse<List<Equipment>>();
		List<Equipment>  list;
		try{
			list =  eService.findById(Long.parseLong(id));
			response.setData(list);
			response.setResultCode(Constants.OK);			
		}catch(Exception e){
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}finally{
			System.out.print("time = "+(System.currentTimeMillis()-start));
		}
		return response;
	}
	
}
