/**  
 * 
 * @Title: SsmController.java 
 * @Package com.atosenet.ga.company.mgt.controller.ga.ssm  
 * @author xyx   
 * @date 2018年10月26日 下午2:06:48 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.controller.ga.ssm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.document.DocList;
import com.atosenet.ga.company.db.model.ga.document.DocListDto;
import com.atosenet.ga.company.db.model.ga.employe.vo.PilotListVO;
import com.atosenet.ga.company.db.repos.ga.company.AircraftFlySumRepository;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.Document.DocListService;
import com.atosenet.ga.company.mgt.service.ga.company.AircraftService;
import com.atosenet.ga.company.mgt.service.ga.employe.PilotService;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: SsmController 
 * @author xyx 
 * @date 2018年10月26日 下午2:06:48 
 *  
 */
@RestController
@CrossOrigin
@RequestMapping("/ssm/api/alls")
@Api(tags="接口")
public class SsmController {
	@Autowired
	AircraftService aircraftService;

	@Autowired
	AircraftFlySumRepository aircraftFlySumRepository;
	
	@Autowired
	PilotService pilotService;
	
	@Autowired
	DocListService DocListService;
	
	@RequestMapping(value = "search/searchfly", method = RequestMethod.GET)
	@ApiIgnore
	@ApiImplicitParam(value = "id", name = "companyId", dataType = "string", paramType = "query")
	public DataResponse<List<String>> getBycompanyIdas(@RequestParam String companyId) {
		DataResponse<List<String>> response = new DataResponse<List<String>>();
		try {
			List<String> list = null;

			list = aircraftService.findflyLists(companyId);

			response.setData(list);

			response.setResultCode(Constants.OK);

		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/search/findallaircraftfly", method = RequestMethod.GET)
	@ApiIgnore
	@ApiOperation(value = "获取公司同型号航空器飞行总时长",notes = "返回两个参数，第一个参数为航空器型号，第二个参数为该型号航空器飞行总时长")
	@ApiImplicitParams({
		@ApiImplicitParam(value="公司ID(可以是','分隔的多个id，可以是单个id)",name="companyIds",dataType="String"),
		@ApiImplicitParam(value="查询开始时间",name="startDate",dataType="String"),
		@ApiImplicitParam(value="查询截止时间",name="endDate",dataType="String"),
	})
	public DataResponse<List<Object>> findlistByCompany(
			@RequestParam(value = "companyIds", required = false) String companyIds,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate) {
		DataResponse<List<Object>> response = new DataResponse<List<Object>>();
		try {
			List<Object> list = new ArrayList<Object>();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date sdate = sdf.parse(startDate);
			Date edate = sdf.parse(endDate);
			list = aircraftFlySumRepository.countIdAndTime(companyIds, sdate, edate);
			response.setData(list);
			response.setResultCode(Constants.OK);
		} catch (Exception e) {
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	//飞行人员统计  根据企业id查询
	@RequestMapping(value="/search/company/statisticBycompanyIds",method = RequestMethod.GET)
	public DataResponse<List<PilotListVO>> statisticBycompanyIds(
			@RequestParam final String companyId,
			@RequestParam final String beginDate,
			@RequestParam final String endDate
			){
		
		DataResponse<List<PilotListVO>> response = new DataResponse<List<PilotListVO>>();
		
		try {
			List<PilotListVO> pilots = pilotService.statisticBycompanyIds(companyId, beginDate,endDate);
			response.setData(pilots);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResultCode(Constants.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	@RequestMapping(value = "/search/findlistbycompanyid", method = RequestMethod.GET)
	public Page<DocListDto> finddoclist(@RequestParam(value = "pagesize", required = false) String pagesize,
			@RequestParam(value = "pageno", required = false) String pageno,
			@RequestParam(value = "companyId", required = false) String companyid
			){
		Pageable pageable = new PageRequest(Integer.parseInt(pageno), Integer.parseInt(pagesize));
		Page<DocListDto> pagelist = null;
	
		pagelist=DocListService.finddoclist(companyid, pageable);

		return pagelist;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiOperation(value = "获取当前公文信息")
	@ApiIgnore
	@ApiImplicitParams(
			{
				@ApiImplicitParam(value="公文ID",name="id",dataType="Long")
			}
			)
	public DocList getById(@PathVariable("id") Long id) {
		return DocListService.findById(id);
	}

}
