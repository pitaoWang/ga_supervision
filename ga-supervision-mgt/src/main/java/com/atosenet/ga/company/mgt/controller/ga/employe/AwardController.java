package com.atosenet.ga.company.mgt.controller.ga.employe;

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

import com.atosenet.ga.company.core.common.model.response.BaseResponse;
import com.atosenet.ga.company.db.model.ga.employe.AwardRecord;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.employe.AwardService;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@RequestMapping("/eim/api/award")
@Api(tags="员工奖惩记录")
public class AwardController {
	
	@Autowired
	private AwardService awardService;
	
	/**
	 * 保存奖惩记录
	 * @param awardRecord
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "新增奖惩记录")
	public DataResponse<AwardRecord> save(@RequestBody AwardRecord awardRecord){
		DataResponse<AwardRecord> response = new DataResponse<AwardRecord>();
		try {
			this.awardService.save(awardRecord);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}

	
	/**
	 * 根据ID批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="{ids}", method = RequestMethod.DELETE)
	@ApiOperation(value="通过id批量删除奖惩记录")
	public BaseResponse deleteById(@PathVariable("ids") List<Long> ids){
		BaseResponse response = new BaseResponse();
		try {
			this.awardService.delete(ids);
		} catch (Exception e) {
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	/**
	 * 根据人员ID分页获取奖惩记录
	 * @param employeeId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/search/getbyemployeeid", method=RequestMethod.GET)
	@ApiOperation(value = "根据人员id分页获取奖惩记录")
	@ApiImplicitParams({@ApiImplicitParam(value="员工ID",name="employeeId",dataType="String",paramType="query"),
		@ApiImplicitParam(value = "页面大小", name = "pageSize", dataType = "int", paramType = "query"),
		@ApiImplicitParam(value = "当前页", name = "page", dataType = "int", paramType = "query")
		})
	public Page<AwardRecord> getByEmployeeId(@RequestParam("employeeId") String employeeId, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
		if(employeeId.equals("")||employeeId == null) {
			return null;
		}
		Pageable pageable = new PageRequest(page, pageSize);
		return this.awardService.findByPage(Long.parseLong(employeeId), pageable);
	}
	
	@RequestMapping(value="/search/{employeeId}",method=RequestMethod.GET)
	@ApiOperation(value = "根据人员id获取奖惩记录")
	@ApiIgnore
	public DataResponse<List<AwardRecord>> getAllByEmployeeId(@PathVariable("employeeId") String employeeId){
		DataResponse<List<AwardRecord>> response = new DataResponse<>();
		try {
			List<AwardRecord> datas=this.awardService.findByEmployeeId(Long.parseLong(employeeId));
			response.setData(datas);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage(e.getMessage());
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	
	/**
	 * 根据ID返回奖惩记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/serach/getbyid",method = RequestMethod.GET)
	@ApiOperation(value="获取单条奖惩记录详细信息")
	@ApiImplicitParam(value="奖惩记录ID",name="id", dataType="String")
	public AwardRecord getById(@RequestParam("id") String id) {	
		return awardService.findById(Long.parseLong(id));
	}
	
}
