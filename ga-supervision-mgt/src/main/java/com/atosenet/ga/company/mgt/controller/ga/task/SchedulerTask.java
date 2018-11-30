/**  
 * 
 * @Title: SchedulerTask.java 
 * @Package com.atosenet.ga.company.mgt.controller.ga.task  
 * @author dgw   
 * @date 2018年8月9日 下午6:44:15 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.controller.ga.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/** 
 * TODO(定时任务)
 * 
 * @ClassName: SchedulerTask 
 * @author dgw 
 * @date 2018年8月9日 下午6:44:15 
 *  
 */
//@Component
public class SchedulerTask {
	
	private int count=0;

    @Scheduled(cron="* * * */6 * ?")
    private void process(){
        System.out.println("this is scheduler task runing  "+(count++));
    }
}
