/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/04/19.
 * 
 */
package com.toobye.common.framework.mybatis;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.ibatis.session.SqlSession;

/**
 * <pre> DAO接口定义.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/04/19  huangys  v1.0      Create
 * </pre>
 * 
 * @param <E> 元素类型
 */
public interface DAO<E> extends Cloneable {
	
	/**
	 * <pre> 获得数据库连接.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/20  huangys  Create
	 * </pre>
	 * 
	 * @return 数据库连接
	 */
	@Nonnull
	public SqlSession getSqlSession();
	
	/**
	 * <pre> 获得sqlmap的命名空间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/04/06  huangys  Create
	 * </pre>
	 * 
	 * @return sqlmap的命名空间
	 */
	@Nonnull
	public String getSqlmapNS();
	
	/**
	 * <pre> 获得记录总条数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/04/06  huangys  Create
	 * </pre>
	 * 
	 * @return 总记录数
	 */
	@Nonnull
	public int getCountAll();
	
	/**
	 * <pre> 指定条件获得记录总条数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/04/06  huangys  Create
	 * </pre>
	 * 
	 * @param e 条件
	 * @return 记录数
	 */
	@Nonnull
	public int getCount(@Nullable final E e);
	
	/**
	 * <pre> 获得全部清单.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/04/06  huangys  Create
	 * </pre>
	 * 
	 * @return 所有记录
	 */
	@Nonnull
	public List<E> getListAll();
	
	/**
	 * <pre> 指定条件获得清单.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/04/06  huangys  Create
	 * </pre>
	 * 
	 * @param e 条件
	 * @return 记录清单
	 */
	@Nonnull
	public List<E> getList(@Nullable final E e);
	
	/**
	 * <pre> 指定条件获得单个对象.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/04/06  huangys  Create
	 * </pre>
	 * 
	 * @param e 条件
	 * @return 单条记录
	 */
	@Nullable
	public E getOne(@Nonnull final E e);
	
	/**
	 * <pre> 指定条件删除记录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/04/06  huangys  Create
	 * </pre>
	 * 
	 * @param e 条件
	 * @return 删除记录数
	 */
	@Nonnull
	public int deleteList(@Nonnull final E e);
	
	/**
	 * <pre> 指定条件更新单条记录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/04/06  huangys  Create
	 * </pre>
	 * 
	 * @param e 条件
	 * @return false无记录更新/true单条记录更新
	 */
	@Nonnull
	public boolean updateOne(@Nonnull final E e);

	/**
	 * <pre> 插入单条记录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/04/06  huangys  Create
	 * </pre>
	 * 
	 * @param e 记录
	 */
	public void insertOne(@Nonnull final E e);
	
	/**
	 * <pre> 指定条件删除单条记录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/04/17  huangys  Create
	 * </pre>
	 * 
	 * @param e 条件
	 * @return false无记录删除/true单条记录删除
	 */
	@Nonnull
	public boolean deleteOne(@Nonnull final E e);
	
	/**
	 * <pre> 插入多条记录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/04/06  huangys  Create
	 * </pre>
	 * 
	 * @param list 记录清单
	 * @return 插入记录数
	 */
	@Nonnull
	public int insertList(@Nonnull final Iterable<E> list);
	
	/**
	 * <pre> 利用数据库特性实现批量插入.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/20  huangys  Create
	 * </pre>
	 * 
	 * @param iterable 记录清单
	 * @return 插入记录数
	 */
	@Nonnull
	public int insertListBatchDBFeatures(@Nonnull final Iterable<E> iterable);
	
	/**
	 * <pre> 利用数据库特性实现批量插入.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/20  huangys  Create
	 * </pre>
	 * 
	 * @param iterable 记录清单
	 * @param rowsPerTime 每次批处理记录数
	 * @return 插入记录数
	 */
	@Nonnull
	public int insertListBatchDBFeatures(@Nonnull final Iterable<E> iterable, @Nonnull final int rowsPerTime);
	
	/**
	 * <pre> 使用批处理方法插入多条记录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/20  huangys  Create
	 * </pre>
	 * 
	 * @param iterable 迭代器
	 */
	void insertListBatch(@Nonnull Iterable<E> iterable);
	
	/**
	 * <pre> Merge单条记录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/04/06  huangys  Create
	 * </pre>
	 * 
	 * @param e 记录
	 */
	public void mergeOne(@Nonnull final E e);

	/**
	 * <pre> 替换一条记录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/08/01  huangys  Create
	 * </pre>
	 * 
	 * @param e 记录
	 */
	public void replaceOne(@Nonnull final E e);

}
