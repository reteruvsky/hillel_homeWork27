package com.example.homework27.dao;

import com.example.homework27.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ProductDao() {
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM product WHERE id=?", id);
    }

    public void save(Product product) {
        jdbcTemplate.update("INSERT INTO product (name, price, cart_id) VALUES(?, ?, ?)",
                product.getName(),
                product.getPrice(),
                product.getCart().getId());
    }

    public Product getById(Long id) {
        return jdbcTemplate.query("SELECT * FROM product WHERE id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Product.class))
                .stream().findAny().orElse(null);
    }

    public List<Product> getAll() {
        return jdbcTemplate.query("SELECT * FROM product",
                new BeanPropertyRowMapper<>(Product.class));
    }

    public List<Product> getByCartId(Long id) {
        return jdbcTemplate.query("SELECT * FROM product WHERE cart_id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Product.class));
    }
}
