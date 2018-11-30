/**
 * 
 */
package com.atosenet.ga.company.mgt.controller.ga.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.base.BaseClassroom;
import com.atosenet.ga.company.db.model.ga.company.OperationBase;
import com.atosenet.ga.company.db.model.ga.company.TakeCourse;
import com.atosenet.ga.company.db.model.ga.company.vo.OperationBaseVO;
import com.atosenet.ga.company.db.model.sys.Org;
import com.atosenet.ga.company.db.repos.ga.company.OperationRepo;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.company.OperationBaseService;
import com.atosenet.ga.company.mgt.service.ga.company.dto.OperationBaseDTO;
import com.atosenet.ga.company.mgt.service.ga.company.dto.OperationIdsDTO;
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
@RequestMapping("/eim/api/operation")
@Api(tags = "基地信息")

public class OperationBaseController {
	
	@Autowired
	private OperationRepo operationRepo;
	
	@Autowired
	private OperationBaseService operationBaseService;
	
	
	@RequestMapping(value= "/search/bycompanyid", method = RequestMethod.GET)
	@ApiOperation(value = "根据企业ID获取通航企业基地列表")
	@ApiImplicitParams({ @ApiImplicitParam(value = "企业id", name = "companyId",paramType="query", dataType = "String") })
	public List<OperationBase> getBycompanyId(@RequestParam("companyId")String companyId){
		return this.operationRepo.findList(Long.parseLong(companyId));
	}
	
	@RequestMapping(value = "pagelist",method = RequestMethod.GET)
	@ApiOperation(value = "获取运行基地分页列表")
	@ApiIgnore
	public Page<OperationBase> findPageAll(HttpServletRequest request,HttpServletResponse response){
		String page =  request.getParameter("page");
		String size =  request.getParameter("size");
		Pageable pageable = new PageRequest(Integer.parseInt(page),Integer.parseInt(size));
		return operationBaseService.findPageAll(pageable);
	}
	
	@RequestMapping(method =RequestMethod.GET)
	@ApiIgnore
	public DataResponse<List<OperationBase>> findAllOperationBase(){
		long start = System.currentTimeMillis();	
		DataResponse<List<OperationBase>> response = new DataResponse<List<OperationBase>>();
		List<OperationBase> list=new ArrayList<OperationBase>();
		try 
		{
			list=  operationBaseService.findAllOperationBase();
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
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ApiOperation(value = "新增/编辑基地信息")
	@ApiImplicitParams({ @ApiImplicitParam(value = "基地信息对象", name = "operationBase",paramType = "body", dataType = "OperationBase"),				
    })
	public OperationBase save(@RequestBody OperationBase operationBase){
		return operationBaseService.save(operationBase);
	}
	
	/*@RequestMapping(value = "/update",method = RequestMethod.GET)
	@ApiOperation(value = "修改运行基地信息")
	public void update(OperationBase operationBase){
		operationBase.setBaseId(2);
		operationBase.setBaseName("test");
		operationBaseService.save(operationBase);
	}*/
	
	@RequestMapping(value = "/delete",method = RequestMethod.DELETE)
	@ApiOperation(value = "根据id删除运行基地信息(包括基地教室,承担课程)")
	@ApiIgnore
	public DataResponse delete(long baseId){
		//operationBaseService.delete(baseId);
		DataResponse res = new DataResponse();
		try{
			operationBaseService.delete(baseId);
			res.setResultCode(Constants.OK);
		}catch(Exception e){
			e.printStackTrace();
			res.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	
	@RequestMapping(value = "/findbybaseid",method = RequestMethod.GET)
	@ApiOperation(value = "根据基地id查询基地信息")
	@ApiImplicitParams({ @ApiImplicitParam(value = "基地id", name = "baseId",paramType = "query", dataType = "String"),				
    })
	public DataResponse<OperationBase> findByBaseId(@RequestParam("baseId") String baseId){
		long start = System.currentTimeMillis();	
		DataResponse<OperationBase> response = new DataResponse<OperationBase>();
		OperationBase operation=new OperationBase();
		try 
		{
			operation =  operationBaseService.findByBaseId(Long.parseLong(baseId));
			response.setData(operation);
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
	
	@RequestMapping(value = "/findtakecoursebybaseid",method = RequestMethod.GET)
	@ApiOperation(value = "根据基地id查询承担课程信息")
	@ApiImplicitParams({ @ApiImplicitParam(value = "基地id", name = "baseId",paramType="query", dataType = "String") })
	public DataResponse<List<TakeCourse>> findTakeCourseByBaseId(@RequestParam("baseId")String baseId){
		long start = System.currentTimeMillis();	
		DataResponse<List<TakeCourse>> response = new DataResponse<List<TakeCourse>>();
		List<TakeCourse> takeList=new ArrayList<TakeCourse>();
		try 
		{
			takeList =  operationBaseService.findTakeCourseByBaseId(Long.parseLong(baseId));
			response.setData(takeList);
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
		
	@RequestMapping(value = "/findclassroombybaseid",method = RequestMethod.GET)
	@ApiImplicitParams({ @ApiImplicitParam(value = "基地id", name = "baseId",paramType="query", dataType = "String") })
	@ApiOperation(value="根据基地id查询基地教室信息")
	public DataResponse<List<BaseClassroom>> findClassroomByBaseId(@RequestParam("baseId")String baseId){
		long start = System.currentTimeMillis();	
		DataResponse<List<BaseClassroom>> response = new DataResponse<List<BaseClassroom>>();
		List<BaseClassroom> classroomList=new ArrayList<BaseClassroom>();
		try 
		{
			classroomList =  operationBaseService.findClassroomByBaseId(Long.parseLong(baseId));
			response.setData(classroomList);
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
	
	@RequestMapping(value = "/saveclassroom",method = RequestMethod.POST)
	@ApiOperation(value="新增/修改基地教室信息")
	@ApiImplicitParams({ @ApiImplicitParam(value = "基地教室对象", name = "classroom",paramType = "body", dataType = "BaseClassroom"),				
    })
	public DataResponse<BaseClassroom> saveClassroom( @RequestBody BaseClassroom classroom){
		long start = System.currentTimeMillis();	
		DataResponse<BaseClassroom> response = new DataResponse<BaseClassroom>();
		BaseClassroom baseClassroom = new BaseClassroom();
		try 
		{
			baseClassroom =  operationBaseService.saveClassroom(classroom);
			response.setData(baseClassroom);
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
	
	@RequestMapping(value = "/saveoperationall",method=RequestMethod.POST)
	@ApiOperation(value="保存运行基地和基地教室和承担课程信息")
	@ApiIgnore
	public DataResponse<OperationBaseDTO> saveOperationBaseAll(@RequestBody OperationBaseDTO operationDTO){
		long start = System.currentTimeMillis();	
		DataResponse<OperationBaseDTO> response = new DataResponse<OperationBaseDTO>();
		try 
		{
			 operationBaseService.saveOperationAll(operationDTO);		
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
	
	@RequestMapping(value = "/savetakecourse",method = RequestMethod.POST)
	@ApiOperation(value = "新增/修改基地承担课程信息")
	@ApiImplicitParams({ @ApiImplicitParam(value = "基地承担课程对象", name = "takeCourse",paramType = "body", dataType = "TakeCourse"),				
    })
	public DataResponse<TakeCourse> saveTakeCourse(@RequestBody TakeCourse takeCourse){
		DataResponse<TakeCourse> response =  new DataResponse<TakeCourse>();
		TakeCourse course = new TakeCourse();
		try{			
			course = operationBaseService.saveTakeCourse(takeCourse);
			response.setData(course);
			response.setResultCode(Constants.OK);
			
		}catch(Exception e){
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/deletebaseroombyid",method=RequestMethod.DELETE)
	@ApiOperation(value = "根据基地教室id删除基地教室")
	@ApiImplicitParams({ @ApiImplicitParam(value = "基地教室id", name = "classroomId",paramType = "query", dataType = "String"),				
    })
	public DataResponse deleteBaseClass(@RequestParam("classroomId") String classroomId){
		DataResponse res = new DataResponse();
		try{
			operationBaseService.deleteBaseClass(Long.parseLong(classroomId));
			res.setResultCode(Constants.OK);
		}catch(Exception e){
			e.printStackTrace();
			res.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	
	@RequestMapping(value = "/deletetakecoursebyid", method = RequestMethod.DELETE)
	@ApiOperation(value = "根据承担课程id删除课程信息")
	@ApiImplicitParams({ @ApiImplicitParam(value = "承担课程id", name = "takecourseId",paramType = "query", dataType = "String"),				
    })
	public DataResponse deleteCourse(@RequestParam("takecourseId")String takecourseId){
		DataResponse res = new DataResponse();
		try{
			operationBaseService.deleteCourse(Long.parseLong(takecourseId));
			res.setResultCode(Constants.OK);
		}catch(Exception e){
			e.printStackTrace();
			res.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	
	@RequestMapping(value = "/findbycompanyid", method = RequestMethod.GET)
	@ApiOperation(value = "根据企业ID分页查询基地信息")
	@ApiImplicitParams({ @ApiImplicitParam(value = "页码", name = "pageno",paramType = "query", dataType = "Integer"),
			@ApiImplicitParam(value = "每页条数", name = "pagesize",paramType = "query", dataType = "Integer"), 
			@ApiImplicitParam(value = "企业id", name = "companyId",paramType = "query", dataType = "Long")		
	})
	public Page<OperationBase> findByCompanyId(@RequestParam(value = "pageno", required = true) String pageno,
			@RequestParam(value = "pagesize", required = true) String pagesize,
			@RequestParam(value = "companyId", required = true) String companyId){		
		Pageable pageable = new PageRequest(Integer.parseInt(pageno), Integer.parseInt(pagesize));	
		return operationBaseService.findByCompanyId(Long.parseLong(companyId), pageable);
	}
	
	@RequestMapping(value = "/batchdeletebase",method = RequestMethod.DELETE)
	@ApiOperation(value = "批量删除基地信息")
	@ApiImplicitParams({ @ApiImplicitParam(value = "基地id实体", name = "operationIdsDTO",paramType = "body", dataType = "OperationIdsDTO"),				
    })
	public DataResponse BatchDelete(@RequestBody OperationIdsDTO operationIdsDTO){
		DataResponse res = new DataResponse();
		try{
			for(Long baseId : operationIdsDTO.getBaseIds()){
				operationBaseService.delete(baseId);
			}
			res.setResultCode(Constants.OK);
		}catch(Exception e){
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return res;
	}
	
}
