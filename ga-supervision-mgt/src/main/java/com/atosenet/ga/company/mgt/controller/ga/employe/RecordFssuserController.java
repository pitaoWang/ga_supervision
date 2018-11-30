/**  
 * 
 * @Title: RecordFssuserController.java 
 * @Package com.atosenet.ga.company.mgt.controller.ga.employe  
 * @author xyx   
 * @date 2018年9月21日 下午4:34:49 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.controller.ga.employe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.company.StructureFss;
import com.atosenet.ga.company.db.model.ga.employe.RecordFssuser;
import com.atosenet.ga.company.mgt.config.Constants;

import com.atosenet.ga.company.mgt.service.ga.employe.RecordFssuserService;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;
import com.atosenet.ga.company.mgt.util.ExcelExport;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: RecordFssuserController 
 * @author xyx 
 * @date 2018年9月21日 下午4:34:49 
 *  
 */
@RestController
@CrossOrigin
@ApiIgnore
@RequestMapping(value="/eim/api/recordFssuser")
@Api(tags="飞行服务站人员的维护")
public class RecordFssuserController {
	@Autowired
	RecordFssuserService recordFssuserService;
	
	@RequestMapping(value="/search/findRecordFssuserByInfo",method = RequestMethod.GET)
	@ApiOperation(value = "支持按照姓名、岗位、类型、职位、手机号码等进行查询的员工信息(带分页)")
 public DataResponse<List<RecordFssuser>> findRecordFssuserByInfo(
		 @RequestParam final Long companyId,
		 @RequestParam final String name,
		 @RequestParam  String type,
		 @RequestParam final String tel,
		 @RequestParam final String fssId,
		 /*@RequestParam final String baseId,*/
		 @RequestParam final Integer currentPage, 
		 @RequestParam("pageSize") final Integer pageSize){
		long start = System.currentTimeMillis();
		
		DataResponse<List<RecordFssuser>> response = new DataResponse<List<RecordFssuser>>();
		
		try 
		{
			
			Pageable pageable = new PageRequest(currentPage-1, pageSize);
			
				List<RecordFssuser> list = recordFssuserService.findRecordFssuserByInfo(companyId,name,type,
						tel,fssId,pageable);
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
	
	@RequestMapping(value="/search/byids",method = RequestMethod.GET)
	
 public DataResponse<List<StructureFss>> findStructureFssByInfo(
		 @RequestParam final Long companyId,
		 @RequestParam  String type){
		long start = System.currentTimeMillis();
		
		DataResponse<List<StructureFss>> response = new DataResponse<List<StructureFss>>();
		
		try 
		{
			
				List<StructureFss> list = recordFssuserService.findStructureFssByInfo(companyId,type);
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
 
 @RequestMapping(value="search/downEmployeeExcel",method = RequestMethod.POST)
 public void downEmployeeExcel(
		 @RequestParam final Long companyId,
		 @RequestParam final String name,
		 @RequestParam  String type,
		 @RequestParam final String tel,
		 @RequestParam final String fssId,
		 HttpServletRequest res,HttpServletResponse rep
		 ){
 	try 
		{
 		
			List<RecordFssuser> list = recordFssuserService.findRecordFssuserByInfos(companyId,name,type,
					tel,fssId);
		  String names ="飞行服务站人员列表";
	      ExcelExport<RecordFssuser> exports = new ExcelExport<RecordFssuser>() {};
	      exports.export(names, list, res, rep);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
 }
}
