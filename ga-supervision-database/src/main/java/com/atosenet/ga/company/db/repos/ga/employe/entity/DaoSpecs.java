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
import com.atosenet.ga.company.db.model.ga.employe.RecordFssuser;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: DaoSpec 
 * @author xyx 
 * @date 2018年8月7日 上午10:54:26 
 *  
 */
public class DaoSpecs {
	public static Specification<RecordFssuser> getSpec(final String fssId, final String name, final String tel 
			){
		return new Specification<RecordFssuser>(){
			
            public Predicate toPredicate(Root<RecordFssuser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p1 = null;
                
                if (fssId != null && !fssId.equals("")) {
                	Expression<String> exp = root.<String>get("fssId");
                	Predicate p3 = exp.in(fssId.split(","));
                   
                    	 p1 = p3;
                   
                }
                
                
               
                    if (name != null && !name.equals("")) {
                   
                        Predicate p2 = cb.like(root.get("name").as(String.class),"%"+name+"%");
                        if (p1 != null) {
                            p1=cb.and(p1, p2);
                        } else {
                            p1 = p2;
                        }
                    }
                    
                    if (tel != null && !tel.equals("")) {
                       
                        Predicate p2 = cb.like(root.get("tel").as(String.class),"%"+tel+"%");
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