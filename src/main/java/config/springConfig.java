package config;

import dao.BusDataDAO;
import dao.BusDataDao_MyBatis;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import service.QueryService;


@Configuration    //表示该类是一个spring容器的配置文件
@PropertySource(value = {"classpath:jdbc.properties"},ignoreResourceNotFound = true)      //读取外部配置文件
public class springConfig {

    @Bean        //表示该容器中的一个bean，相当于xml中的<bean/>
    public BusDataDAO getBusDataDAO(){
        return new BusDataDAO();
    }

    @Bean
    public QueryService getQueryService(){
        return new QueryService();
    }

    @Value("${connection.driverClass}")
    private String driver;

    @Value("${connection.url}")
    private String url;

    @Value("${connection.username}")
    private String userName;

    @Value("${connection.password}")
    private String password;

    @Bean
    //以java配置的方式配置bean及其属性，其实和xml配置bean是一样的效果
    public BoneCPDataSource getBoneCPDataSource(){
        BoneCPDataSource dataSource =  new BoneCPDataSource();
        //这里需要导入oracle的jar驱动，ojdbc14-10.2.0.4.0.jar，由于需要付费，无法由maven自动下载，需要自行下载放在依赖文件夹中
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setIdleMaxAgeInMinutes(30);     //空闲连接的最大存活时间为30分钟
        dataSource.setMinConnectionsPerPartition(5);     //最小连接数
        dataSource.setMaxConnectionsPerPartition(30);     //最大连接数
        return dataSource;
    }

    @Bean
    //以java配置的方式来配置mybais,连接池采用上面已配置好的BoneCPDataSource
    //需要导入mybatis的jar包，以及mybatis和spring整合的jar包（SqlSessionFactoryBean在mybatis-spring.jar中才有）
    public SqlSessionFactoryBean getSqlSessionFactory(BoneCPDataSource ds){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis.xml"));
        sqlSessionFactoryBean.setDataSource(ds);
        return sqlSessionFactoryBean;
    }

    @Bean
    public BusDataDao_MyBatis getBusDataDao_MyBatis(){
        return new BusDataDao_MyBatis();
    }

}
