package com.atosenet.ga.company.core.common.model.response;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 创建时间:  2017/07/25 18:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /** 返回结果码 **/
    private int resultCode = 200;
    /** 错误描述 **/
    private String message = null;
    
}
