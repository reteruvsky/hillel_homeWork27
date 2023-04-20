package com.example.homework27.config;

import com.example.homework27.dao.CartDao;
import com.example.homework27.dao.ProductDao;
import jakarta.persistence.EntityManager;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.example.homework27")
public class Config {

    @Bean
    public DataSource getDatasourceCart() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/home_work_27")
                .username("root")
                .password("Sekret")
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplateCart() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(getDatasourceCart());
        return jdbcTemplate;
    }

    @Bean
    public ProductDao productDao() {
        ProductDao productDao = new ProductDao();
        productDao.setJdbcTemplate(jdbcTemplateCart());
        return productDao;
    }

    @Bean
    public CartDao cartDao() {
        CartDao cartDao = new CartDao();
        cartDao.setJdbcTemplate(jdbcTemplateCart());
        return cartDao;
    }
}
