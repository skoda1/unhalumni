package command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Student;
import connectionprovider.ConnectionProvider;

public class ListStudentCommand {

	public ArrayList<Student> execute() {
		ArrayList<Student> ret = new ArrayList<Student>();
		try {
			Connection connection = ConnectionProvider.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM students");
			while (rs.next()) {
				Student s = new Student();
				s.setDepartment(rs.getString("department"));
				s.setName(rs.getString("name"));
				s.setId(rs.getInt("id"));
				s.setYear(rs.getString("year"));
				ret.add(s);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public static void main(String[] args) {
		ListStudentCommand demo = new ListStudentCommand();
		ArrayList<Student> ret = new ArrayList<Student>();
		ret  = demo.execute();
		for(Student i :ret){
			System.out.println(i.getName());
		}
		
		
	}

}
