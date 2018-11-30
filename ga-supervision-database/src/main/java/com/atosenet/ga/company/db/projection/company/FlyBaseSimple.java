package com.atosenet.ga.company.db.projection.company;

public interface FlyBaseSimple {
	long getFlybaseid();
	String getFlybasename();
	CompanySimple getcompanyId();
	
	 interface CompanySimple{
	    	int getCompanyId();
	    	String getName();
	    }
}
