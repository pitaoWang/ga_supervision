package com.atosenet.ga.fileserver.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class FileInfo extends BaseResponse {
	private static final long serialVersionUID = 1L;
	
	String name;
	
	String url;
}
