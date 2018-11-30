/** 
 * Project Name:ga-company-mgt 
 * File Name:SchedulerConfig.java 
 * Package Name:com.atosenet.ga.sys.config.quartz 
 * Date:2018年9月2日上午2:42:38 
 * Copyright (c) 2018, wpt1225_gmail_com All Rights Reserved. 
 * 
 */ 
package com.atosenet.ga.sys.config.quartz;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/** 
* ClassName: SchedulerConfig <br/> 
* Function: TODO ADD FUNCTION. <br/> 
* Reason: TODO ADD REASON(可选). <br/> 
* date: 2018年9月2日 上午2:42:38 <br/> 
* 
* @author Administrator 
* @version  
* @since JDK 1.8 
*/
@Configuration
public class SchedulerConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
 //       schedulerFactoryBean.setJobFactory(springJobFactory);
        return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }
    
}
