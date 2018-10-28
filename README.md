# springDemo
spring service&amp;DAO demo, trying to understand the advantage of IoC

1. 需要添加依赖jar包：spring4.3.18的相关jar包；com.jolbox.bonecp.BoneCPDataSource；com.google.guava；org.slf4j；ojdbc14-10.2.0.4.0.jar（Oracle的jar驱动）
2. 在maven中添加依赖之后，点击import changes，会联网下载到本地的依赖文件夹中，但oracle的jar包驱动需要付费，无法联网自动下载
3. BoneCPDataSource是封装的数据库连接池
4. 利用spring的Ioc依赖注入，Service只需要自动装配DAO，与DAO内部信息没有耦合，DAO则自动装配数据库连接，实例化对象和装配对象属性的工作由容器来完成
5. 除了写service和DAO，还要写好作为配置文件的java类（@Configuration，@Bean），要写一个启动的类（读入总配置文件）
