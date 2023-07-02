package com.example.web_shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(User user) {
        String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, user.getName(), user.getEmail(), user.getPassword());
    }

    public void updateUser(User user) {
        String query = "UPDATE users SET name = ?, email = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(query, user.getName(), user.getEmail(), user.getPassword(), user.getId());
    }

    public void deleteUser(User user) {
        String query = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(query, user.getId());
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        RowMapper<User> rowMapper = (resultSet, rowNum) -> {
            int userId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            return new User(name, email, password);
        };
        return jdbcTemplate.query(sql, new Object[]{id}, rowMapper).stream().findFirst().orElse(null);

    }

    public List<User> getAllUsers() {
        String query = "SELECT * FROM users";
        return jdbcTemplate.query(query, (resultSet, rowNum) -> {
            User user = new User(resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("password"));
            user.setId(resultSet.getInt("id"));
            return user;
        });
    }
}


