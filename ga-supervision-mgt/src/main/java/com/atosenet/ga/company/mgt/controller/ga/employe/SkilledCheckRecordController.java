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

import com.atosenet.ga.company.db.model.ga.employe.SkilledCheckRecord;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.employe.SkilledCheckRecordService;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/** 
 * TODO(熟练检查记录)
 * 
 * @ClassName: SkilledCheckRecordController 
 * @author xyx 
 * @date 2018年8月8日 上午11:16:53 
 *  
 */
@RestController
@CrossOrigin
@RequestMapping("/eim/api/skilledCheckRecord")
@Api(tags="员工（飞行员）检查记录")
public class SkilledCheckRecordController {
	@Autowired
	SkilledCheckRecordService skilledCheckRecordService;
	
	/**
	 * 分页获取检查记录
	 * @param pilotId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/serach/getbypilotId",method = RequestMethod.GET)
	@ApiOperation(value="根据飞行员id来获取检查记录")
	@ApiImplicitParams({
		@ApiImplicitParam(value="飞行员pilot表下的id",name="pilotId",dataType="String"),	
		@ApiImplicitParam(value="当前页数",name="page",dataType="Integer"),	
		@ApiImplicitParam(value="每页的大小",name="pageSize",dataType="Integer")	
	})
	public Page<SkilledCheckRecord> getbypilotId(@RequestParam("pilotId") String pilotId, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
		if(pilotId.equals("") || pilotId == null){
			return null;
		}
		Pageable pageable = new PageRequest(page, pageSize);
		return skilledCheckRecordService.findBypilotId(Integer.parseInt(pilotId), pageable);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value="保存检查记录")
	public DataResponse<SkilledCheckRecord> save(@RequestBody SkilledCheckRecord skilledCheckRecord, HttpServletRequest request) {
		DataResponse<SkilledCheckRecord> response = new DataResponse<SkilledCheckRecord>();
		try {
			skilledCheckRecordService.save(skilledCheckRecord,request);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;	
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除检查记录")
	@ApiIgnore
	public void deleteById(@PathVariable("id") Long id) {
		skilledCheckRecordService.delete(id);
	}
	
	@RequestMapping(value = "/batchDelete/{ids}", method = RequestMethod.DELETE)
	@ApiOperation(value = "根据ID批量删除检查记录")
	public void batchDelete(@PathVariable("ids") List<Long> ids) {
		skilledCheckRecordService.batchDelete(ids);
	}
	
	@RequestMapping(value="/serach/getbyId",method = RequestMethod.GET)
	@ApiOperation(value="获取某条检查记录详细信息")
	@ApiImplicitParam(value="熟练检查记录ID",name="id",dataType="String")
	public SkilledCheckRecord getById(@RequestParam("id") String id) {	
		return skilledCheckRecordService.findById(Long.parseLong(id));
	}
}
