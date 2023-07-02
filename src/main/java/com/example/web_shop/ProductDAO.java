package com.example.web_shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addProduct(Product product) {
        String query = "INSERT INTO products (name, price, description) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, product.getName(), product.getPrice(), product.getDescription());
    }

    public void updateProduct(Product product) {
        String query = "UPDATE products SET name = ?, price = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(query, product.getName(), product.getPrice(), product.getDescription(), product.getId());
    }

    public void deleteProduct(Product product) {
        String query = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(query, product.getId());
    }

    public Product getProductById(int id) {
        String query = "SELECT * FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (resultSet, rowNum) -> {
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            String description = resultSet.getString("description");
            Product product = new Product(name, price, description);
            product.setId(resultSet.getInt("id"));
            return product;
        });
    }


    public List<Product> getAllProducts() {
        String query = "SELECT * FROM products";
        return jdbcTemplate.query(query, (resultSet, rowNum) -> {
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            String description = resultSet.getString("description");
            Product product = new Product(name, price, description);
            product.setId(resultSet.getInt("id"));
            return product;
        });
    }

}

