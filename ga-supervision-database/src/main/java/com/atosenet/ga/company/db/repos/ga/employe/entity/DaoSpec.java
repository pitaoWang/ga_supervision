/**  
 * 
 * @Title: DaoSpec.java 
 * @Package com.atosenet.ga.company.db.repos.ga.employe.entity  
 * @author xyx   
 * @date 2018年8月7日 上午10:54:26 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.repos.ga.employe.entity;



import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.springframework.data.jpa.domain.Specification;


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
	public static Specification<Employee> getSpec(final String companyId, final String userName, final String jobTitle,final String techpos,
			final String homeMobile,final String cardId){
		return new Specification<Employee>(){
			
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p1 = null;
                Predicate p4 = cb.equal(root.get("flag"),0);
                p1 = p4;
                Expression<String> exp = root.<String>get("companyId");
                Predicate p3 = exp.in(companyId.split(","));
                //p1 = p3;
                if (p1 != null) {
                    p1=cb.and(p1, p3);
                } else {
                	p1 = p3;
                }
                    if (userName != null && !userName.equals("")) {
                   
                        Predicate p2 = cb.like(root.get("userName").as(String.class),"%"+userName+"%");
                        if (p1 != null) {
                            p1=cb.and(p1, p2);
                        } else {
                            p1 = p2;
                        }
                    }
                    if (jobTitle != null && !jobTitle.equals("")) {
                    	 System.out.println(jobTitle);
                    	Expression<String> exps = root.<String>get("jobTitle");
                    	
                        Predicate p2 = exps.in(jobTitle.split(","));
                        
                        //System.out.println(p2);
                    	//String[] strs = jobTitle.split(",");
                    	
                        //Predicate p2 = cb.like(root.get("jobTitle").as(String.class),"%"+jobTitle+"%");
      
                   
                        if (p1 != null) {
                            p1=cb.and(p1, p2);
                        } else {
                            p1 = p2;
                        }
                    }
                    if (techpos != null && !techpos.equals("")) {
                        
                        Predicate p2 = cb.like(root.get("techpos").as(String.class),"%"+techpos+"%");
                        if (p1 != null) {
                            p1=cb.and(p1, p2);
                        } else {
                            p1 = p2;
                        }
                    }
                    
                    if (homeMobile != null && !homeMobile.equals("")) {
                       
                        Predicate p2 = cb.like(root.get("homeMobile").as(String.class),"%"+homeMobile+"%");
                        if (p1 != null) {
                            p1=cb.and(p1, p2);
                        } else {
                            p1 = p2;
                        }
                    }
                    
                    if (cardId != null && !cardId.equals("")) {
                    	
                        Predicate p2 = cb.equal(root.get("cardId"),cardId);
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