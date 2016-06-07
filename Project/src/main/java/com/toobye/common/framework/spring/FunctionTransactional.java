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

import com.toobye.common.lang.Function;

/**
 * <pre> 嵌套Function的事务.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/05/07  huangys  v1.0      Create
 * </pre>
 * 
 * @param <IN> 输入参数类型
 * @param <OUT> 返回值类型
 */
@Component
@Scope("prototype")
public class FunctionTransactional<IN, OUT> implements Function<IN, OUT> {
	
	private Function<IN, OUT> function;
	
	/**
	 * <pre> 构造器. </pre>
	 *
	 * @param callable 可调用对象
	 */
	public FunctionTransactional(final Function<IN, OUT> function) {
		this.function = function;
	}

	@Override
	@Transactional
	public OUT apply(IN in) {
		return function.apply(in);
	}
	
}
