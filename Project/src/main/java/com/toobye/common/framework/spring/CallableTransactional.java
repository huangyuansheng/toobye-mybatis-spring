/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/05/07.
 * 
 */
package com.toobye.common.framework.spring;

import java.util.concurrent.Callable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre> 嵌套Callable的事务.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/05/07  huangys  v1.0      Create
 * </pre>
 * 
 * @param <V> 返回值类型
 */
@Component
@Scope("prototype")
public class CallableTransactional<V> implements Callable<V> {
	
	private Callable<V> callable;
	
	/**
	 * <pre> 构造器. </pre>
	 *
	 * @param callable 可调用对象
	 */
	public CallableTransactional(final Callable<V> callable) {
		this.callable = callable;
	}

	@Override
	@Transactional
	public V call() {
		try {
			return callable.call();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
