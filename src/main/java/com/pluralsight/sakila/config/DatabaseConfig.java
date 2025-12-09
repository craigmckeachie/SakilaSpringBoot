package com.pluralsight.sakila.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


import javax.sql.DataSource;

@Component
public class DatabaseConfig {
    private BasicDataSource basicDataSource;

    @Bean
    public DataSource getDataSource() {
        return basicDataSource;
    }


    public DatabaseConfig() {
        String url = "jdbc:mysql://localhost:3306/sakila";
        String username = "root";
        String password = "";

        this.basicDataSource = new BasicDataSource();
        this.basicDataSource.setUrl(url);
        this.basicDataSource.setUsername(username);
        this.basicDataSource.setPassword(password);
    }
}
