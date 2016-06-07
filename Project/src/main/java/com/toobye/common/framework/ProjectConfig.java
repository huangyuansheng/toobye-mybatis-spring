/*
 * Copyright 2013 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2013/06/28.
 * 
 */
package com.toobye.common.framework;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.db.DBConn;
import com.toobye.common.db.JdbcConnInfo;
import com.toobye.common.io.PropertyFile;
import com.toobye.common.string.StringSplit;
import com.toobye.common.string.StringUtils;

/**
 * <pre> 工程配置.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/01/22  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class ProjectConfig {
	
	private ProjectConfig() { }
	
	private static final String PROJECT_CONFIG_FILE = "project.properties";
	private static final Map<String, String> ALL_PROPERTIES;
	private static final Map<String, Map<String, String>> GROUP_PROPERTIES = new HashMap<String, Map<String, String>>(); 
	
	static {
		// 解析属性文件，并将属性分组
		ALL_PROPERTIES = PropertyFile.parse(PROJECT_CONFIG_FILE);
		for (Entry<String, String> entry : ALL_PROPERTIES.entrySet()) {
			String[] keyParts = StringSplit.splitChar(entry.getKey(), '.', 2);
			if (keyParts.length == 2 
					&& !StringUtils.isBlank(keyParts[0])
					&& !StringUtils.isBlank(keyParts[1])) {
				addGroupProperties(keyParts[0], keyParts[1], entry.getValue());
			}
		}
	}
	
	/**
	 * <pre> 添加至分组属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/06/07  huangys  Create
	 * </pre>
	 * 
	 * @param group 组名
	 * @param key 属性名称（不含组名）
	 * @param value 属性值
	 */
	private static void addGroupProperties(final String group, final String key, final String value) {
		if (GROUP_PROPERTIES.containsKey(group)) {
			GROUP_PROPERTIES.get(group).put(key, value);
		} else {
			Map<String, String> groupMap = new HashMap<String, String>();
			groupMap.put(key, value);
			GROUP_PROPERTIES.put(group, groupMap);
		}
	}
	
	/**
	 * <pre> 获得属性值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/22  huangys  Create
	 * </pre>
	 * 
	 * @param name 属性名称
	 * @return 属性值
	 */
	@Nullable
	public static String getProperty(@Nonnull final String name) {
		return ALL_PROPERTIES.get(name);
	}
	
	/**
	 * <pre> 获得属性值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/24  huangys  Create
	 * </pre>
	 * 
	 * @param name 属性名称
	 * @return 属性值<span style="color:red;font-weight:bold">属性值不存在或无法转换会抛出异常</span>
	 */
	@Nonnull
	public static double getPropertyDouble(@Nonnull final String name) {
		return Double.parseDouble(ALL_PROPERTIES.get(name));
	}
	
	/**
	 * <pre> 获得属性值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/24  huangys  Create
	 * </pre>
	 * 
	 * @param name 属性名称
	 * @return 属性值<span style="color:red;font-weight:bold">属性值不存在或无法转换会抛出异常</span>
	 */
	@Nonnull
	public static long getPropertyLong(@Nonnull final String name) {
		return Long.parseLong(ALL_PROPERTIES.get(name));
	}
	
	/**
	 * <pre> 获得属性值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/24  huangys  Create
	 * </pre>
	 * 
	 * @param name 属性名称
	 * @return 属性值<span style="color:red;font-weight:bold">属性值不存在或无法转换会抛出异常</span>
	 */
	@Nonnull
	public static int getPropertyInteger(@Nonnull final String name) {
		return Integer.parseInt(ALL_PROPERTIES.get(name));
	}
	
	/**
	 * <pre> 获得全部属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/22  huangys  Create
	 * </pre>
	 * 
	 * @return 包含全部属性的Map
	 */
	@Nonnull
	public static Map<String, String> getAllProperties() {
		return ALL_PROPERTIES;
	}
	
	/**
	 * <pre> 获得单组属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/22  huangys  Create
	 * </pre>
	 * 
	 * @param group 组名
	 * @return 单组属性
	 */
	@Nullable
	public static Map<String, String> getGroupProperties(@Nonnull final String group) {
		return GROUP_PROPERTIES.get(group);
	}
	
	/**
	 * <pre> 获得元数据库连接的相关属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/22  huangys  Create
	 * </pre>
	 * 
	 * @return 元数据库相关属性
	 */
	@Nonnull
	public static JdbcConnInfo getMDBJdbcConnInfo() {
		return JdbcConnInfo.getJdbcConnInfo(getProperty("mdb.driver"), getProperty("mdb.url"), getProperty("mdb.user"), getProperty("mdb.password"));
	}
	
	/**
	 * <pre> 获得元数据库的表前缀.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/06/16  huangys  Create
	 * </pre>
	 * 
	 * @return 表前缀
	 */
	public static String getMDBPrefix() {
		return getProperty("mdb.prefix");
	}
	
	/**
	 * <pre> 获得元数据库连接的相关属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/22  huangys  Create
	 * </pre>
	 * 
	 * @return 元数据库相关属性
	 */
	@Nonnull
	public static Connection getMDBConnection() {
		return DBConn.getConnection(getMDBJdbcConnInfo());
	}

}
