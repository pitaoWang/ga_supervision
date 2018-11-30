package com.atosenet.ga.company.core.common.model.response;

public class ApiResponse<T> extends BaseResponse{

	private static final long serialVersionUID = 1L;
	
	T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
