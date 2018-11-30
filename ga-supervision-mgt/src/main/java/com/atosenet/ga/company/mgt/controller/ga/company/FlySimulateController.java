/**
 * 
 */
package com.atosenet.ga.company.mgt.controller.ga.company;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

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

import com.atosenet.ga.company.core.common.model.response.BaseResponse;
import com.atosenet.ga.company.db.model.ga.company.Aircraft;
import com.atosenet.ga.company.db.model.ga.company.Simulator;
import com.atosenet.ga.company.db.model.ga.company.vo.SimulatorVO;
import com.atosenet.ga.company.db.model.sys.Org;
import com.atosenet.ga.company.db.repos.sys.OrgRepository;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.company.FlySimulateService;
import com.atosenet.ga.company.mgt.service.ga.company.dto.AircraftCheckDTO;
import com.atosenet.ga.company.mgt.service.ga.company.dto.SimulatorDTO;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;
import com.atosenet.ga.company.mgt.util.ExcelExport;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author lvhao 飞行模拟设备
 *
 */

@RestController
@RequestMapping("/eim/api/simulate")
@Api(tags="飞行训练模拟器设备维护")
@CrossOrigin
public class FlySimulateController {

	@Autowired
	private FlySimulateService fsService;
	
	@Autowired
	private OrgRepository orgRepository;

	/**
	 * 根据企业id 导出飞行模拟设备列表
	 */
	@RequestMapping(value = "/export/companyid", method = RequestMethod.POST)
	@ApiIgnore
	@ApiOperation(value="根据企业id导出设备列表")
	public void export(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String companyId = request.getParameter("companyId");
		List<SimulatorVO> datas = fsService.getLists(name, type,companyId);
		
		if (datas != null && datas.size() != 0) {
			// 创建匿名子类
			ExcelExport<SimulatorVO> excelExport = new ExcelExport<SimulatorVO>() {
			};
			// 导出excel
			String names = "飞行模拟训练器";
			try {
				excelExport.export(names, datas, request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	//新增&修改  飞行训练模拟设备
	@RequestMapping(method=RequestMethod.POST)
	@ApiIgnore
	@ApiOperation(value="新增修改飞行训练模拟器")
	public BaseResponse save(@RequestBody SimulatorDTO info, HttpServletRequest request){
		//this.fsService.save(simulator);
			BaseResponse response = new BaseResponse();
		try {
			this.fsService.save(info);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	//模拟训练器删除  级联删除 发动机 鉴定记录 适用课程
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	@ApiIgnore
	@ApiOperation(value="模拟训练器删除")
	public void delete(@PathVariable("id") long id){
		this.fsService.delete(id);
		
	}
	
	/**
	 * 
	* getById: 根据ID获取模拟训练器. <br/> 
	* 
	* @author Administrator 
	* @param id
	* @return 
	* @since JDK 1.8
	 */
	@RequestMapping(value="/getSimulatorById/{id}", method=RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value="根据ID获取模拟训练器")
	public SimulatorDTO getSimulatorById(@PathVariable("id") long id) {
		return this.fsService.getSimulatorById(id);
	}
	
	//模拟训练器查询   page从0开始
	@RequestMapping(value="/pageList", method=RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value="分页查询模拟训练器")
	public Page<Simulator> getPage(HttpServletRequest request, HttpServletResponse response){
		String page = request.getParameter("page");
		String size = request.getParameter("size");
		Pageable pageable = new PageRequest(Integer.parseInt(page), Integer.parseInt(size));
		return this.fsService.selectPage(pageable);
	}
	
	//模拟训练器查询   page从0开始
	@RequestMapping(value="/getListByPage", method=RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value="分页查询模拟训练器")
	public Page<SimulatorVO> getListByPage(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String page = request.getParameter("currentPage");
		String size = request.getParameter("pageSize");
		String companyId = request.getParameter("companyId");
		Pageable pageable = new PageRequest(Integer.parseInt(page)-1, Integer.parseInt(size));
		return this.fsService.getList(name, type,companyId, pageable);
	}
	
	@RequestMapping(value = "/search/findsimulatebycertificateNum", method = RequestMethod.GET)
	@ApiIgnore
	public DataResponse<Simulator> findsimulatebycertificateNum(@RequestParam("certificateNum") String certificateNum,@RequestParam("companyId") Long companyId){
		long start = System.currentTimeMillis();	
		DataResponse<Simulator> response = new DataResponse<Simulator>();
		Simulator simulator = new Simulator();
		try{
			simulator =  fsService.findsimulatebycertificateNum(certificateNum,companyId);
			response.setData(simulator);
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
	
	@RequestMapping(value = "/search/findsimulatebycertificateNums", method = RequestMethod.GET)
	@ApiIgnore
	public DataResponse<Simulator> findsimulatebycertificateNums(@RequestParam("certificateNum") String certificateNum,@RequestParam("id") Long id,@RequestParam("companyId") Long companyId){
		long start = System.currentTimeMillis();	
		DataResponse<Simulator> response = new DataResponse<Simulator>();
		Simulator simulator = new Simulator();
		try{
			simulator =  fsService.findsimulatebycertificateNums(certificateNum,id,companyId);
			response.setData(simulator);
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
