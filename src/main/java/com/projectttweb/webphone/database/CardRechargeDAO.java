package com.projectttweb.webphone.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.projectttweb.webphone.model.CardRechargeHistory;

public class CardRechargeDAO implements DAOInterface<CardRechargeHistory>{

	@Override
	public ArrayList<CardRechargeHistory> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CardRechargeHistory selectById(CardRechargeHistory t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(CardRechargeHistory t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertAll(ArrayList<CardRechargeHistory> arr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(CardRechargeHistory t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<CardRechargeHistory> arr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(CardRechargeHistory t) {
		// TODO Auto-generated method stub
		int num = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "INSERT INTO cardrechargehistory (idcart, userID, soseri, mathe, menhgia, status, sotienreal, nhamang, date) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, t.getIdCard());
			stm.setString(2, t.getUser().getUserID());
			stm.setString(3, t.getSoSeri());
			stm.setString(4, t.getMaThe());
			stm.setString(5, t.getMenhGia());
			stm.setString(6, t.getStatus());
			stm.setString(7, t.getSoTienReal());
			stm.setString(8, t.getNhaMang());
			stm.setDate(9, t.getNgayNap());
			num = stm.executeUpdate();
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return num;
	}

	public boolean kiemTraTheDaSuDungHayChua(String nhaMang, String seri, String maThe) {
		// TODO Auto-generated method stub
		boolean res = false;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM cardrechargehistory WHERE nhamang = ? AND soseri = ? AND mathe = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, nhaMang.trim());
			stm.setString(2, seri.trim());
			stm.setString(3, maThe.trim());
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				res = true;
				break;
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}

	public String getIDCardLast() {
		// TODO Auto-generated method stub
		String ans = "";
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM cardrechargehistory ORDER BY idcart DESC LIMIT 1";
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				ans = rs.getString("idcart");
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
