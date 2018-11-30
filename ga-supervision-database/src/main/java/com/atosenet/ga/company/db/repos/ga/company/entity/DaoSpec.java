/**  
 * 
 * @Title: DaoSpec.java 
 * @Package com.atosenet.ga.company.db.repos.ga.employe.entity  
 * @author xyx   
 * @date 2018年8月7日 上午10:54:26 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.repos.ga.company.entity;



import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.springframework.data.jpa.domain.Specification;

import com.atosenet.ga.company.db.model.ga.company.Aircraft;
import com.atosenet.ga.company.db.model.ga.employe.Employee;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: DaoSpec 
 * @author xyx 
 * @date 2018年8月7日 上午10:54:26 
 *  
 */
public class DaoSpec {
	public static Specification<Aircraft> getSpec(final String companyId, final String regNumber, final String aircraftStatus){
		return new Specification<Aircraft>(){
			
            public Predicate toPredicate(Root<Aircraft> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p1 = null;
                
                Predicate p4 = cb.equal(root.get("delflag"),0);
                p1 = p4;
                Expression<String> exp = root.<String>get("companyId");
                Predicate p3 = exp.in(companyId.split(","));
                if (p1 != null) {
                    p1=cb.and(p1, p3);
                } else {
                	p1 = p3;
                }
                    if (regNumber != null && !regNumber.equals("")) {
                   
                        Predicate p2 = cb.like(root.get("regNumber").as(String.class),"%"+regNumber+"%");
                        if (p1 != null) {
                            p1=cb.and(p1, p2);
                        } else {
                            p1 = p2;
                        }
                    }    
                    if (aircraftStatus != null && !aircraftStatus.equals("")) {
                    	
                        Predicate p2 = cb.equal(root.get("aircraftStatus"),aircraftStatus);
                        if (p1 != null) {
                            p1=cb.and(p1, p2);
                        } else {
                            p1 = p2;
                        }
                    }
                    
                return  p1;
            }
		};
    }
}