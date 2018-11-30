package com.atosenet.ga.sys.controller.common;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.sys.common.utils.ResultUtil;
import com.atosenet.ga.sys.common.vo.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author Exrickx
 */
@Slf4j
@RestController
@Api(tags = "Security相关接口")
@ApiIgnore
@RequestMapping("/xboot/common")
@Transactional
public class SecurityController {

    @RequestMapping(value = "/needLogin",method = RequestMethod.GET)
    @ApiOperation(value = "没有登录")
    public Result<Object> needLogin(){
        return new ResultUtil<Object>().setErrorMsg(401, "您还未登录");
    }
}
