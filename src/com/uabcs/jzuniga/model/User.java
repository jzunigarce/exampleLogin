package com.uabcs.jzuniga.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.uabcs.jzuniga.db.DBConnection;

public class User {
	
	public static final String TABLE = "user";
	private int id;
	private String email;
	private String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static ArrayList<User> findAll() {
		ArrayList<User> usuarios = new ArrayList<User>();
		DBConnection db = new DBConnection();
		Connection conn = db.getConnection();
		
		String query = "SELECT * FROM " + User.TABLE;
		try {
			PreparedStatement stm = conn.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setEmail(rs.getString("email"));
				u.setPassword(rs.getString("password"));
				usuarios.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return usuarios;
		}
	}
	
	public static User findByEmail(String email) {
		User user = null;
		
		try {
			DBConnection db = new DBConnection();
			Connection conn = db.getConnection();
			
			String query = "SELECT * FROM " + User.TABLE 
					+ " WHERE email = ?";
			PreparedStatement stm = conn.prepareStatement(query);
			stm.setString(1, email);
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}
