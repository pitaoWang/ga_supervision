/**  
 * 
 * @Title: PlanTypeController.java 
 * @Package com.atosenet.ga.company.mgt.controller.ga.company  
 * @author dgw   
 * @date 2018年7月31日 下午5:06:58 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.controller.ga.company;

import java.text.ParseException;
import java.util.ArrayList;
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

import com.atosenet.ga.company.db.model.ga.company.Equipment;
import com.atosenet.ga.company.db.model.ga.company.PlanType;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.company.PlanTypeService;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/** 
 * TODO(机型controller)
 * 
 * @ClassName: PlanTypeController 
 * @author dgw 
 * @date 2018年7月31日 下午5:06:58 
 *  
 */
@RestController
@CrossOrigin
@RequestMapping("/eim/api/plantype")
@Api(tags="机型(plantype)")
public class PlanTypeController {
	
	@Autowired
	PlanTypeService planTypeService;
	
	@RequestMapping(value = {"list",""},method = RequestMethod.GET)
	@ApiOperation(value = "返回系统中所有的机型列表")
	public DataResponse<List<PlanType>> findAll(){
		long start = System.currentTimeMillis();	
		DataResponse<List<PlanType>> response = new DataResponse<List<PlanType>>();
		List<PlanType> list=new ArrayList<PlanType>();
		try 
		{
			list=  planTypeService.findAll();
			response.setData(list);
			response.setResultCode(Constants.OK);			
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
	
	
	
		@RequestMapping(value = "/search/findByCompany", method = RequestMethod.GET)	
		@ApiOperation(value = "根据企业ID分页查询机型信息")
		@ApiImplicitParams({
			@ApiImplicitParam(value="公司ID",name="companyId",dataType="String",paramType="query")
	})
		public Page<PlanType> findViewListByCompany(
				@RequestParam(value = "currentPage", required = false) String currentPage,
				@RequestParam(value = "pageSize", required = false) String pageSize,
				@RequestParam(value = "companyId", required = false) String companyId) throws ParseException {
			Pageable pageable = new PageRequest(Integer.parseInt(currentPage)-1, Integer.parseInt(pageSize));
			Page<PlanType> st =null;
			
			try {
				st = planTypeService.findViewListByCompany(companyId,pageable);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				
			}
			return st;
			
		}
		
		@RequestMapping(value = "/search/findByCompanys", method = RequestMethod.GET)	
		@ApiOperation(value = "根据企业ID分页查询机型信息")
		@ApiIgnore
		@ApiImplicitParams({
			@ApiImplicitParam(value="公司ID",name="companyId",dataType="String",paramType="query")
	})
		public Page<PlanType> findViewListByCompanys(
				@RequestParam(value = "currentPage", required = false) String currentPage,
				@RequestParam(value = "pageSize", required = false) String pageSize,
				@RequestParam(value = "typeNumber", required = false) String typeNumber,
				@RequestParam(value = "manufacturer", required = false) String manufacturer,
				@RequestParam(value = "aircraftType", required = false) String aircraftType,
				@RequestParam(value = "companyId", required = false) String companyId) throws ParseException {
			Pageable pageable = new PageRequest(Integer.parseInt(currentPage)-1, Integer.parseInt(pageSize));
			Page<PlanType> st =null;
			
			try {
				st = planTypeService.findViewListByCompanys(companyId,typeNumber,manufacturer,aircraftType,pageable);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				
			}
			return st;
			
		}
		
		
		
	
	@RequestMapping(value = "/findbyplantypeid/{plantypeid}", method = RequestMethod.GET)
	@ApiOperation(value = "根据机型ID获取机型基础信息")
	@ApiImplicitParams({
		@ApiImplicitParam(value="机型的id",name="plantypeid",dataType="String",paramType="path")	
	})
	public DataResponse<PlanType> findByPlanTypeId(@PathVariable("plantypeid") String plantypeid){
		long start = System.currentTimeMillis();	
		DataResponse<PlanType> response = new DataResponse<PlanType>();
		PlanType  planType = new PlanType();
		try{
			planType = planTypeService.findByPlanTypeId(Long.parseLong(plantypeid));
			response.setData(planType);
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
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ApiOperation(value = "保存/编辑机型基础信息")
	public PlanType save(@RequestBody PlanType planType) {
		
		planType.setCreatetime(new Date());
		planType.setUpdatetime(new Date());
		return planTypeService.save(planType);
	}
	
	/**
	 * 
	* 删除机型数据. <br/> 
	* 
	* @author Administrator 
	* @param id 
	* @since JDK 1.8
	 */
	@RequestMapping(value = "/delete/one/{id}",method = RequestMethod.DELETE)
	@ApiIgnore
	@ApiOperation(value = "删除机型信息")
	public void delete(@PathVariable("id") long id) {
		planTypeService.delete(id);
	}
	
	@RequestMapping(value = "/delete/{list}",method = RequestMethod.DELETE)
	@ApiOperation(value = "批量删除机型信息")
	public void deletes(@RequestBody List<PlanType> list) {
		planTypeService.deletes(list);
	}
}
