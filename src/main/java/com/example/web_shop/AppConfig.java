package com.example.web_shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        // Настройки для подключения к базе данных MySQL
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/internet_shop");
        dataSource.setUsername("Creker");
        dataSource.setPassword("296150");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public UserDAO userDao(JdbcTemplate jdbcTemplate) {
        return new UserDAO(jdbcTemplate);
    }

    @Bean
    public ProductDAO productDao(JdbcTemplate jdbcTemplate) {
        return new ProductDAO(jdbcTemplate);
    }

    @Bean
    public EmployeeDAO employeeDao(JdbcTemplate jdbcTemplate) {
        return new EmployeeDAO(jdbcTemplate);
    }

    @Bean
    public Main main(UserDAO userDao, ProductDAO productDao, EmployeeDAO employeeDao) {
        return new Main(userDao, productDao, employeeDao);
    }

    // Другие бины и настройки

}

