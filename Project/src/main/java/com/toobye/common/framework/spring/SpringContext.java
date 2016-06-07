/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/05/30.
 * 
 */
package com.toobye.common.framework.spring;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;

import com.toobye.common.base.Exceptions;
import com.toobye.common.framework.ProjectConfig;
import com.toobye.common.framework.mybatis.DBEnumTypeHandler;
import com.toobye.common.string.StringFormat;

/**
 * <pre> Spring管理.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/05/30  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class SpringContext {
	
	private SpringContext() { }
	
	static {
		// 项目环境变量加入至系统环境变量中
		setProperties(ProjectConfig.getAllProperties());
		// 初始化Mybatis数据库枚举类TypeHandler
		DBEnumTypeHandler.init();
	}
	
	/**
	 * <pre> 获得Spring-Bean.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/05/29  huangys  Create
	 * </pre>
	 * 
	 * @param name bean名称
	 * @return Spring-Bean
	 */
	@Nonnull
	public static Object getBean(@Nonnull final String name) {
		return getContext().getBean(name);
	}
	
	/**
	 * <pre> 获得需初始化参数的Spring-Bean.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/20  huangys  Create
	 * </pre>
	 * 
	 * @param name Bean名称
	 * @param params 所需初始化参数
	 * @return Spring-Bean
	 */
	public static Object getBean(@Nonnull final String name, @Nonnull Object... args) {
		return getContext().getBean(name, args);
	}
	
	/**
	 * <pre> 获得Spring-Bean.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/05/22  huangys  Create
	 * </pre>
	 * 
	 * @param <T> Bean类型
	 * @param requiredType Bean类型
	 * @return Spring-Bean
	 */
	@Nonnull
	public static <T> T getBean(@Nonnull final Class<T> requiredType) {
		return getContext().getBean(requiredType);
	}
	
	/**
	 * <pre> 获得需初始化参数的Spring-Bean.
	 * 该类型的Bean只能是prototype
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/05/29  huangys  Create
	 * </pre>
	 * 
	 * @param <T> Bean类型
	 * @param requiredType Bean类型
	 * @param params 所需初始化参数
	 * @return Spring-Bean
	 */
	@Nonnull
	public static <T> T getBean(@Nonnull final Class<T> requiredType, @Nullable final Object... params) {
		return getBean(StringFormat.uncapitalize(requiredType.getSimpleName()), requiredType, params);
	}
	
	/**
	 * <pre> 获得Spring-Bean.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/05/28  huangys  Create
	 * </pre>
	 * 
	 * @param <T> Bean类型
	 * @param name Bean名称
	 * @param requiredType Bean类型
	 * @return Spring-Bean
	 */
	@Nonnull
	public static <T> T getBean(@Nonnull final String name, @Nonnull final Class<T> requiredType) {
		return getContext().getBean(name, requiredType);
	}
	
	/**
	 * <pre> 获得需初始化参数的Spring-Bean.
	 * 该类型的Bean只能是prototype
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/05/29  huangys  Create
	 * </pre>
	 * 
	 * @param <T> Bean类型
	 * @param name Bean名称
	 * @param requiredType Bean类型
	 * @param params 所需初始化参数
	 * @return Spring-Bean
	 */
	@SuppressWarnings("unchecked")
	@Nonnull
	public static <T> T getBean(@Nonnull final String name, @Nonnull final Class<T> requiredType, @Nullable final Object... params) {
		return (T) getContext().getBean(name, params);
	}
	
	/**
	 * <pre> 获得属性值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/05/22  huangys  Create
	 * </pre>
	 * 
	 * @param key 属性名称
	 * @return 属性值
	 */
	@Nullable
	public static String getProperty(@Nonnull final String key) {
		return getContext().getEnvironment().getProperty(key);
	}
	
	/**
	 * <pre> 获得属性值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/05/22  huangys  Create
	 * </pre>
	 * 
	 * @param key 属性名称
	 * @param defaultValue 默认属性值
	 * @return 属性值
	 */
	@Nonnull
	public static String getProperty(@Nonnull final String key, @Nonnull final String defaultValue) {
		return getContext().getEnvironment().getProperty(key, defaultValue);
	}
	
	/**
	 * <pre> 获得属性值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/05/22  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 属性值类型
	 * @param key 属性名称
	 * @param targetType 属性值类型
	 * @return 属性值
	 */
	@Nullable
	public static <T> T getProperty(@Nonnull final String key, @Nonnull final Class<T> targetType) {
		return getContext().getEnvironment().getProperty(key, targetType);
	}
	
	/**
	 * <pre> 获得属性值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/05/22  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 属性值类型
	 * @param key 属性名称
	 * @param targetType 属性值类型
	 * @param defaultValue 默认属性值
	 * @return 属性值
	 */
	@Nonnull
	public static <T> T getProperty(@Nonnull final String key, @Nonnull final Class<T> targetType, @Nonnull final T defaultValue) {
		return getContext().getEnvironment().getProperty(key, targetType, defaultValue);
	}
	
	/**
	 * <pre> 获得属性值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/05/22  huangys  Create
	 * </pre>
	 * 
	 * @param key 属性名称
	 * @return 属性值<span style="color:red;font-weight:bold">若属性值不存在会抛出异常</span>
	 */
	@Nonnull
	public static String getRequiredProperty(@Nonnull final String key) {
		return getContext().getEnvironment().getRequiredProperty(key);
	}
	
	/**
	 * <pre> 获得属性值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/05/22  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 属性值类型
	 * @param key 属性名称
	 * @param targetType 属性值类型
	 * @return 属性值<span style="color:red;font-weight:bold">若属性值不存在会抛出异常</span>
	 */
	@Nonnull
	public static <T> T getRequiredProperty(@Nonnull final String key, @Nonnull final Class<T> targetType) {
		return getContext().getEnvironment().getRequiredProperty(key, targetType);
	}
	
	/**
	 * <pre> 设置多个属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/05/21  huangys  Create
	 * </pre>
	 * 
	 * @param propertiesMap 属性Map
	 */
	public static void setProperties(@Nonnull final Map<String, String> propertiesMap) {
		// 添加动态设置的属性
		// 1. 属性值可以包含其他属性${property}
		// 2. 属性值可以在运行过程中使用System.setProperty变更
		// 3. System.setProperties会丢失原有属性值
		for (Entry<String, String> property : propertiesMap.entrySet()) {
			setProperty(property.getKey(), property.getValue());
		}
	}
	
	/**
	 * <pre> 设置属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/05/21  huangys  Create
	 * </pre>
	 * 
	 * @param key 属性名称
	 * @param value 属性值
	 */
	public static void setProperty(@Nonnull final String key, @Nonnull final String value) {
		System.setProperty(key, value);
	}
	
	private static ApplicationContext getContext() {
		return SpringConfig.getContext();
	}
	
	/**
	 * <pre> 返回数据库连接.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/29  huangys  Create
	 * </pre>
	 * 
	 * @return 数据库连接
	 */
	public static SqlSession getSqlSession() {
		return getBean(SqlSessionTemplate.class);
	}
	
	/**
	 * <pre> 返回数据库连接.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/29  huangys  Create
	 * </pre>
	 * 
	 * @return 数据库连接
	 */
	public static SqlSession getSqlSessionBatch() {
		Exceptions.throwNoImplementation();
		return null;
		// return getBean(SqlSessionFactory.class).openSession(ExecutorType.BATCH, false);
	}
	
	/**
	 * <pre> 获得非Spring管理的数据库连接.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/07  huangys  Create
	 * </pre>
	 * 
	 * @return 数据库连接
	 */
	public static SqlSession getSqlSessionNotSpringManaged() {
		Exceptions.throwNoImplementation();
		return null;
	}
    
}
