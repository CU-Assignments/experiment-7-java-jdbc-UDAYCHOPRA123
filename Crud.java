import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
    static final String URL = "jdbc:mysql://localhost:3306/your_database";
    static final String USER = "root";
    static final String PASSWORD = "your_password";
    
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.println("1. Add Product");
                System.out.println("2. View Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> addProduct(conn, scanner);
                    case 2 -> viewProducts(conn);
                    case 3 -> updateProduct(conn, scanner);
                    case 4 -> deleteProduct(conn, scanner);
                    case 5 -> System.exit(0);
                    default -> System.out.println("Invalid option!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addProduct(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Product Name: ");
        String name = scanner.next();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        String sql = "INSERT INTO Product (ProductName, Price, Quantity) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, quantity);
            pstmt.executeUpdate();
            System.out.println("Product added successfully.");
        }
    }

    private static void viewProducts(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Product";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("ProductID") + " | " +
                                   rs.getString("ProductName") + " | " +
                                   rs.getDouble("Price") + " | " +
                                   rs.getInt("Quantity"));
            }
        }
    }

    private static void updateProduct(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Product ID to update: ");
        int id = scanner.nextInt();
        System.out.print("Enter new Price: ");
        double price = scanner.nextDouble();
        
        String sql = "UPDATE Product SET Price = ? WHERE ProductID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, price);
            pstmt.setInt(2, id);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) System.out.println("Product updated successfully.");
            else System.out.println("Product not found.");
        }
    }

    private static void deleteProduct(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Product ID to delete: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM Product WHERE ProductID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) System.out.println("Product deleted.");
            else System.out.println("Product not found.");
        }
    }
}


CREATE DATABASE IF NOT EXISTS JDBC_Experiment;

USE JDBC_Experiment;

CREATE TABLE IF NOT EXISTS Employee (
    EmpID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100) NOT NULL,
    Salary DECIMAL(10,2) NOT NULL
);

INSERT INTO Employee (Name, Salary) VALUES 
('Alice Johnson', 50000.00),
('Bob Smith', 60000.00),
('Charlie Brown', 55000.00);

CREATE TABLE IF NOT EXISTS Product (
    ProductID INT PRIMARY KEY AUTO_INCREMENT,
    ProductName VARCHAR(100) NOT NULL,
    Price DECIMAL(10,2) NOT NULL,
    Quantity INT NOT NULL
);
