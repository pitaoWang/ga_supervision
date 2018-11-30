/**  
 * 
 * @Title: prepareReleaseController.java 
 * @Package com.atosenet.ga.company.mgt.controller.ga.company  
 * @author xyx   
 * @date 2018年9月27日 下午2:06:27 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.controller.ga.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.company.Aircraft;
import com.atosenet.ga.company.db.model.ga.company.PrepareRelease;
import com.atosenet.ga.company.mgt.config.Constants;
import com.atosenet.ga.company.mgt.service.ga.company.AircraftService;
import com.atosenet.ga.company.mgt.service.ga.company.PrepareReleaseService;
import com.atosenet.ga.company.mgt.service.ga.employe.response.DataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: prepareReleaseController 
 * @author xyx 
 * @date 2018年9月27日 下午2:06:27 
 *  
 */
@RestController
@CrossOrigin
@ApiIgnore	
@RequestMapping("/eim/api/prepareRelease")
@Api(tags="放行单信息")
public class PrepareReleaseController {
	@Autowired
	PrepareReleaseService prepareReleaseService;
	
	@RequestMapping(value="search/findPrepareReleaseByInfo",method = RequestMethod.GET)
	 public Page<PrepareRelease> findAircraftByInfo(@RequestParam final String organizationId,@RequestParam final String aircraftNo,
			 @RequestParam("releaseTime")  String releaseTime,
			 @RequestParam final Integer currentPage, @RequestParam("pageSize") final Integer pageSize){
			long start = System.currentTimeMillis();
			Page<PrepareRelease> list = null;
			try 
			{
				
				   Pageable pageable = new PageRequest(currentPage-1, pageSize);
				
					list = prepareReleaseService.findAircraftByInfo(organizationId,aircraftNo,releaseTime,pageable);
					
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			finally 
			{
				System.out.print("time = "+(System.currentTimeMillis() - start));
			}
			
			return list;
		}
}
