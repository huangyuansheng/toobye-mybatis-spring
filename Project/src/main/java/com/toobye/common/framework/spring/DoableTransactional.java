/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/05/07.
 * 
 */
package com.toobye.common.framework.spring;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.toobye.common.lang.Doable;

/**
 * <pre> 嵌套Doable的事务.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/05/07  huangys  v1.0      Create
 * </pre>
 * 
 * @param <T> 传入参数类型
 */
@Component
@Scope("prototype")
public class DoableTransactional<T> implements Doable<T> {
	
	private Doable<T> doable;
	
	/**
	 * <pre> 构造器. </pre>
	 *
	 * @param runnable 可运行对象
	 */
	public DoableTransactional(final Doable<T> doable) {
		this.doable = doable;
	}

	@Override
	@Transactional
	public void run(final T t) {
		doable.run(t);
	}
	
}
