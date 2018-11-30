/**
 * 
 */
package com.atosenet.ga.company.mgt.controller.ga.employe;

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

import com.atosenet.ga.company.db.model.ga.employe.UpdateCheckRecord;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.employe.UpdateCheckRecordService;
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
@RequestMapping("/eim/api/updateCheckRecord")
@Api(tags="更新检查记录")
@ApiIgnore
public class UpdateCheckRecordController {
	
	@Autowired
	private UpdateCheckRecordService updateCheckRecordService;
	
	@RequestMapping(value="/search/getbypilotId",method = RequestMethod.GET)
	@ApiOperation(value="根据飞行教员id来获取更新检查记录")
	@ApiImplicitParams({
		@ApiImplicitParam(value="飞行员pilot表下的id",name="pilotId",dataType="String")	
	})
	public List<UpdateCheckRecord> getbypilotId(@RequestParam("pilotId") String pilotId){
		
		if(pilotId.equals("") || pilotId == null){
			return null;
		}
		return updateCheckRecordService.findBypilotId(Integer.parseInt(pilotId));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value="保存更新检查记录")
	public DataResponse<UpdateCheckRecord> save(@RequestBody UpdateCheckRecord updateCheckRecord,HttpServletRequest request)
	{
		DataResponse<UpdateCheckRecord> response = new DataResponse<UpdateCheckRecord>();
		
		
		try 
		{
			updateCheckRecordService.save(updateCheckRecord,request);
			response.setResultCode(Constants.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;	
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除更新检查记录")
	public void deleteById(@PathVariable("id") Long id) {
		updateCheckRecordService.delete(id);
	}
	
	@RequestMapping(value="/serach/getbyId",method = RequestMethod.GET)
	@ApiOperation(value="获取某条更新检查记录详细信息")
	@ApiImplicitParam(value="更新检查记录ID",name="id",dataType="String")
	public UpdateCheckRecord getById(@RequestParam("id") String id)
	{	
		return updateCheckRecordService.findById(Long.parseLong(id));
	}

}
