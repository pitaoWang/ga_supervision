/**  
 * 
 * @Title: CheckRecordController.java 
 * @Package com.atosenet.ga.company.mgt.controller.ga.company  
 * @author xyx   
 * @date 2018年8月8日 下午5:51:01 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.controller.ga.company;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.company.CheckRecord;
import com.atosenet.ga.company.db.model.ga.employe.WorkRecord;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.company.CheckRecordService;
import com.atosenet.ga.company.mgt.service.ga.employe.WorkRecordService;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: CheckRecordController 
 * @author xyx 
 * @date 2018年8月8日 下午5:51:01 
 *  
 */
@RestController
@CrossOrigin
@RequestMapping("/eim/api/checkRecord")
@Api(tags="航空器定检记录")
public class CheckRecordController {
	@Autowired
	CheckRecordService checkRecordService;
	
	@RequestMapping(value="/serach/getbyaircaftId",method = RequestMethod.GET)
	@ApiOperation(value="根据航空器id来获取某航空器定检记录")
	@ApiIgnore
	@ApiImplicitParams({
		@ApiImplicitParam(value="员工employee表下的id",name="aircaftId",dataType="String")	
	})
	public List<CheckRecord> getbyaircaftId(@RequestParam("aircaftId") String aircaftId){	
		return checkRecordService.findByAircaftId(Integer.parseInt(aircaftId));
	}
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value="保存/编辑航空器定检记录")
	public DataResponse<CheckRecord> save(@RequestBody CheckRecord checkRecord,HttpServletRequest request)
	{
		DataResponse<CheckRecord> response = new DataResponse<CheckRecord>();
		
		
		try 
		{
			CheckRecord result = checkRecordService.save(checkRecord,request);
			response.setData(result);
			response.setResultCode(Constants.OK);
			
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;	
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除航空器定检记录")
	public DataResponse<Long> deleteById(@PathVariable("id") Long id) {
		DataResponse<Long> response = new DataResponse<Long>();
		Long result = checkRecordService.delete(id);
		
			response.setData(result);
			response.setResultCode(Constants.OK);
			return response;
	}
	
	@RequestMapping(value="/serach/getbyId",method = RequestMethod.GET)
	@ApiOperation(value="获取某条航空器定检记录详细信息")
	@ApiIgnore
	@ApiImplicitParam(value="航空器定检记录ID",name="id",dataType="String")
	public CheckRecord getById(@RequestParam("id") String id)
	{	
		return checkRecordService.findById(Long.parseLong(id));
	}
}
