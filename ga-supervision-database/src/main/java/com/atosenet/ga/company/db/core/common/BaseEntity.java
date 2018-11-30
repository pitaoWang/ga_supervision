package com.atosenet.ga.company.db.core.common;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public class BaseEntity implements Serializable {
	public static final Integer DEL_FLAG_NORMAL = 0;
	public static final Integer DEL_FLAG_DELETED = 1;
	public static final Integer DEL_FLAG_DRAFT = 2;
	
	public static final String DEL_FLAG_NORMAL_STR = "0";
	public static final String DEL_FLAG_DELETED_STR = "1";
	public static final String DEL_FLAG_DRAFT_STR = "2";
}
