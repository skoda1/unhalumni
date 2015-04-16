package command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Student;
import connectionprovider.ConnectionProvider;

public class SearchCommand {

	public ArrayList<Student> executeName(String name) {
		ArrayList<Student> ret = new ArrayList<Student>();
		
		try {
			Connection connection = ConnectionProvider.getConnection();
			// Statement stmt = connection.createStatement();
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM students WHERE name = ?");
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
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

	public ArrayList<Student> executeDepartment(String department) {
		ArrayList<Student> ret = new ArrayList<Student>();
		
		try {
			Connection connection = ConnectionProvider.getConnection();
			// Statement stmt = connection.createStatement();
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM students WHERE department = ?");
			stmt.setString(1, department);
			ResultSet rs = stmt.executeQuery();
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
	
	public ArrayList<Student> executeYear(String year) {
		ArrayList<Student> ret = new ArrayList<Student>();
		
		try {
			Connection connection = ConnectionProvider.getConnection();
			// Statement stmt = connection.createStatement();
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM students WHERE year = ?");
			stmt.setString(1, year);
			ResultSet rs = stmt.executeQuery();
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
		SearchCommand demo = new SearchCommand();
		ArrayList<Student> ret = new ArrayList<Student>();
		ret  = demo.executeName("Sahul");
		for(Student i :ret){
			System.out.println(i.getName());
		}
		
		ArrayList<Student> ret1 = new ArrayList<Student>();
		ret1  = demo.executeDepartment("CS");
		for(Student i :ret1){
			System.out.println(i.getName());
		}
		
		
	}
}
