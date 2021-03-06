package com.fsoft.thangdt3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fsoft.thangdt3.model.Role;
import com.fsoft.thangdt3.model.User;
import com.fsoft.thangdt3.utils.DBConfig;

public class UserDAO {
	public UserDAO() {
		try {
			Class.forName(DBConfig.DB_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public User login(String name, String pass) {
		Connection con = null;
		PreparedStatement pm = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.USER_NAME, DBConfig.PASS);
			pm = con.prepareStatement("select * from user where username = ? and pass = ?");
			pm.setString(1, name);
			pm.setString(2, pass);
			rs = pm.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPass(rs.getString("pass"));
				user.setRole(Role.valueOf(rs.getString("role")));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pm != null) {
				try {
					pm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;

	}
}
