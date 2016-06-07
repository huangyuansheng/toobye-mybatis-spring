/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/05/30.
 * 
 */
package com.toobye.common.framework.spring;

import javax.annotation.Nonnull;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * <pre> Spring配置设定.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/05/30  huangys  v1.0      Create
 * </pre>
 * 
 */
@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class SpringConfig {
	
	/**
	 * <pre> 通过类注解创建Spring的Context对象. </pre>
	 */
	private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext("applicationContext.xml"); 
	
	/**
	 * <pre> 获得Spring-Context.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/06/07  huangys  Create
	 * </pre>
	 * 
	 * @return Spring-Context
	 */
	@Nonnull
	public static ApplicationContext getContext() {
		return CONTEXT;
	}
	
	/**
     * <pre> 使System.setProperty、@PropertySource引入的属性值可以在配置文件中生效.
     * 
     * Modification History:
     * Date        Author   Action
     * 2014/05/21  huangys  Create
     * </pre>
     * 
     * @return Nothing
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
       return new PropertySourcesPlaceholderConfigurer();
    }
    
}
