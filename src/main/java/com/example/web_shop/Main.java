package com.example.web_shop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;


public class Main {

    // Зависимости
    private final UserDAO userDao;
    private final ProductDAO productDao;
    private final EmployeeDAO employeeDao;


    // Конструктор
    public Main(UserDAO userDao, ProductDAO productDao, EmployeeDAO employeeDao) {
        this.userDao = userDao;
        this.productDao = productDao;
        this.employeeDao = employeeDao;
    }


    public static void main(String[] args) {
        // Создаем экземпляр класса Main
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Main main = context.getBean(Main.class);

        // Вызываем методы для проверки функциональности
        main.createAndRetrieveUsers();
        main.createAndRetrieveProducts();
        main.createAndRetrieveEmployees();
    }

    // Методы для проверки функциональности

    public void createAndRetrieveUsers() {
        // Создание пользователей
        User user1 = new User("John", "john@example.com", "1234567");
        userDao.addUser(user1);

        User user2 = new User("Jane", "jane@example.com", "pass1234");
        userDao.addUser(user2);

        // Получение всех пользователей
        List<User> users = userDao.getAllUsers();
        System.out.println("All Users:");
        for (User user : users) {
            System.out.println(user);
        }

        // Получение пользователя по ID
        int userId = 1;
        User retrievedUser = userDao.getUserById(userId);
        System.out.println("Retrieved User:");
        System.out.println(retrievedUser);
    }

    public void createAndRetrieveProducts() {
        // Создание продуктов
        Product product1 = new Product("Product 1", 9.99, "Product 1 description");
        productDao.addProduct(product1);

        Product product2 = new Product("Product 2", 19.99, "Product 2 description");
        productDao.addProduct(product2);

        // Получение всех продуктов
        List<Product> products = productDao.getAllProducts();
        System.out.println("All Products:");
        for (Product product : products) {
            System.out.println(product);
        }

        // Получение продукта по ID
        int productId = 1;
        Product retrievedProduct = productDao.getProductById(productId);
        System.out.println("Retrieved Product:");
        System.out.println(retrievedProduct);
    }

    public void createAndRetrieveEmployees() {
        // Создание работников
        Employee employee1 = new Employee("John", "Manager", 2000);
        employeeDao.addEmployee(employee1);

        Employee employee2 = new Employee("Jane", "Clerk", 1500);
        employeeDao.addEmployee(employee2);

        // Получение всех работников
        List<Employee> employees = employeeDao.getAllEmployees();
        System.out.println("All Employees:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        // Получение работника по ID
        int employeeId = 1;
        Employee retrievedEmployee = employeeDao.getEmployeeById(employeeId);
        System.out.println("Retrieved Employee:");
        System.out.println(retrievedEmployee);
    }
}

