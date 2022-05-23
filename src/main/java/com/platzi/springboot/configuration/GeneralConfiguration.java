package com.platzi.springboot.configuration;

import com.platzi.springboot.bean.MyBean;
import com.platzi.springboot.bean.implementation.MyBeanTwoImpl;
import com.platzi.springboot.bean.implementation.MyBeanWithPropertiesImpl;
import com.platzi.springboot.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;


@Configuration
@PropertySource("classpath:connection.properties")
@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfiguration {

    @Value("${value.name}")
    private String name;

    @Value("${value.apellido}")
    private String apellido;

    @Value("${value.random}")
    private String randomValue;

    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${driver}")
    private String driver;
    @Value("${username}")
    private String userName;
    @Value("${password}")
    private String password;


    @Bean
    public MyBean myBean(){
        return new MyBeanTwoImpl();
    }

    @Bean
    public MyBeanWithPropertiesImpl fuction(){
        return new MyBeanWithPropertiesImpl(name, apellido);
    }


    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driver);
        dataSourceBuilder.url(jdbcUrl);
        dataSourceBuilder.username(userName);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }


}
