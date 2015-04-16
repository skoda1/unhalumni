package command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Student;
import connectionprovider.ConnectionProvider;

public class CreateStudentCommand {

	public String execute(Student s) {

		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("INSERT INTO students(name, department, year) VALUES(?, ?, ?) Returning id");
			stmt.setString(1, s.getName());
			stmt.setString(2, s.getDepartment());
			stmt.setString(3, s.getYear());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getString("id");
			}

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	}
	
	public static void main(String[] args) {
		CreateStudentCommand demo = new CreateStudentCommand();
		Student a = new Student();
		a.setName("Sahul");
		a.setDepartment("CS");
		a.setYear("2014");
		
		System.out.println(demo.execute(a));
	}

}
