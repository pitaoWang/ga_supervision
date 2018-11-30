/**  
 * 
 * @Title: SkilledCheckRecordController.java 
 * @Package com.atosenet.ga.company.mgt.controller.ga.employe  
 * @author xyx   
 * @date 2018年8月8日 上午11:16:53 
 * @version V1.0
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

import com.atosenet.ga.company.db.model.ga.employe.PeriodicalCheckRecord;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.employe.PeriodicalCheckRecordService;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/** 
 * TODO(定期检查记录)
 * 
 * @ClassName: SkilledCheckRecordController 
 * @author xyx 
 * @date 2018年8月8日 上午11:16:53 
 *  
 */
@RestController
@CrossOrigin
@RequestMapping("/eim/api/periodicalCheckRecord")
@Api(tags="定期检查记录")
@ApiIgnore
public class PeriodicalCheckRecordController {
	@Autowired
	PeriodicalCheckRecordService periodicalCheckRecordService;
	
	@RequestMapping(value="/serach/getbypilotId",method = RequestMethod.GET)
	@ApiOperation(value="根据飞行员id来获取定期检查记录")
	@ApiImplicitParams({
		@ApiImplicitParam(value="飞行员pilot表下的id",name="pilotId",dataType="String")	
	})
	public List<PeriodicalCheckRecord> getbypilotId(@RequestParam("pilotId") String pilotId){
		if(pilotId.equals("")||pilotId == null){
			return null;
		}
		return periodicalCheckRecordService.findBypilotId(Integer.parseInt(pilotId));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value="保存定期检查记录")
	public DataResponse<PeriodicalCheckRecord> save(@RequestBody PeriodicalCheckRecord periodicalCheckRecord,HttpServletRequest request)
	{
		DataResponse<PeriodicalCheckRecord> response = new DataResponse<PeriodicalCheckRecord>();
		
		
		try 
		{
			periodicalCheckRecordService.save(periodicalCheckRecord,request);
			response.setResultCode(Constants.OK);
			
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;	
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除定期检查记录")
	public void deleteById(@PathVariable("id") Long id) {
		periodicalCheckRecordService.delete(id);
	}
	
	@RequestMapping(value="/serach/getbyId",method = RequestMethod.GET)
	@ApiOperation(value="获取某条熟定期查记录详细信息")
	@ApiImplicitParam(value="定期检查记录ID",name="id",dataType="String")
	public PeriodicalCheckRecord getById(@RequestParam("id") String id)
	{	
		return periodicalCheckRecordService.findById(Long.parseLong(id));
	}
}
