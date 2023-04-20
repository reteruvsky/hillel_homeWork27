package com.example.homework27;

import com.example.homework27.dao.CartDao;
import com.example.homework27.dao.ProductDao;
import com.example.homework27.entity.Cart;
import com.example.homework27.entity.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomeWork27Application {

    public static void main(String[] args) {
        SpringApplication.run(HomeWork27Application.class, args);
    }

    @Bean
    public CommandLineRunner getRunner(ApplicationContext context) {
        return args -> {
            ProductDao productDao = (ProductDao) context.getBean("productDao");
            CartDao cartDao = (CartDao) context.getBean("cartDao");

            Cart cart = cartDao.getById(1L);
            System.out.println(cart);
            Product cola = new Product("Cola", 3.0, cart);
            Product jin = new Product("Beffeeter", 50.0, cart);
            Product beer = new Product("Lvivske", 2.0, cart);

            cartDao.delete(3L);
            cartDao.getProductsByCartId(1L, productDao);

            productDao.getAll().forEach(System.out::println);

        };
    }
}
