/**
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 *
 * Create At 2016/06/07.
 *
 */
package com.toobye.mdb.dao;

import com.toobye.common.framework.mybatis.DAOBase;
import com.toobye.common.lang.Checks;
import com.toobye.mdb.dto.Funny;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.ibatis.session.SqlSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 * <pre> FUNNY(null)数据接入类.
 *
 * Modification History:
 * Date        Author   Version   Action
 * 2016/06/07  huangys  v1.0      Create
 * </pre>
 *
 */
@Component
@Scope("prototype")
public final class FunnyDAO extends DAOBase<Funny> {

	/**
	 * <pre> 构造器. </pre>
	 *
	 * @param sqlSession 数据库连接
	 */
	public FunnyDAO(@Nonnull final SqlSession sqlSession) {
		super(sqlSession);
	}

	/**
	 * <pre> 指定唯一条件下，获得单个对象.
	 * </pre>
	 *
	 * @param a null
	 * @return 获得单个null(FUNNY)
	 */
	@Nullable
	public Funny getOne(@Nonnull final String a) {
		Checks.nullThrow(a);
		Funny funny = new Funny();
		funny.a = a;
		return getOne(funny);
	}

	/**
	 * <pre> 指定唯一条件下，删除单个对象.
	 * </pre>
	 *
	 * @param a null
	 * @return false无记录删除/true单条记录删除
	 */
	@Nonnull
	public boolean deleteOne(@Nonnull final String a) {
		Checks.nullThrow(a);
		Funny funny = new Funny();
		funny.a = a;
		return deleteOne(funny);
	}

}
