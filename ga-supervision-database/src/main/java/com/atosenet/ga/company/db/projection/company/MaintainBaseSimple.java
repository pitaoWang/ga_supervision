package com.atosenet.ga.company.db.projection.company;

public interface MaintainBaseSimple {
	long getMaintainbaseid();
    String getMaintainbasename();
    
    CompanySimple getCompanyId();
    
    interface CompanySimple{
    	int getCompanyId();
    	String getName();
    }
}
