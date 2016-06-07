/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/05/20.
 * 
 */
package com.toobye.common.framework.mybatis;

import javax.annotation.Nonnull;

/**
 * <pre> 设置数据库连接池配置.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/05/20  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class DBProperties {
	
	private DBProperties() { }

	/**
	 * <pre> 设置数据库连接池.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/20  huangys  Create
	 * </pre>
	 * 
	 * @param initialPoolSize 初始大小
	 * @param minPoolSize 最小大小
	 * @param maxPoolSize 最大大小
	 */
	public static void set(@Nonnull final int initialPoolSize, @Nonnull final int minPoolSize, @Nonnull final int maxPoolSize) {
		System.setProperty("mdb.initialPoolSize", "" + initialPoolSize);
		System.setProperty("mdb.minPoolSize", "" + minPoolSize);
		System.setProperty("mdb.maxPoolSize", "" + maxPoolSize);
	}
	
}
