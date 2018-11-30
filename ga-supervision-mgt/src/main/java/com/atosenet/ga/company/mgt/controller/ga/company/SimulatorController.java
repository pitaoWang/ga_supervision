/**  
 * 
 * @Title: SimulatorController.java 
 * @Package com.atosenet.ga.company.mgt.controller.ga.company  
 * @author dgw   
 * @date 2018年8月2日 上午9:40:46 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.controller.ga.company;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.atosenet.ga.company.db.model.ga.company.Aircraft;
import com.atosenet.ga.company.db.model.ga.company.PlanType;
import com.atosenet.ga.company.db.model.ga.company.Simulator;
import com.atosenet.ga.company.db.model.ga.company.vo.SimulatorVO;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.company.SimulatorService;
import com.atosenet.ga.company.mgt.service.ga.company.dto.SimulatorDTO;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/** 
 * TODO(模拟训练器controller层)
 * 
 * @ClassName: SimulatorController 
 * @author dgw 
 * @date 2018年8月2日 上午9:40:46 
 *  
 */
@RestController
@CrossOrigin
@RequestMapping("/eim/api/simulator")
@Api(tags="模拟训练器(simulator)")
public class SimulatorController {
	
	@Autowired
	SimulatorService simulatorService;
	
	@RequestMapping(value = "/findsimulatorlistbycompanyid/{companyid}", method = RequestMethod.GET)
	@ApiOperation(value = "根据企业ID获取通航企业模拟训练器列表分页")
	@ApiImplicitParams({
		@ApiImplicitParam(value="企业id",name="companyid",dataType="String",paramType="path")	
	})
	public Page<SimulatorVO> findByPlanTypeId(@PathVariable("companyid") String companyid,
			@RequestParam(value = "pageno", required = false) String pageno,
	        @RequestParam(value = "pagesize", required = false) String pagesize){
		long start = System.currentTimeMillis();	
		Pageable pageable = new PageRequest(Integer.parseInt(pageno), Integer.parseInt(pagesize));
		Page<SimulatorVO> st = null;
		try{
			st = simulatorService.findByCompanyId(Long.parseLong(companyid),pageable);
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			System.out.print("time = "+(System.currentTimeMillis()-start));
		}
		return st;
	}
	
	
	@RequestMapping(value="/getSimulatorById/{id}", method=RequestMethod.GET)
	@ApiOperation(value="根据ID获取模拟训练器")
	public SimulatorVO getSimulatorById(@PathVariable("id") long id) {
		return this.simulatorService.getSimulatorById(id);
	}
	
	//新增&修改  飞行训练模拟设备
		@RequestMapping(method=RequestMethod.POST)
		
		@ApiOperation(value="新增修改飞行训练模拟器")
		public BaseResponse save(@RequestBody Simulator simulator, HttpServletRequest request){
			//this.fsService.save(simulator);
				BaseResponse response = new BaseResponse();
			try {
				this.simulatorService.save(simulator);
				response.setResultCode(Constants.OK);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
				response.setMessage(e.getMessage());
			}
			return response;
		}
		
		
		@RequestMapping(value="{list}", method=RequestMethod.DELETE)
		@ApiOperation(value="批量模拟训练器删除")
		public void delete(@RequestBody List<Simulator> list){
			this.simulatorService.delete(list);
			
		}
}
