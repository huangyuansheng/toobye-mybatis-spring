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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre> 嵌套Runnable的事务.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/05/07  huangys  v1.0      Create
 * </pre>
 * 
 */
@Component
@Scope("prototype")
public class RunnableTransactionalNew implements Runnable {
	
	private Runnable runnable;
	
	/**
	 * <pre> 构造器. </pre>
	 *
	 * @param runnable 可运行对象
	 */
	public RunnableTransactionalNew(final Runnable runnable) {
		this.runnable = runnable;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void run() {
		runnable.run();
	}
	
}
