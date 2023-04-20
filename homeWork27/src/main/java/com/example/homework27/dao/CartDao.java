package com.example.homework27.dao;

import com.example.homework27.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class CartDao {

    private JdbcTemplate jdbcTemplate;

    public CartDao() {

    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM cart WHERE id=?", id);
    }

    public void create(Cart cart) {
        jdbcTemplate.update("INSERT INTO  cart VALUES ()");
    }

    public Cart getById(Long id) {
        return jdbcTemplate.query("SELECT * FROM cart WHERE id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Cart.class))
                .stream().findAny().orElse(null);
    }

    public void getProductsByCartId(Long cartId, ProductDao productDao) {
        productDao.getByCartId(cartId).forEach(System.out::println);
    }
}
