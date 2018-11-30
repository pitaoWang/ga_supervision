package com.atosenet.ga.sys.service;

import java.util.List;

import com.atosenet.ga.sys.base.XbootBaseService;
import com.atosenet.ga.sys.entity.QuartzJob;

/**
 * 定时任务接口
 * @author Exrick
 */
public interface QuartzJobService extends XbootBaseService<QuartzJob,String> {

    /**
     * 通过类名获取
     * @param jobClassName
     * @return
     */
    List<QuartzJob> findByJobClassName(String jobClassName);
}