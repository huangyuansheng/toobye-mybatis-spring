/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/04/19.
 * 
 */
package com.toobye.common.framework.mybatis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.ibatis.session.SqlSession;

import com.toobye.common.base.Exceptions;
import com.toobye.common.lang.Checks;
import com.toobye.common.string.StringUtils;

/**
 * <pre> DAO基础实现类.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/04/19  huangys  v1.0      Create
 * </pre>
 * 
 * @param <E> 元素类型
 */
public abstract class DAOBase<E> implements DAO<E> {

	private SqlSession sqlSession;

	/**
	 * <pre> 构造器. </pre>
	 *
	 * @param sqlSession 数据库连接
	 */
	public DAOBase(@Nonnull final SqlSession sqlSession) {
		Checks.nullThrow(sqlSession);
		this.sqlSession = sqlSession;
	}
	
	private String sqlmapNS;
	@Override
	public String getSqlmapNS() {
		if (sqlmapNS == null) {
			sqlmapNS = StringUtils.removeEnd(getClass().getSimpleName(), "DAO");
		}
		return sqlmapNS;
	}
	
	@Override
	public SqlSession getSqlSession() {
		return sqlSession;
	}
	
	@Override
	public int getCountAll() {
		return getCount(null);
	}
	
	@Override
	public int getCount(@Nullable final E e) {
		return getSqlSession().selectOne(getSqlmapNS() + ".getCount", e);
	}
	
	@Override
	public List<E> getListAll() {
		return getList(null);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<E> getList(@Nullable final E e) {
		return (List<E>) getSqlSession().selectList(getSqlmapNS() + ".getList", e);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public E getOne(@Nonnull final E e) {
		Checks.nullThrow(e);
		return (E) getSqlSession().selectOne(getSqlmapNS() + ".getList", e);
	}
	
	@Override
	public int deleteList(@Nonnull final E e) {
		Checks.nullThrow(e);
		return getSqlSession().delete(getSqlmapNS() + ".deleteList", e);
	}
	
	@Override
	public boolean updateOne(@Nonnull final E e) {
		Checks.nullThrow(e);
		int rows = getSqlSession().update(getSqlmapNS() + ".updateOne", e);
		if (rows == 0) {
			return false;
		} else if (rows == 1) {
			return true;
		} else {
			throw new RuntimeException("Update more than one rows.");
		}
	}
	
	@Override
	public void insertOne(@Nonnull final E e) {
		Checks.nullThrow(e);
		getSqlSession().insert(getSqlmapNS() + ".insertOne", e);
	}
	
	@Override
	public boolean deleteOne(@Nonnull final E e) {
		Checks.nullThrow(e);
		int rows = getSqlSession().delete(getSqlmapNS() + ".deleteOne", e);
		if (rows == 0) {
			return false;
		} else if (rows == 1) {
			return true;
		} else {
			throw new RuntimeException("Delete more than one rows.");
		}
	}
	
	@Override
	public int insertList(@Nonnull final Iterable<E> iterable) {
		Checks.nullThrow(iterable);
		int ret = 0;
		Iterator<E> it = iterable.iterator();
		while (it.hasNext()) {
			insertOne(it.next());
			ret++;
		}
		return ret;
	}
	
	private static final int DEFAULT_ROWS_PER_TIME = 100;
	@Override
	public int insertListBatchDBFeatures(@Nonnull final Iterable<E> iterable) {
		return insertListBatchDBFeatures(iterable, DEFAULT_ROWS_PER_TIME);
	}
	
	@Override
	public int insertListBatchDBFeatures(@Nonnull final Iterable<E> iterable, @Nonnull final int rowsPerTime) {
		Checks.nullThrow(iterable);
		int ret = 0;
		Iterator<E> it = iterable.iterator();
		List<E> tmp = new ArrayList<>();
		while (it.hasNext()) {
			tmp.add(it.next());
			if (tmp.size() == rowsPerTime) {
				getSqlSession().insert(getSqlmapNS() + ".insertListBatchDBFeatures", tmp);
				tmp.clear();
			}
			ret++;
		}
		if (!tmp.isEmpty()) {
			getSqlSession().insert(getSqlmapNS() + ".insertListBatchDBFeatures", tmp);
		}
		return ret;
	}
	
	@Override
	public void insertListBatch(@Nonnull final Iterable<E> iterable) {
		Exceptions.throwNoImplementation();
	}
	
	@Override
	public void mergeOne(@Nonnull final E e) {
		Checks.nullThrow(e);
		if (!updateOne(e)) {
			insertOne(e);
		}
	}
	
	@Override
	public void replaceOne(@Nonnull final E e) {
		Exceptions.throwNoImplementation();
	}
	
}
