package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 *  @Description 线程池配置
 *  @ClassName DruidConfig
 *  @author he.xuelong
 *  @Date 2021年07月14日 15:39
 *
 */
public class DruidConfig {

    @Value("${spring.datasource.password}")
    private String passwordAuthentication;
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("{spring.datasource.druid}")
    private String connectionProperties;

    /**
     * 将所有前缀为spring.datasource下的配置项都加载DataSource中
     * @param
     * @return javax.sql.DataSource
     */
    @Bean
    public DataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(passwordAuthentication);
        dataSource.setConnectionProperties(connectionProperties);
        return dataSource;
    }

}
