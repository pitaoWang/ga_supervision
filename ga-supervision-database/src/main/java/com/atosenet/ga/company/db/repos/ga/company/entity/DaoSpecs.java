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



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.springframework.data.jpa.domain.Specification;

import com.atosenet.ga.company.db.model.ga.company.Aircraft;
import com.atosenet.ga.company.db.model.ga.company.PrepareRelease;
import com.atosenet.ga.company.db.model.ga.employe.Employee;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: DaoSpec 
 * @author xyx 
 * @date 2018年8月7日 上午10:54:26 
 *  
 */
public class DaoSpecs {
	public static Specification<PrepareRelease> getSpec(final String organizationId, final String aircraftNo, final String releaseTime){
		return new Specification<PrepareRelease>(){
			
            public Predicate toPredicate(Root<PrepareRelease> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p1 = null;
                
                               
                Predicate p4 = cb.equal(root.get("deleteFlag"),0);
                p1 = p4;
                Expression<String> exp = root.<String>get("organizationId");
                Predicate p3 = exp.in(organizationId.split(","));
                if (p1 != null) {
                    p1=cb.and(p1, p3);
                } else {
                	p1 = p3;
                }
                
                    if (aircraftNo != null && !aircraftNo.equals("")) {
                   
                        Predicate p2 = cb.like(root.get("aircraftNo").as(String.class),"%"+aircraftNo+"%");
                        if (p1 != null) {
                            p1=cb.and(p1, p2);
                        } else {
                            p1 = p2;
                        }
                    }    
                    if (releaseTime != null && !releaseTime.equals("")&& !releaseTime.equals(",")) {
                    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                		Date sdate = null;
                		Date edate = null;
						try {
							sdate = sdf.parse(releaseTime.split(",")[0]);
							edate = sdf.parse(releaseTime.split(",")[1]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                		
                        Predicate p2 = cb.between(root.<Date>get("releaseTime"),sdate,edate);
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