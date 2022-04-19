package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import ru.yandex.clickhouse.BalancedClickhouseDataSource;
import ru.yandex.clickhouse.settings.ClickHouseProperties;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

/**
 *
 * @Author: Ranphy
 * @Date: 2021/3/5 13:46
 * @desc 副数据源
 */
@Configuration
@MapperScan(basePackages = ClusterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "clusterSqlSessionFactory")
public class ClusterDataSourceConfig {
 
    static final String PACKAGE = "com.example.demo.dao.click";
    static final String MAPPER_LOCATION = "classpath:/resources/mapper/click/*";
 
    @Value("${spring.cluster.datasource.url}")
    private String url;
 
    @Value("${spring.cluster.datasource.username}")
    private String username;
 
    @Value("${spring.cluster.datasource.password}")
    private String password;
 
    @Value("${spring.cluster.datasource.driver-class-name}")
    private String driverClassName;
 
    @Value("${spring.cluster.datasource.name}")
    private String name;
 
    @Value("${spring.datasource.druid.initialSize}")
    private int initialSize;
 
    @Value("${spring.datasource.druid.minIdle}")
    private int minIdle;
 
    @Value("${spring.datasource.druid.maxActive}")
    private int maxActive;
 
    @Value("${spring.datasource.druid.maxWait}")
    private int maxWait;
 
    @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
 
    @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
 
    @Value("${spring.datasource.druid.validationQuery}")
    private String validationQuery;
 
    @Value("${spring.datasource.druid.testWhileIdle}")
    private boolean testWhileIdle;
 
    @Value("${spring.datasource.druid.testOnBorrow}")
    private boolean testOnBorrow;
 
    @Value("${spring.datasource.druid.testOnReturn}")
    private boolean testOnReturn;
 
    @Value("${spring.datasource.druid.poolPreparedStatements}")
    private boolean poolPreparedStatements;
 
    @Value("${spring.datasource.druid.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
 
    @Value("{spring.datasource.druid.connectionProperties}")
    private String connectionProperties;
 
 
    @Bean(name = "clusterDataSource")
    public DataSource masterDataSource() {
        ClickHouseProperties properties = new ClickHouseProperties();
        properties.setAsync(true);
        properties.setSessionCheck(true);
        properties.setUser(username);
        properties.setPassword(password);
        properties.setDatabase(name);

        BalancedClickhouseDataSource dataSource = new BalancedClickhouseDataSource(url, properties);
        dataSource.scheduleActualization(15, TimeUnit.SECONDS);
        return dataSource;
    }
 
    @Bean(name = "clusterTransactionManager")
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }
 
    @Bean(name = "clusterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(ClusterDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}