package com.atosenet.ga.sys.controller.manage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.mgt.service.ga.company.GaCompanyService;
import com.atosenet.ga.sys.common.constant.CommonConstant;
import com.atosenet.ga.sys.common.utils.ResultUtil;
import com.atosenet.ga.sys.common.vo.Result;
import com.atosenet.ga.sys.entity.Department;
import com.atosenet.ga.sys.entity.User;
import com.atosenet.ga.sys.service.DepartmentService;
import com.atosenet.ga.sys.service.UserService;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;


/**
 * @author Exrick
 */
@Slf4j
@RestController
@Api(tags = "部门管理接口")
@ApiIgnore
@RequestMapping("/xboot/department")
//@CacheConfig(cacheNames = "department")
@Transactional
public class DepartmentController {

	@Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/findByParentId/{parentId}",method = RequestMethod.GET)
    public Result<List<Department>> findByParentId(@PathVariable Long parentId) {
    	List<Department> list = new ArrayList<>();
    	list = departmentService.findByParentIdAndTypeOrderBySortOrder(parentId,"2"); 
    	
    	return new ResultUtil<List<Department>>().setData(list);
		
	}
    
    @RequestMapping(value = "/getByParentId/{parentId}",method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取")
    //@Cacheable(key = "#parentId")
    public Result<List<Department>> getByParentId(@PathVariable Long parentId,String type){
    	List<Department> list = new ArrayList<>();
    if(type.equals("1")){
    	list = departmentService.findByParentIdOrderBySortOrder(parentId);  
     }else{
    		 list = departmentService.findByIdOrderBySortOrder(parentId); 
     }
        // lambda表达式
        list.forEach(item -> {
            if(CommonConstant.PARENT_ID!=item.getParentId()){
                Department parent = departmentService.get(item.getParentId());
                item.setParentTitle(parent.getTitle());
            }else{
                item.setParentTitle("一级部门");
            }
        });
        return new ResultUtil<List<Department>>().setData(list);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加")
   // @CacheEvict(key = "#department.parentId")
    public Result<Department> add(@ModelAttribute Department department){

        Department d = departmentService.save(department);
        // 如果不是添加的一级 判断设置上级为父节点标识
        if(CommonConstant.PARENT_ID!=(department.getParentId())){
            Department parent = departmentService.get(department.getParentId());
            if(parent.getIsParent()==null||!parent.getIsParent()){
                parent.setIsParent(true);
                departmentService.update(parent);
                // 更新上级节点的缓存
                //redisTemplate.delete("department::" + parent.getParentId());
            }
        }
        return new ResultUtil<Department>().setData(d);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ApiOperation(value = "编辑")
    public Result<Department> edit(@ModelAttribute Department department){

        Department d = departmentService.update(department);
        // 手动删除所有部门缓存
        //Set<String> keys = redisTemplate.keys("department:" + "*");
       // redisTemplate.delete(keys);
        // 删除所有用户缓存
       // Set<String> keysUser = redisTemplate.keys("user:" + "*");
       // redisTemplate.delete(keysUser);
        return new ResultUtil<Department>().setData(d);
    }

    @RequestMapping(value = "/delByIds/{ids}",method = RequestMethod.DELETE)
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delByIds(@PathVariable Long[] ids){

        for(Long id:ids){
            List<User> list = userService.findByDepartmentId(id);
            if(list!=null&&list.size()>0){
                return new ResultUtil<Object>().setErrorMsg("删除失败，包含正被用户使用关联的部门");
            }
        }
        for(Long id:ids){
            departmentService.delete(id);
            
        }
        /*if(!StrUtil.isEmpty(idsp)){
       	List<Long> list = new ArrayList<>();
        	for(int i =0;i<idsp.split(",").length;i++){
       		list.add(Long.parseLong(idsp.split(",")[i]));
        	}
        departmentService.deletes(list);
        }*/
        // 手动删除所有部门缓存
       // Set<String> keys = redisTemplate.keys("department:" + "*");
       // redisTemplate.delete(keys);
        return new ResultUtil<Object>().setSuccessMsg("批量通过id删除数据成功");
   }

}
