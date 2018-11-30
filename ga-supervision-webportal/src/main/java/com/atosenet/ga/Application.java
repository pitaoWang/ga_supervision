package com.atosenet.ga;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;

@SpringBootApplication
public class Application extends SpringBootServletInitializer
{
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
	
    public static void main(String[] args) throws Exception
    {
    	SpringApplication application = new SpringApplication(Application.class);
        application.setBannerMode(Mode.CONSOLE);
        application.run(args); 
        
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				System.out.println("System exit now !");
			}
		}));
        
    }
    
    @Bean
    public MDCInsertingServletFilter defaultLogbackFilter(){
    	return new MDCInsertingServletFilter();
    }
    
}
