package com.projectttweb.webphone.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.projectttweb.webphone.model.NumBank;

public class NumBankDAO implements DAOInterface<NumBank>{

	@Override
	public ArrayList<NumBank> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NumBank selectById(NumBank t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(NumBank t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertAll(ArrayList<NumBank> arr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(NumBank t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<NumBank> arr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(NumBank t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String selectSoTienTrongAtm(String stk) {
		// TODO Auto-generated method stub
		String ans = "";
		int num = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM numbank WHERE sotaikhoan = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, stk.trim());
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				ans = rs.getString("soDu");
				System.out.println(ans +" đây là số dư nè");
				break;
			}
			num = (int) Double.parseDouble(ans);
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return String.valueOf(num);
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String ans = "";
		String stk = "2345678987";
		Connection con = JDBCUtil.getConnection();
		String sql = "SELECT * FROM numbank WHERE sotaikhoan = ?";
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setString(1, stk.trim());
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			ans = rs.getString("soDu");
			System.out.println(ans +" đây là số dư nè");
			break;
		}
		JDBCUtil.closeConnection(con);
		int num = (int) Double.parseDouble(ans);
		System.out.println(num);
	}

	public int deleteStk(String stk) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "DELETE FROM numbank WHERE sotaikhoan = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, stk);
			res = stm.executeUpdate();
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}

	public String selectSTK(String stk) {
		// TODO Auto-generated method stub
		String ans = "";
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM numbank WHERE id = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, stk.trim());
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				ans = rs.getString("sotaikhoan");
				break;
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ans;
	}

}
