# springDemo
# spring service&amp;DAO demo, trying to understand the advantage of IoC

1. 需要添加依赖jar包：spring4.3.18的相关jar包；com.jolbox.bonecp.BoneCPDataSource；com.google.guava；org.slf4j；ojdbc14-10.2.0.4.0.jar（Oracle的jar驱动）
2. 在maven中添加依赖之后，点击import changes，会联网下载到本地的依赖文件夹中，但oracle的jar包驱动需要付费，无法联网自动下载
3. BoneCPDataSource是封装的数据库连接池
4. 利用spring的Ioc依赖注入，Service只需要自动装配DAO，与DAO内部信息没有耦合，DAO则自动装配数据库连接，实例化对象和装配对象属性的工作由容器来完成
5. 除了写service和DAO，还要写好作为配置文件的java类（@Configuration，@Bean），要写一个启动的类（读入总配置文件）

# add MyBatis Demo1

1. 需要依赖myBatis的jar包、myBatis和spring整合的jar包
2. 仍然利用java配置的方式来配置myBatis的SqlSessionFactory，在springConfig.java中增加新的@Bean，并利用原有的BoneCPDataSource作为myBatis的连接池，如此一来，myBatis的全局配置文件中不再需要配置连接池信息
3. sqlSession与connection本质上相同，都是非线程安全，每次使用需要获取一个新的对象，用完要在finally中关闭
4. 需要两个配置文件，全局配置文件mybatis.xml（连接池信息、事务管理器信息、映射信息）；XXXMapper.xml（sql映射文件，具体的sql语句写在此文件中）
5. 需要编写XXXMapper接口，没有实现类，但由于和XXXMapper.xml绑定（通过.xml中的namespace），myBatis会为这个mapper接口生成一个代理对象，即
   XXXMapper mapper = session.getMapper(XXXMapper.class);
