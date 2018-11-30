/**  
 * 
 * @Title: PrepareReleaseRepository.java 
 * @Package com.atosenet.ga.company.db.repos.ga.company  
 * @author xyx   
 * @date 2018年9月27日 下午2:20:32 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.repos.ga.company;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


import com.atosenet.ga.company.db.model.ga.company.PrepareRelease;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: PrepareReleaseRepository 
 * @author xyx 
 * @date 2018年9月27日 下午2:20:32 
 *  
 */
public interface PrepareReleaseRepository extends PagingAndSortingRepository<PrepareRelease, Long>,JpaSpecificationExecutor<PrepareRelease>{

}
