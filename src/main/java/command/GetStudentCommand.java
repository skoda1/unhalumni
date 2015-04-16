package command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Student;
import connectionprovider.ConnectionProvider;

public class GetStudentCommand {

	public Student execute(int id) {
		Student s = new Student();
		try {
			Connection connection = ConnectionProvider.getConnection();
			// Statement stmt = connection.createStatement();
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM students WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				s.setDepartment(rs.getString("department"));
				s.setName(rs.getString("name"));
				s.setId(rs.getInt("id"));
				s.setId(rs.getInt("year"));
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

}
