/**
 * 
 */
package com.atosenet.ga.company.mgt.controller.ga.company;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.atosenet.ga.company.db.model.ga.company.CheckRecord;
import com.atosenet.ga.company.db.model.ga.company.Simulator;
import com.atosenet.ga.company.db.model.ga.company.SimulatorRecord;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.company.SimulatorRecordService;
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
@RequestMapping("/eim/api/simulatorRecode")
@Api(tags="模拟训练器鉴定记录")
public class SimulatorRecordController {

	@Autowired
	private SimulatorRecordService srService;

	// 分页查询
	@RequestMapping(value = "/pageList", method = RequestMethod.GET)
	@ApiIgnore
	public Page<SimulatorRecord> getPage(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		String size = request.getParameter("size");
		Pageable pageable = new PageRequest(Integer.parseInt(page), Integer.parseInt(size));
		return this.srService.selectPage(pageable);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ApiOperation(value="保存/编辑鉴定记录")
	public DataResponse<SimulatorRecord> save(@RequestBody SimulatorRecord simulatorRecord,HttpServletRequest request){
	DataResponse<SimulatorRecord> response = new DataResponse<SimulatorRecord>();
		
		
		try 
		{
			SimulatorRecord result = this.srService.save(simulatorRecord);
			response.setData(result);
			response.setResultCode(Constants.OK);
			
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
		
	}
	
	@RequestMapping(value = "/findbysimulatorId",method=RequestMethod.GET)
	@ApiOperation(value="根据模拟训练器ID获取模拟器的鉴定记录")
	@ApiImplicitParams(
			{ 
				@ApiImplicitParam(value = "模拟训练器id", name = "simulatorId",paramType="query", dataType = "String")
				}
			)
	public List<SimulatorRecord> findBySimulatorId(@RequestParam("simulatorId")String simulatorId){
		return srService.findBySimulatorId(Long.parseLong(simulatorId));
	}
	
	@RequestMapping(value = "/findbysimulatorIds",method=RequestMethod.POST)
	@ApiOperation(value="根据模拟训练器ID获取模拟器的鉴定记录(多个航空器)")
	public List<SimulatorRecord> findBySimulatorIds(@RequestBody List<Simulator> simulator){
		List<Long> list = new ArrayList<>();
		for (Simulator simulators : simulator) {
			list.add(simulators.getId());
		}
		return srService.findBySimulatorIds(list);
	}
	
	@RequestMapping(value = "/one/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除鉴定记录")
	@ApiIgnore
	public DataResponse<Long> deleteById(@PathVariable("id") Long id) {
		DataResponse<Long> response = new DataResponse<Long>();
		Long result = srService.delete(id);
		
			response.setData(result);
			response.setResultCode(Constants.OK);
			return response;
	}
	@RequestMapping(value = "{list}", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除鉴定记录")
	
	public void deleteByIds(@RequestBody List<SimulatorRecord> list) {
		
		srService.deletes(list);
		
	}

}
