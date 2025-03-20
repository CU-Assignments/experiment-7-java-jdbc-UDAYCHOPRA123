public class Student {
    private int studentID;
    private String name;
    private String department;
    private double marks;

    public Student(int studentID, String name, String department, double marks) {
        this.studentID = studentID;
        this.name = name;
        this.department = department;
        this.marks = marks;
    }

    // Getters and Setters
}

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password";

    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO Student (StudentID, Name, Department, Marks) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, student.getStudentID());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getDepartment());
            pstmt.setDouble(4, student.getMarks());
            pstmt.executeUpdate();
        }
    }

    public List<Student> getStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Student";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(rs.getInt("StudentID"), rs.getString("Name"), rs.getString("Department"), rs.getDouble("Marks")));
            }
        }
        return students;
    }
}



import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StudentApp {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter ID, Name, Department, Marks: ");
                    int id = scanner.nextInt();
                    String name = scanner.next();
                    String dept = scanner.next();
                    double marks = scanner.nextDouble();
                    try {
                        dao.addStudent(new Student(id, name, dept, marks));
                        System.out.println("Student added!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                case 2 -> {
                    try {
                        List<Student> students = dao.getStudents();
                        for (Student s : students) {
                            System.out.println(s.getStudentID() + " | " + s.getName() + " | " + s.getDepartment() + " | " + s.getMarks());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                case 3 -> System.exit(0);
            }
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

INSERT INTO Product (ProductName, Price, Quantity) VALUES 
('Laptop', 75000.00, 10),
('Smartphone', 40000.00, 20),
('Headphones', 3000.00, 50);

CREATE TABLE IF NOT EXISTS Student (
    StudentID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100) NOT NULL,
    Department VARCHAR(50) NOT NULL,
    Marks DECIMAL(5,2) NOT NULL
);

INSERT INTO Student (Name, Department, Marks) VALUES 
('David Miller', 'Computer Science', 89.5),
('Emma Watson', 'Electronics', 92.0),
('John Doe', 'Mechanical', 85.3);

SHOW TABLES;

SELECT * FROM Employee;
SELECT * FROM Product;
SELECT * FROM Student;
