/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/04/19.
 * 
 */
package com.toobye.common.framework.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.toobye.common.dbenums.DBEnum;
import com.toobye.common.dbenums.DBEnums;
import com.toobye.common.framework.spring.SpringContext;
import com.toobye.common.lang.Checks;
import com.toobye.common.reflect.Reflections;

/**
 * <pre> 数据库枚举类TypeHandler.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/04/19  huangys  v1.0      Create
 * </pre>
 * 
 * @param <DBType> 数据库类型
 * @param <E> 数据库枚举类型
 */
public final class DBEnumTypeHandler<DBType, E extends Enum<? extends DBEnum<DBType>>> extends BaseTypeHandler<E> {

	private DBEnumTypeHandler() { }
	
	private Class<E> type;
	private DBEnumTypeHandler(final Class<E> type) {
		Checks.nullThrow(type);
		this.type = type;
	}
	
	/**
	 * <pre> 初始化Mybatis数据库枚举类TypeHandler.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/19  huangys  Create
	 * </pre>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void init() {
		// 获取所有数据库枚举类
		Set<Class<? extends DBEnum>> dbEnumTypes = Reflections.getSubTypesOf(DBEnum.class.getPackage().getName(), DBEnum.class);
		// 获取Mybatis的TypeHandlerRegistry
		TypeHandlerRegistry registry = SpringContext.getBean(SqlSessionFactory.class).getConfiguration().getTypeHandlerRegistry();
		for (Class<? extends DBEnum> dbEnumType : dbEnumTypes) {
			registry.register(dbEnumType, new DBEnumTypeHandler(dbEnumType));
		}
	}
	
	@Override
	public void setNonNullParameter(final PreparedStatement ps, final int i, final E parameter, final JdbcType jdbcType) throws SQLException {
		if (jdbcType == null) {
	        ps.setString(i, ((DBType) DBEnums.getValue((Enum<? extends DBEnum<DBType>>) parameter)).toString());
	    } else {
	        ps.setObject(i, parameter.name(), jdbcType.TYPE_CODE);
	    }
	}

	@Override
	public E getNullableResult(final ResultSet rs, final String columnName) throws SQLException {
		String s = rs.getString(columnName);
		return s == null ? null : (E) DBEnums.valueOf(type, s);
	}

	@Override
	public E getNullableResult(final ResultSet rs, final int columnIndex) throws SQLException {
		String s = rs.getString(columnIndex);
		return s == null ? null : (E) DBEnums.valueOf(type, s);
	}

	@Override
	public E getNullableResult(final CallableStatement cs, final int columnIndex) throws SQLException {
		String s = cs.getString(columnIndex);
		return s == null ? null : (E) DBEnums.valueOf(type, s);
	}
	
}
