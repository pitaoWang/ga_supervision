package com.atosenet.ga.sys.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.atosenet.ga.sys.base.XbootBaseService;
import com.atosenet.ga.sys.common.vo.SearchVo;
import com.atosenet.ga.sys.entity.Log;

/**
 * 日志接口
 * @author Exrickx
 */
public interface LogService extends XbootBaseService<Log,String> {

    /**
     * 日志搜索
     * @param key
     * @param searchVo
     * @param pageable
     * @return
     */
    Page<Log> searchLog(String key, SearchVo searchVo, Pageable pageable);

    /**
     * 删除所有
     */
    void deleteAll();
}
