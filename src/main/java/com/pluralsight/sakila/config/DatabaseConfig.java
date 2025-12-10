package com.pluralsight.sakila.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    private BasicDataSource basicDataSource;

    @Bean
    public DataSource getDataSource() {
        return basicDataSource;
    }


    public DatabaseConfig(@Value("${datasource.url}") String url,
                          @Value("${datasource.username}") String username,
                          @Value("${datasource.password}") String password) {

        this.basicDataSource = new BasicDataSource();
        this.basicDataSource.setUrl(url);
        this.basicDataSource.setUsername(username);
        this.basicDataSource.setPassword(password);
    }
}
