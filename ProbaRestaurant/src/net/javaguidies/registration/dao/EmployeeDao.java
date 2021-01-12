package net.javaguidies.registration.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import net.javaguidies.registration.model.Employee;

public class EmployeeDao {
	public int registerEmployee(Employee employee) throws ClassNotFoundException{
		String INSERT_USERS_SQL = "INSERT INTO employee"+"(first_name, last_name, username, password, address, contact) VALUES"+
	"(?, ?, ?, ?, ?, ?);";
		int result=0;
		
		Class.forName("com.mysql.jdbc.Driver");
		
		try(Connection connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","Lion2508!");
				
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){
			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getUsername());
			preparedStatement.setString(4, employee.getPassword());
			preparedStatement.setString(5, employee.getAddress());
			preparedStatement.setString(6, employee.getContact());
			 System.out.println(preparedStatement);
			 result=preparedStatement.executeUpdate();
			 
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
