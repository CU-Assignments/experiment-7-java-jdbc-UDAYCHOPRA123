import java.sql.*;

public class EmployeeFetcher {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/your_database";
        String user = "root";
        String password = "your_password";
        
        String query = "SELECT EmpID, Name, Salary FROM Employee";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("EmpID\tName\tSalary");
            while (rs.next()) {
                System.out.println(rs.getInt("EmpID") + "\t" +
                                   rs.getString("Name") + "\t" +
                                   rs.getDouble("Salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
