package com.va.week6.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.va.week6.model.Student;

public class StudentDao {
	
	public int registerStudent(Student student) throws ClassNotFoundException {

		// create insert sql statement
		String INSERT_USERS_SQL = "INSERT INTO users"
				+ " (username, password, mobile_number, email) VALUES "
				+ "(?, ?, ?, ?);";

		int result = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");

		// connecting to the assignment2 database
		// again, need to figure out how to pass db password from secrets file
		try (Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/assignment2", "nets1038_w2026_nj", "***REMOVED***")) {
			
			// using prepared statement to insert values
			PreparedStatement ps = connection.prepareStatement(INSERT_USERS_SQL);
			ps.setString(1, student.getUsername());
			ps.setString(2, student.getPassword());
			ps.setString(3, student.getMobileNumber());
			ps.setString(4, student.getEmail());

			System.out.println(ps);
			result = ps.executeUpdate();

		} catch (SQLException e) {
			printSQLException(e);
		}

		return result;
	}

	public List<Student> selectStudents() throws ClassNotFoundException {

		// create select sql statement
		String SELECT_USERS_SQL = "SELECT username, password, mobile_number, email FROM users;";

		List<Student> students = new ArrayList<Student>();
		Class.forName("com.mysql.cj.jdbc.Driver");

		// connecting to the assignment2 database
		// we're using the user who has select and insert access
		try (Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/assignment2", "nets1038_w2026_nj", "***REMOVED***")) {

			// using prepared statement to select stored users
			PreparedStatement ps = connection.prepareStatement(SELECT_USERS_SQL);
			ResultSet rs = ps.executeQuery();

			// get rows and add to list
			// try to reduce complexity here 
			while (rs.next()) {
				Student student = new Student();

				student.setUsername(rs.getString("username"));
				student.setPassword(rs.getString("password"));
				student.setMobileNumber(rs.getString("mobile_number"));
				student.setEmail(rs.getString("email"));

				students.add(student);
			}

		} catch (SQLException e) {
			printSQLException(e);
		}

		return students;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {

				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());

				Throwable t = ex.getCause();

				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}