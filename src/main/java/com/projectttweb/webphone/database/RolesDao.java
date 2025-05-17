package com.projectttweb.webphone.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.projectttweb.webphone.model.Roles;

public class RolesDao implements DAOInterface<Roles> {

	@Override
	public ArrayList<Roles> selectAll() {
		ArrayList<Roles> answer = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM roles";
			PreparedStatement st = con.prepareStatement(sql);
			System.out.println("Executing: " + sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int roleID = rs.getInt("roleID");
				String roleName = rs.getString("roleName");
				Roles role = new Roles(roleID, roleName);
				answer.add(role);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in selectAll: " + e.getMessage());
			e.printStackTrace();
		}
		return answer;
	}

	@Override
	public Roles selectById(Roles t) {
		Roles role = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM roles WHERE roleID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, t.getRoleID());
			System.out.println("Executing: " + sql + " with roleID=" + t.getRoleID());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				int roleID = rs.getInt("roleID");
				String roleName = rs.getString("roleName");
				role = new Roles(roleID, roleName);
			} else {
				System.out.println("No role found for roleID=" + t.getRoleID());
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in selectById for roleID=" + t.getRoleID() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return role;
	}

	@Override
	public int insert(Roles t) {
		int answer = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "INSERT INTO roles (roleID, roleName) VALUES (?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, t.getRoleID());
			st.setString(2, t.getRoleName());
			answer = st.executeUpdate();
			System.out.println("Inserted role: " + t.getRoleID());
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in insert for roleID=" + t.getRoleID() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return answer;
	}

	@Override
	public int insertAll(ArrayList<Roles> arr) {
		int dem = 0;
		for (Roles roles : arr) {
			dem += this.insert(roles);
		}
		return dem;
	}

	@Override
	public int delete(Roles t) {
		int answer = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "DELETE FROM roles WHERE roleID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, t.getRoleID());
			answer = st.executeUpdate();
			System.out.println("Deleted role: " + t.getRoleID());
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in delete for roleID=" + t.getRoleID() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return answer;
	}

	@Override
	public int deleteAll(ArrayList<Roles> arr) {
		int dem = 0;
		for (Roles roles : arr) {
			dem += this.delete(roles);
		}
		return dem;
	}

	@Override
	public int update(Roles t) {
		int answer = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE roles SET roleName = ? WHERE roleID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getRoleName());
			st.setInt(2, t.getRoleID());
			answer = st.executeUpdate();
			System.out.println("Updated role: " + t.getRoleID());
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in update for roleID=" + t.getRoleID() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return answer;
	}

	public static void main(String[] args) {
		RolesDao roleDao = new RolesDao();
		ArrayList<Roles> list = roleDao.selectAll();
		for (Roles roles : list) {
			System.out.println(roles.toString());
		}
	}
}