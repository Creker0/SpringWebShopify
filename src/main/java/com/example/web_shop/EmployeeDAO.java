package com.example.web_shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addEmployee(Employee employee) {
        String query = "INSERT INTO employees (name, position, salary) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, employee.getName(), employee.getPosition(), employee.getSalary());
    }

    public void updateEmployee(Employee employee) {
        String query = "UPDATE employees SET name = ?, position = ?, salary = ? WHERE id = ?";
        jdbcTemplate.update(query, employee.getName(), employee.getPosition(), employee.getSalary(), employee.getId());
    }

    public void deleteEmployee(Employee employee) {
        String query = "DELETE FROM employees WHERE id = ?";
        jdbcTemplate.update(query, employee.getId());
    }

    public Employee getEmployeeById(int id) {
        String query = "SELECT * FROM employees WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (resultSet, rowNum) -> {
            String name = resultSet.getString("name");
            String position = resultSet.getString("position");
            double salary = resultSet.getDouble("salary");
            Employee employee = new Employee(name, position, salary);
            employee.setId(resultSet.getInt("id"));
            return employee;
        });
    }

    public List<Employee> getAllEmployees() {
        String query = "SELECT * FROM employees";
        return jdbcTemplate.query(query, (resultSet, rowNum) -> {
            String name = resultSet.getString("name");
            String position = resultSet.getString("position");
            double salary = resultSet.getDouble("salary");
            Employee employee = new Employee(name, position, salary);
            employee.setId(resultSet.getInt("id"));
            return employee;
        });
    }


}

