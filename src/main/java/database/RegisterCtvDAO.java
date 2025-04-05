package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Registerctv;

public class RegisterCtvDAO implements DAOInterface<Registerctv> {

	@Override
	public ArrayList<Registerctv> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Registerctv selectById(Registerctv t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Registerctv t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertAll(ArrayList<Registerctv> arr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Registerctv t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<Registerctv> arr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Registerctv t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertCtv(String iD, String userID, Date todaysDate, int size, int size2, int size3, double soTienKiemDuoc) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "INSERT INTO registerctv (id, userID, dateregister, sospdadangban, sospdathuhoi, sospbantrongthang, sotienkiemduoc, dateend) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, iD);
			stm.setString(2, userID);
			stm.setDate(3, todaysDate);
			stm.setInt(4, size);
			stm.setInt(5, size2);
			stm.setInt(6, size3);
			stm.setString(7, String.valueOf(soTienKiemDuoc));
			stm.setDate(8, null);
			res = stm.executeUpdate();
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}

	public String selectIDCurrent() {
		// TODO Auto-generated method stub
		String ans = "";
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM registerctv ORDER BY id DESC LIMIT 1";
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				ans = rs.getString("id");
				break;
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ans;
	}

	public String selectSoTienKiemDuoc(String userID) {
		// TODO Auto-generated method stub
		String ans = "";
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM registerctv WHERE userID = ? AND dateend IS NULL";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, userID);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				ans = rs.getString("sotienkiemduoc");
				break;
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ans;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection con = JDBCUtil.getConnection();
		String ans = "";
		String sql = "SELECT * FROM registerctv WHERE userID = ? AND dateend IS NULL";
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setString(1, "1733249055174");
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			ans = rs.getString("sotienkiemduoc");
			System.out.println(ans + "số tiền kiếm được nè");
			break;
		}
		JDBCUtil.closeConnection(con);
	}

	public int capNhatNgayKetThuc(String userID, Date todays) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE registerctv SET dateend = ? WHERE userID = ? AND dateend IS NULL";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setDate(1, todays);
			stm.setString(2, userID);
			res = stm.executeUpdate();
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}

}
