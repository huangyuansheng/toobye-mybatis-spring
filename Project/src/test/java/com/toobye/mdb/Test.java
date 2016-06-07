/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/06/07.
 * 
 */
package com.toobye.mdb;

import org.apache.ibatis.session.SqlSession;

import com.toobye.common.dbenums.YesNo;
import com.toobye.common.framework.mybatis.DBProperties;
import com.toobye.common.framework.spring.RunnableTransactional;
import com.toobye.common.framework.spring.SpringContext;
import com.toobye.common.time.DateFormat;
import com.toobye.mdb.dao.FunnyDAO;
import com.toobye.mdb.dto.Funny;

/**
 * <pre> 测试简单事务.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/06/07  huangys  v1.0      Create
 * </pre>
 * 
 */
public class Test {
	
	public static void main(String[] args) {
		// 初始化数据库连接池配置
		DBProperties.set(5, 5, 5);
		// 获取数据库连接
		SqlSession session = SpringContext.getSqlSession();
		// DAO
		final FunnyDAO funnyDAO = new FunnyDAO(session);
		// 以事务方式成功插入两条记录
		final Funny funny1 = new Funny();
		funny1.a = "1";
		funny1.b = YesNo.Yes;
		funny1.c = DateFormat.getCurrDetail();
		final Funny funny2 = new Funny();
		funny2.a = "2";
		funny2.b = YesNo.No;
		funny2.c = DateFormat.getCurrDetail();
		RunnableTransactional rt1 = SpringContext.getBean(RunnableTransactional.class, new Runnable() {
			public void run() {
				funnyDAO.insertOne(funny1);
				funnyDAO.insertOne(funny2);
			}
		});
		rt1.run();
		// 以事务方式插入两条记录，因主键冲突失败
		try {
			final Funny funny3 = new Funny();
			funny3.a = "3";
			funny3.b = YesNo.Yes;
			funny3.c = DateFormat.getCurrDetail();
			final Funny funny4 = new Funny();
			funny4.a = "3";
			funny4.b = YesNo.No;
			funny4.c = DateFormat.getCurrDetail();
			RunnableTransactional rt2 = SpringContext.getBean(RunnableTransactional.class, new Runnable() {
				public void run() {
					funnyDAO.insertOne(funny3);
					funnyDAO.insertOne(funny4);
				}
			});
			rt2.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回记录总数
		System.out.println("记录总数：" + funnyDAO.getCountAll());
		// 返回所有记录
		System.out.println("全部记录：" + funnyDAO.getListAll());
	}

}
