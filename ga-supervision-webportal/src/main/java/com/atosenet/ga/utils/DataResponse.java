package com.atosenet.ga.utils;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class DataResponse<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 返回结果码 **/
    private int resultCode = -1;
    /** 错误描述 **/
    private String message = null;
    
	/** 数据 **/
    private T data = null;
    
}