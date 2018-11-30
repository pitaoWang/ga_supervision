package com.atosenet.ga.company.db.projection.sys;

public interface UserInfoForAuth {
	String getAccount();
	String getEmail();
	String getMobilephone();
	String getIpaddress();
	
	OrgForAuth getOrg();
	
	RoleForAuth getRoles();
	
	interface OrgForAuth {
		Long getId();
		String getName();
		String getJobtitle();
		String getTelephone();
	}
	
	interface RoleForAuth {
		String getRolename();
	}
}
