package command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Student;
import connectionprovider.ConnectionProvider;

public class DeleteSongCommand {

	public String execute(Student s) {

		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("DELETE FROM SONGS WHERE title=?, artist=? WHERE id=?");
			stmt.setString(1, s.getName());
			stmt.setString(2, s.getDepartment());
			stmt.setInt(3, s.getId());
			stmt.executeQuery();

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	}

}
