package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.SoTaiKhoan;

public class SoTaiKhoanDAO implements DAOInterface<SoTaiKhoan> {

	@Override
	public ArrayList<SoTaiKhoan> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SoTaiKhoan selectById(SoTaiKhoan t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(SoTaiKhoan t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertAll(ArrayList<SoTaiKhoan> arr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(SoTaiKhoan t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<SoTaiKhoan> arr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(SoTaiKhoan t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String selectIDLast() {
		// TODO Auto-generated method stub
		String mess = "";
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM numbank ORDER BY id DESC LIMIT 1";
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				mess = rs.getString("id");
				break;
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mess;
	}

	public int insertSoTaiKhoan(SoTaiKhoan stk3) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "INSERT INTO numbank(id, sotaikhoan, soDu, maPin, nganHang) VALUES (?,?,?,?,?)";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, stk3.getiD());
			stm.setString(2, stk3.getStk());
			stm.setString(3, String.valueOf(stk3.getSoDu()));
			stm.setString(4, stk3.getMaPin());
			stm.setString(5, stk3.getNganHang());
			res = stm.executeUpdate();
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}

	public boolean kiemTraSoTaiKhoan(String stk2) {
		// TODO Auto-generated method stub
		boolean res = false;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM numbank WHERE sotaikhoan = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, stk2.trim());
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
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

	public SoTaiKhoan selectSoTaiKhoan(String stk) {
		// TODO Auto-generated method stub
		SoTaiKhoan stk3 = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM numbank WHERE sotaikhoan = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, stk.trim());
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				String iD = rs.getString("id");
				String stk2 = rs.getString("sotaikhoan");
				String soDu = rs.getString("soDu");
				String maPin = rs.getString("maPin");
				String bank = rs.getString("nganHang");
				stk3 = new SoTaiKhoan(iD, stk2, soDu, maPin, bank);
				break;
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return stk3;

	}

	public int capNhatSoDu(String valueOf, String stk) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE numbank SET soDu = ? WHERE sotaikhoan = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, valueOf.trim());
			stm.setString(2, stk.trim());
			res = stm.executeUpdate();
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}

}
