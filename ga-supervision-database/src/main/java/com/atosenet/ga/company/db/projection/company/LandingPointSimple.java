package com.atosenet.ga.company.db.projection.company;

public interface LandingPointSimple {
	long getId();
	String getName();
	CompanySimple getcompanyId();
	
	 interface CompanySimple{
	    	int getCompanyId();
	    	String getName();
	    }
}
