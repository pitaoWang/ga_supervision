/**  
 * 
 * @Title: RecordFssuserRepository.java 
 * @Package com.atosenet.ga.company.db.repos.ga.employe  
 * @author xyx   
 * @date 2018年9月21日 下午4:51:42 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.repos.ga.employe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atosenet.ga.company.db.model.ga.employe.RecordFssuser;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: RecordFssuserRepository 
 * @author xyx 
 * @date 2018年9月21日 下午4:51:42 
 *  
 */
public interface RecordFssuserRepository extends JpaRepository<RecordFssuser, Long>,JpaSpecificationExecutor<RecordFssuser>{

}
