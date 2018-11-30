package com.atosenet.ga.company.db.config;

import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.cache.ehcache.EhCacheRegionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.google.common.collect.Maps;

@Configuration
@EnableJpaRepositories(basePackages="com.atosenet.ga")
@EnableTransactionManagement
public class JpaConfig {

	@Value("${app.db.genddl:true}")
	private boolean genDdl = true;
	
	@Value("${app.db.showsql:true}")
	private boolean showSql = true;
	
	@Bean
	@ConfigurationProperties("spring.datasource.druid.ao")
	public DataSource dataSourceAo() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(genDdl);
		vendorAdapter.setShowSql(showSql);
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.atosenet.ga");
		factory.setDataSource(dataSourceAo());
		
		Map<String , Object> jpaProp = Maps.newHashMap();
		jpaProp.put("net.sf.ehcache.configurationResourceName", "config/cache/ehcache-hibernate.xml");
		jpaProp.put("hibernate.cache.use_second_level_cache", true);
		jpaProp.put("hibernate.cache.use_query_cache", true);
		jpaProp.put("hibernate.cache.region.factory_class", EhCacheRegionFactory.class);
		jpaProp.put("javax.persistence.sharedCache.mode", "ENABLE_SELECTIVE");
		
		factory.setJpaPropertyMap(jpaProp);
		
		return factory;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return txManager;
	}
}
