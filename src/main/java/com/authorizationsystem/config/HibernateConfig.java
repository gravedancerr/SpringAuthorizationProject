package com.authorizationsystem.config;

import lombok.RequiredArgsConstructor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.authorizationsystem"})
@EnableTransactionManagement
@PropertySource(value = "classpath:db.properties")
@RequiredArgsConstructor
public class HibernateConfig {
    private final Environment environment;

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment
                .getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment
                .getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment
                .getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment
                .getRequiredProperty("hibernate.connection.driver_class"));
        dataSource.setUrl(environment
                .getRequiredProperty("hibernate.connection.url"));
        dataSource.setUsername(environment
                .getRequiredProperty("hibernate.connection.username"));
        dataSource.setPassword(environment
                .getRequiredProperty("hibernate.connection.password"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setHibernateProperties(getProperties());
        sessionFactory.setPackagesToScan("com/authorizationsystem/model");
        return sessionFactory;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}
