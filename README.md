# toobye-mybatis-spring
搭建项目通用框架，整合MyBatis和Spring框架。<br>
Building framework using Spring and MyBatis.<br>

该项目依赖Common项目。
# 主要功能
## Properties
      com.toobye.common.framework.ProjectConfig
      用于加载并管理项目属性信息，对应的配置文件为project.properties
## Spring
      com.toobye.common.framework.spring.SpringConfig
      加载Spring配置文件applicationContext.xml
      利用PropertySourcesPlaceholderConfigurer，使System.setProperty、@PropertySource引入的属性值可以在配置文件中生效
      
      com.toobye.common.framework.spring.*Transactional或*TransactionalNew
      简化事务创建的编码工作
      New后缀是表明事务传递方式，可参见代码
      
      applicationContext.xml
      <!-- Spring组件扫描（含子包），此处用于加载DAO -->
      <context:component-scan base-package="com.toobye.mdb" />
## MyBatis
      mybatis-config.xml
      <!-- DTO路径 -->
      <typeAliases>
      	<package name="com.toobye.mdb.dto"/>
      </typeAliases>
      
      mybatis-spring.xml
      <!-- sqlSessionFactory定义部分 -->

      <!-- 引用属性mdb.prefix，用于定义元数据的表名前缀 -->
      <!-- 引用属性mdb.dbType，用于定义数据库类型（1:Mysql;2:Oracle;），因为MyBatis提供的内置变量_databaseId有时不可用 -->
      <property name="configurationProperties">
      <props>
      	<prop key="mdb.prefix">${mdb.prefix}</prop>
      	<prop key="mdb.dbType">${mdb.dbType}</prop>
      </props>
      </property>

      <!-- 定义Mapper扫描位置 -->
      <property name="mapperLocations" value="classpath:/mapper/*.xml" />
      
      com.toobye.common.framework.mybatis.DBEnumTypeHandler
      对于数据库引用的枚举类做转换，如枚举值YesNo.Yes，数据库中存放为Y
      数据库枚举类路径必须为：com.toobye.common.dbenums，如需变更请更改源码
      
      com.toobye.common.framework.mybatis.DBProperties
      对于需要启动多项服务的代码，可自定义不同连接池属性
      使用连接池前，必须调用set方法初始化连接池属性，否则报错
      
      com.toobye.common.framework.mybatis.DAO&DAOBase
      DAO定义和基础实现
## 样例
      project.properties
      正确配置数据库连接信息
      
      表结构
      Create table ${mdb.prefix}_FUNNY(A varchar(20), B varchar(20), C varchar(20), primary key(A));
      
      DTO
      com.toobye.mdb.dto.Funny
      
      DAO
      com.toobye.mdb.dao.FunnyDAO
      
      Mapper
      mapper/Funny.xml
      
      Test
      com.toobye.mdb.Test
      执行main方法，数据库应该仅插入两条数据，可以查看控制台输出或查看数据库中的表中记录
