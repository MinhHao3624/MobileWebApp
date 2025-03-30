package com.projectttweb.webphone.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.projectttweb.webphone.model.AtmRechargeHistory;

public class AtmRechargeHistoryDAO implements DAOInterface<AtmRechargeHistory>{

	@Override
	public ArrayList<AtmRechargeHistory> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtmRechargeHistory selectById(AtmRechargeHistory t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(AtmRechargeHistory t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertAll(ArrayList<AtmRechargeHistory> arr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(AtmRechargeHistory t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<AtmRechargeHistory> arr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(AtmRechargeHistory t) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "INSERT INTO atmrechargehistory (id, userID, amountbeforedesposit, amountdesposit, amountafterdesposit, date, status, bank, numaccount, pincode) VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, t.getiD());
			stm.setString(2, t.getUserID().getUserID());
			stm.setString(3, t.getAmountBeforeDesposit());
			stm.setString(4, t.getAmountDesposit());
			stm.setString(5, t.getAmountAfterDesposit());
			stm.setDate(6, t.getDate());
			stm.setString(7, t.getStatus());
			stm.setString(8, t.getBank());
			stm.setString(9, t.getNumAccount());
			stm.setString(10, t.getPinCode());
			res = stm.executeUpdate();
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}

	public String selectIDCur() {
		// TODO Auto-generated method stub
		String res = "";
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM atmrechargehistory ORDER BY id DESC LIMIT 1";
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				res = rs.getString("id");
				break;
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}

}
