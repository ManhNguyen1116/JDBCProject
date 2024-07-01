package com.example.JDBCExample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App {
	// connection is responsible to make connection with database
	Connection conn = null;
	// statement is used to execute sql queries in mysql server
	Statement stm = null;
	// good to use to insert lots of data
	PreparedStatement preStatement = null;

	App() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // using driver here to connect with database
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "5vm3gjAImLbVM0dgz07y");
			System.out.println("Connected ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	void fetchAllData() {
		String query = "select * from records";
		try {
			stm = conn.createStatement();
			// execute select query and return Resultset (Data in interface)
			ResultSet set = stm.executeQuery(query);
			while (set.next()) {
				System.out.println(set.getInt(1) + set.getString(2) + set.getString(3) + set.getString(4)
						+ set.getString(5) + set.getLong(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void insertData(String name, String purchase, String email, String password, String phone) {
		String query = "insert into records (name,purchase,email,password,phone) values (?,?,?,?,?)";
		try {
			preStatement = conn.prepareStatement(query);
			preStatement.setString(1, name);
			preStatement.setString(2, purchase);
			preStatement.setString(3, email);
			preStatement.setString(4, password);
			preStatement.setString(5, phone);
			preStatement.executeUpdate(); // making change into record table
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void fetchSingleData(String email) {
		String query = "select ? from records";
		try {
			preStatement = conn.prepareStatement("select * from records where email = ?");
			preStatement.setString(1, email);
			ResultSet set = preStatement.executeQuery();
			set.next();
			System.out.println(set.getInt(1) + set.getString(2) + set.getString(3) + set.getString(4) + set.getString(5)
					+ set.getString(6));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	Records fetchSingleData2(String email) {
		String query = "select ? from records";
		try {
			preStatement = conn.prepareStatement("select * from records where email = ?");
			preStatement.setString(1, email);
			ResultSet set = preStatement.executeQuery();
			set.next();
			return new Records(set.getInt(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5),
					set.getString(6));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	void updateRecord(String email) {
		// collect all of the data from email
		Records record = fetchSingleData2(email);
		try {
			record.setPhone("32145512335"); // new phone number updated into record class

			String query = "update records set phone = ? where email = ?";
			preStatement = conn.prepareStatement(query);
			preStatement.setString(1, record.getPhone()); // getting latest phone number from record
			preStatement.setString(2, record.getEmail()); // updated phone based on email id
			preStatement.executeUpdate(); // making change into record table
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		App app = new App();
		// app.insertData("ravi","50000","ravi@gmail.com","asd","2387234824");
		app.updateRecord("ravi@gmail.com");
		app.fetchAllData();
	}
}
