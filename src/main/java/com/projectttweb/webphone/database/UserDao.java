package com.projectttweb.webphone.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.projectttweb.webphone.model.Roles;
import com.projectttweb.webphone.model.SoTaiKhoan;
import com.projectttweb.webphone.model.User;

public class UserDao implements DAOInterface<User> {

	@Override
	public ArrayList<User> selectAll() {
		ArrayList<User> answer = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM user";
			PreparedStatement st = con.prepareStatement(sql);
			System.out.println("Executing: " + sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String userID = rs.getString("userID");
				String userName = rs.getString("userName");
				String passWord = rs.getString("passWord");
				String fullName = rs.getString("fullName");
				String email = rs.getString("email");
				String phone = rs.getString("phoneNumber");
				int roleID = rs.getInt("roleID");
				Roles role = new Roles();
				role.setRoleID(roleID);
				Roles roles = new RolesDao().selectById(role);
				if (roles == null) {
					System.out.println("No role found for roleID=" + roleID + " for userID=" + userID);
					roles = new Roles(roleID, "Unknown");
				}
				Date dateOfBirth = rs.getDate("dateofbirth");
				String sex = rs.getString("sex");
				String address = rs.getString("address");
				Date createAt = rs.getDate("createAt");
				String authenticationCode = rs.getString("authenticationCode");
				Date confirmationTime = rs.getDate("confirmationTime");
				int status = rs.getInt("status");
				String image = rs.getString("image");
				String isKey = rs.getString("isKey");
				String soDu = rs.getString("soDu");
				String typeUser = rs.getString("typeuser");
				int age = rs.getInt("age");
				String cccd = rs.getString("cccd");
				String degree = rs.getString("degree");
				String numBank = rs.getString("numbank");
				User user = new User(userID, userName, passWord, fullName, email, phone, roles, dateOfBirth, sex,
						address, createAt, authenticationCode, confirmationTime, status, image, isKey, soDu, typeUser,
						age, cccd, degree, numBank != null ? new SoTaiKhoan(numBank) : null);
				answer.add(user);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in selectAll: " + e.getMessage());
			e.printStackTrace();
		}
		return answer;
	}

	@Override
	public User selectById(User t) {
		User answer = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM user WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getUserID());
			System.out.println("Executing: " + sql + " with userID=" + t.getUserID());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				String userID = rs.getString("userID");
				String userName = rs.getString("userName");
				String passWord = rs.getString("passWord");
				String fullName = rs.getString("fullName");
				String email = rs.getString("email");
				String phone = rs.getString("phoneNumber");
				int roleID = rs.getInt("roleID");
				Roles role = new Roles();
				role.setRoleID(roleID);
				Roles roles = new RolesDao().selectById(role);
				if (roles == null) {
					System.out.println("No role found for roleID=" + roleID + " for userID=" + userID);
					roles = new Roles(roleID, "Unknown");
				}
				Date dateOfBirth = rs.getDate("dateofbirth");
				String sex = rs.getString("sex");
				String address = rs.getString("address");
				Date createAt = rs.getDate("createAt");
				String authenticationCode = rs.getString("authenticationCode");
				Date confirmationTime = rs.getDate("confirmationTime");
				int status = rs.getInt("status");
				String image = rs.getString("image");
				String isKey = rs.getString("isKey");
				String soDu = rs.getString("soDu");
				String typeUser = rs.getString("typeuser");
				int age = rs.getInt("age");
				String cccd = rs.getString("cccd");
				String degree = rs.getString("degree");
				String numBank = rs.getString("numbank");
				answer = new User(userID, userName, passWord, fullName, email, phone, roles, dateOfBirth, sex, address,
						createAt, authenticationCode, confirmationTime, status, image, isKey, soDu, typeUser, age, cccd,
						degree, numBank != null ? new SoTaiKhoan(numBank) : null);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in selectById for userID=" + t.getUserID() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return answer;
	}

	@Override
	public int insert(User t) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "INSERT INTO user (userID, userName, passWord, email, phoneNumber, roleID, createAt, authenticationCode, confirmationTime, status, image) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getUserID());
			st.setString(2, t.getUserName());
			st.setString(3, t.getPassWord());
			st.setString(4, t.getEmail());
			st.setString(5, t.getPhoneNumber());
			st.setInt(6, t.getRole().getRoleID());
			st.setDate(7, t.getCreateAt());
			st.setString(8, t.getAuthenticationCode());
			st.setDate(9, t.getConfirmationTime());
			st.setInt(10, t.getStatus());
			st.setString(11, t.getImageAvatar());
			ketQua = st.executeUpdate();
			System.out.println("Inserted user: " + t.getUserID());
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in insert for userID=" + t.getUserID() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int insertAll(ArrayList<User> arr) {
		int count = 0;
		for (User user : arr) {
			count += this.insert(user);
		}
		return count;
	}

	@Override
	public int delete(User t) {
		int ans = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "DELETE FROM user WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getUserID());
			ans = st.executeUpdate();
			System.out.println("Deleted user: " + t.getUserID());
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in delete for userID=" + t.getUserID() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return ans;
	}

	@Override
	public int deleteAll(ArrayList<User> arr) {
		int count = 0;
		for (User user : arr) {
			count += this.delete(user);
		}
		return count;
	}

	@Override
	public int update(User t) {
		int ans = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE user SET userName = ?, passWord = ?, fullName = ?, email = ?, phoneNumber = ?, roleID = ?, dateofbirth = ?, sex = ?, address = ?, createAt = ?, authenticationCode = ?, confirmationTime = ?, status = ?, image = ?, isKey = ?, soDu = ?, typeuser = ?, age = ?, cccd = ?, degree = ?, numbank = ? WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getUserName());
			st.setString(2, t.getPassWord());
			st.setString(3, t.getFullName());
			st.setString(4, t.getEmail());
			st.setString(5, t.getPhoneNumber());
			st.setInt(6, t.getRole().getRoleID());
			st.setDate(7, t.getDateOfBirth());
			st.setString(8, t.getSex());
			st.setString(9, t.getAddress());
			st.setDate(10, t.getCreateAt());
			st.setString(11, t.getAuthenticationCode());
			st.setDate(12, t.getConfirmationTime());
			st.setInt(13, t.getStatus());
			st.setString(14, t.getImageAvatar());
			st.setString(15, t.getIsKey());
			st.setString(16, t.getSoDu());
			st.setString(17, t.getTypeUser());
			st.setInt(18, t.getAge());
			st.setString(19, t.getCccd());
			st.setString(20, t.getDegree());
			st.setString(21, t.getSoTaiKhoan() != null ? t.getSoTaiKhoan().getStk() : null);
			st.setString(22, t.getUserID());
			ans = st.executeUpdate();
			System.out.println("Updated user: " + t.getUserID());
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in update for userID=" + t.getUserID() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return ans;
	}

	public boolean kiemTraTenDangNhap(String userName) {
		boolean res = false;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM user WHERE userName = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, userName);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				res = true;
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in kiemTraTenDangNhap for userName=" + userName + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public int insert2(User user) {
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "INSERT INTO user (userID, userName, passWord, fullName, email, phoneNumber, roleID, dateofbirth, sex, address, createAt, authenticationCode, confirmationTime, status, image, isKey) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getUserID());
			st.setString(2, user.getUserName());
			st.setString(3, user.getPassWord());
			st.setString(4, user.getFullName());
			st.setString(5, user.getEmail());
			st.setString(6, user.getPhoneNumber());
			st.setInt(7, user.getRole().getRoleID());
			st.setDate(8, user.getDateOfBirth());
			st.setString(9, user.getSex());
			st.setString(10, user.getAddress());
			st.setDate(11, user.getCreateAt());
			st.setString(12, null);
			st.setDate(13, null);
			st.setInt(14, 0);
			st.setString(15, "");
			st.setString(16, user.getIsKey());
			res = st.executeUpdate();
			System.out.println("Inserted user (insert2): " + user.getUserID());
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in insert2 for userID=" + user.getUserID() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public int updateVertifyInformation(User user) {
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE user SET authenticationCode = ?, confirmationTime = ?, status = ? WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getAuthenticationCode());
			st.setDate(2, user.getConfirmationTime());
			st.setInt(3, 0);
			st.setString(4, user.getUserID());
			res = st.executeUpdate();
			System.out.println("Updated verify info for userID=" + user.getUserID());
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in updateVertifyInformation for userID=" + user.getUserID() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public int deleteByEmail(String email) {
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "DELETE FROM user WHERE email = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, email);
			res = st.executeUpdate();
			System.out.println("Deleted user with email=" + email);
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in deleteByEmail for email=" + email + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public User selectById2(User user) {
		User us = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM user WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getUserID());
			System.out.println("Executing: " + sql + " with userID=" + user.getUserID());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				String userID = rs.getString("userID");
				String userName = rs.getString("userName");
				String passWord = rs.getString("passWord");
				String fullName = rs.getString("fullName");
				String email = rs.getString("email");
				String phone = rs.getString("phoneNumber");
				int roleID = rs.getInt("roleID");
				Roles role = new Roles();
				role.setRoleID(roleID);
				Roles roles = new RolesDao().selectById(role);
				if (roles == null) {
					System.out.println("No role found for roleID=" + roleID + " for userID=" + userID);
					roles = new Roles(roleID, "Unknown");
				}
				Date dateOfBirth = rs.getDate("dateofbirth");
				String sex = rs.getString("sex");
				String address = rs.getString("address");
				Date createAt = rs.getDate("createAt");
				String authenticationCode = rs.getString("authenticationCode");
				Date confirmationTime = rs.getDate("confirmationTime");
				int status = rs.getInt("status");
				String image = rs.getString("image");
				String isKey = rs.getString("isKey");
				String soDu = rs.getString("soDu");
				String typeUser = rs.getString("typeuser");
				int age = rs.getInt("age");
				String cccd = rs.getString("cccd");
				String degree = rs.getString("degree");
				String numBank = rs.getString("numbank");
				us = new User(userID, userName, passWord, fullName, email, phone, roles, dateOfBirth, sex, address,
						createAt, authenticationCode, confirmationTime, status, image, isKey, soDu, typeUser, age, cccd,
						degree, numBank != null ? new SoTaiKhoan(numBank) : null);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in selectById2 for userID=" + user.getUserID() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return us;
	}

	public User selectById3(String userID) {
		User us = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM user WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, userID.trim());
			System.out.println("Executing: " + sql + " with userID=" + userID);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				String userID1 = rs.getString("userID");
				String userName = rs.getString("userName");
				String passWord = rs.getString("passWord");
				String fullName = rs.getString("fullName");
				String email = rs.getString("email");
				String phone = rs.getString("phoneNumber");
				int roleID = rs.getInt("roleID");
				Roles role = new Roles();
				role.setRoleID(roleID);
				Roles roles = new RolesDao().selectById(role);
				if (roles == null) {
					System.out.println("No role found for roleID=" + roleID + " for userID=" + userID1);
					roles = new Roles(roleID, "Unknown");
				}
				Date dateOfBirth = rs.getDate("dateofbirth");
				String sex = rs.getString("sex");
				String address = rs.getString("address");
				Date createAt = rs.getDate("createAt");
				String authenticationCode = rs.getString("authenticationCode");
				Date confirmationTime = rs.getDate("confirmationTime");
				int status = rs.getInt("status");
				String image = rs.getString("image");
				String isKey = rs.getString("isKey");
				String soDu = rs.getString("soDu");
				String typeUser = rs.getString("typeuser");
				int age = rs.getInt("age");
				String cccd = rs.getString("cccd");
				String degree = rs.getString("degree");
				String numBank = rs.getString("numbank");
				us = new User(userID1, userName, passWord, fullName, email, phone, roles, dateOfBirth, sex, address,
						createAt, authenticationCode, confirmationTime, status, image, isKey, soDu, typeUser, age, cccd,
						degree, numBank != null ? new SoTaiKhoan(numBank) : null);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in selectById3 for userID=" + userID + ": " + e.getMessage());
			e.printStackTrace();
		}
		return us;
	}

	public int updateVertifyInformation2(User us) {
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE user SET status = ? WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, us.getStatus());
			st.setString(2, us.getUserID());
			res = st.executeUpdate();
			System.out.println("Updated verify info (2) for userID=" + us.getUserID());
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in updateVertifyInformation2 for userID=" + us.getUserID() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public User selectByUserNameAndPassWord(User user) {
		User us = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM user WHERE userName = ? AND passWord = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getUserName());
			st.setString(2, user.getPassWord());
			System.out.println("Executing: " + sql + " with userName=" + user.getUserName());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				String userID = rs.getString("userID");
				String userName = rs.getString("userName");
				String passWord = rs.getString("passWord");
				String fullName = rs.getString("fullName");
				String email = rs.getString("email");
				String phone = rs.getString("phoneNumber");
				int roleID = rs.getInt("roleID");
				Roles role = new Roles();
				role.setRoleID(roleID);
				Roles roles = new RolesDao().selectById(role);
				if (roles == null) {
					System.out.println("No role found for roleID=" + roleID + " for userID=" + userID);
					roles = new Roles(roleID, "Unknown");
				}
				Date dateOfBirth = rs.getDate("dateofbirth");
				String sex = rs.getString("sex");
				String address = rs.getString("address");
				Date createAt = rs.getDate("createAt");
				String authenticationCode = rs.getString("authenticationCode");
				Date confirmationTime = rs.getDate("confirmationTime");
				int status = rs.getInt("status");
				String image = rs.getString("image");
				String isKey = rs.getString("isKey");
				String soDu = rs.getString("soDu");
				String typeUser = rs.getString("typeuser");
				int age = rs.getInt("age");
				String cccd = rs.getString("cccd");
				String degree = rs.getString("degree");
				String numBank = rs.getString("numbank");
				us = new User(userID, userName, passWord, fullName, email, phone, roles, dateOfBirth, sex, address,
						createAt, authenticationCode, confirmationTime, status, image, isKey, soDu, typeUser, age, cccd,
						degree, numBank != null ? new SoTaiKhoan(numBank) : null);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in selectByUserNameAndPassWord for userName=" + user.getUserName() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return us;
	}

	public int updateImgUser(User user) {
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE user SET image = ? WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getImageAvatar());
			st.setString(2, user.getUserID());
			res = st.executeUpdate();
			System.out.println("Updated image for userID=" + user.getUserID());
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in updateImgUser for userID=" + user.getUserID() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public int updateCusInformation(User user) {
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE user SET fullName = ?, email = ?, phoneNumber = ?, address = ?, dateofbirth = ?, sex = ? WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getFullName());
			st.setString(2, user.getEmail());
			st.setString(3, user.getPhoneNumber());
			st.setString(4, user.getAddress());
			st.setDate(5, user.getDateOfBirth());
			st.setString(6, user.getSex());
			st.setString(7, user.getUserID());
			res = st.executeUpdate();
			System.out.println("Updated customer info for userID=" + user.getUserID());
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in updateCusInformation for userID=" + user.getUserID() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public int updateImgAvatar(User user) {
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE user SET image = ? WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getImageAvatar());
			st.setString(2, user.getUserID());
			res = st.executeUpdate();
			System.out.println("Updated avatar for userID=" + user.getUserID());
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in updateImgAvatar for userID=" + user.getUserID() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public int upDatePassWordNew(User user) {
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE user SET passWord = ? WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getPassWord());
			st.setString(2, user.getUserID());
			res = st.executeUpdate();
			System.out.println("Updated password for userID=" + user.getUserID());
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in upDatePassWordNew for userID=" + user.getUserID() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public boolean kiemTraByEmail(String email) {
		boolean res = false;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM user WHERE email = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				res = true;
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in kiemTraByEmail for email=" + email + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public boolean kiemTraSoDienThoai(String phone) {
		boolean res = false;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM user WHERE phoneNumber = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, phone);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				res = true;
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in kiemTraSoDienThoai for phone=" + phone + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public User getUserByEmail(String email) {
		User user = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM user WHERE email = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, email);
			System.out.println("Executing: " + sql + " with email=" + email);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				String userID = rs.getString("userID");
				String userName = rs.getString("userName");
				String passWord = rs.getString("passWord");
				String fullName = rs.getString("fullName");
				String gmail = rs.getString("email");
				String phone = rs.getString("phoneNumber");
				int roleID = rs.getInt("roleID");
				Roles role = new Roles();
				role.setRoleID(roleID);
				Roles roles = new RolesDao().selectById(role);
				if (roles == null) {
					System.out.println("No role found for roleID=" + roleID + " for userID=" + userID);
					roles = new Roles(roleID, "Unknown");
				}
				Date dateOfBirth = rs.getDate("dateofbirth");
				String sex = rs.getString("sex");
				String address = rs.getString("address");
				Date createAt = rs.getDate("createAt");
				String authenticationCode = rs.getString("authenticationCode");
				Date confirmationTime = rs.getDate("confirmationTime");
				int status = rs.getInt("status");
				String image = rs.getString("image");
				String isKey = rs.getString("isKey");
				String soDu = rs.getString("soDu");
				String typeUser = rs.getString("typeuser");
				int age = rs.getInt("age");
				String cccd = rs.getString("cccd");
				String degree = rs.getString("degree");
				String numBank = rs.getString("numbank");
				user = new User(userID, userName, passWord, fullName, gmail, phone, roles, dateOfBirth, sex, address,
						createAt, authenticationCode, confirmationTime, status, image, isKey, soDu, typeUser, age, cccd,
						degree, numBank != null ? new SoTaiKhoan(numBank) : null);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in getUserByEmail for email=" + email + ": " + e.getMessage());
			e.printStackTrace();
		}
		return user;
	}

	public int updatePassWordNewReal(User user) {
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE user SET passWord = ? WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getPassWord());
			st.setString(2, user.getUserID());
			res = st.executeUpdate();
			System.out.println("Updated password (real) for userID=" + user.getUserID());
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in updatePassWordNewReal for userID=" + user.getUserID() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public User selectUserById(String userID) {
		User user = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM user WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, userID.trim());
			System.out.println("Executing: " + sql + " with userID=" + userID);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				String userID1 = rs.getString("userID");
				String userName = rs.getString("userName");
				String passWord = rs.getString("passWord");
				String fullName = rs.getString("fullName");
				String email = rs.getString("email");
				String phone = rs.getString("phoneNumber");
				int roleID = rs.getInt("roleID");
				Roles role = new Roles();
				role.setRoleID(roleID);
				Roles roles = new RolesDao().selectById(role);
				if (roles == null) {
					System.out.println("No role found for roleID=" + roleID + " for userID=" + userID1);
					roles = new Roles(roleID, "Unknown");
				}
				Date dateOfBirth = rs.getDate("dateofbirth");
				String sex = rs.getString("sex");
				String address = rs.getString("address");
				Date createAt = rs.getDate("createAt");
				String authenticationCode = rs.getString("authenticationCode");
				Date confirmationTime = rs.getDate("confirmationTime");
				int status = rs.getInt("status");
				String image = rs.getString("image");
				String isKey = rs.getString("isKey");
				String soDu = rs.getString("soDu");
				String typeUser = rs.getString("typeuser");
				int age = rs.getInt("age");
				String cccd = rs.getString("cccd");
				String degree = rs.getString("degree");
				String numBank = rs.getString("numbank");
				user = new User(userID1, userName, passWord, fullName, email, phone, roles, dateOfBirth, sex, address,
						createAt, authenticationCode, confirmationTime, status, image, isKey, soDu, typeUser, age, cccd,
						degree, numBank != null ? new SoTaiKhoan(numBank) : null);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in selectUserById for userID=" + userID + ": " + e.getMessage());
			e.printStackTrace();
		}
		return user;
	}

	public ArrayList<User> selectUserNotAdmin() {
		ArrayList<User> ans = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM user WHERE roleID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, 2);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String userID = rs.getString("userID");
				String userName = rs.getString("userName");
				String passWord = rs.getString("passWord");
				String fullName = rs.getString("fullName");
				String email = rs.getString("email");
				String phone = rs.getString("phoneNumber");
				int roleID = rs.getInt("roleID");
				Roles role = new Roles();
				role.setRoleID(roleID);
				Roles roles = new RolesDao().selectById(role);
				if (roles == null) {
					System.out.println("No role found for roleID=" + roleID + " for userID=" + userID);
					roles = new Roles(roleID, "Unknown");
				}
				Date dateOfBirth = rs.getDate("dateofbirth");
				String sex = rs.getString("sex");
				String address = rs.getString("address");
				Date createAt = rs.getDate("createAt");
				String authenticationCode = rs.getString("authenticationCode");
				Date confirmationTime = rs.getDate("confirmationTime");
				int status = rs.getInt("status");
				String image = rs.getString("image");
				String isKey = rs.getString("isKey");
				String soDu = rs.getString("soDu");
				String typeUser = rs.getString("typeuser");
				int age = rs.getInt("age");
				String cccd = rs.getString("cccd");
				String degree = rs.getString("degree");
				String numBank = rs.getString("numbank");
				User user = new User(userID, userName, passWord, fullName, email, phone, roles, dateOfBirth, sex, address,
						createAt, authenticationCode, confirmationTime, status, image, isKey, soDu, typeUser, age, cccd,
						degree, numBank != null ? new SoTaiKhoan(numBank) : null);
				ans.add(user);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in selectUserNotAdmin: " + e.getMessage());
			e.printStackTrace();
		}
		return ans;
	}

	public int updateUserIsKey(String userID, String string) {
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE user SET isKey = ? WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, string);
			st.setString(2, userID.trim());
			res = st.executeUpdate();
			System.out.println("Updated isKey for userID=" + userID);
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in updateUserIsKey for userID=" + userID + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public boolean checkUserNameIsTrue(String userName) {
		boolean res = false;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM user WHERE userName = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, userName.trim());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				res = true;
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in checkUserNameIsTrue for userName=" + userName + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public int khoaTaiKhoanViNhapSaiHonMuoiLan(String userName) {
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE user SET isKey = ? WHERE userName = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "Bị khóa");
			st.setString(2, userName.trim());
			res = st.executeUpdate();
			System.out.println("Locked account for userName=" + userName);
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in khoaTaiKhoanViNhapSaiHonMuoiLan for userName=" + userName + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public boolean kiemTraNameUserHasBlock(String userName) {
		boolean res = false;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM user WHERE userName = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, userName);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				String isKey = rs.getString("isKey");
				if ("Bị khóa".equalsIgnoreCase(isKey)) {
					res = true;
				}
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in kiemTraNameUserHasBlock for userName=" + userName + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public int capNhatSoDuMoi(User user, String menhGia) {
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE user SET soDu = ? WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			int soDuMoi = Integer.parseInt(user.getSoDu().trim()) + Integer.parseInt(menhGia.trim());
			st.setString(1, String.valueOf(soDuMoi));
			st.setString(2, user.getUserID());
			res = st.executeUpdate();
			System.out.println("Updated soDu for userID=" + user.getUserID() + " to " + soDuMoi);
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in capNhatSoDuMoi for userID=" + user.getUserID() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public String isCTV(String userID) {
		String res = "";
		User user = selectById3(userID);
		if (user != null && user.getRole().getRoleID() == 3) {
			res = "ok";
		} else {
			res = "notok";
		}
		return res;
	}

	public boolean kiemTraHoTen(String name, String userID) {
		boolean check = false;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM user WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, userID);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				String fullName = rs.getString("fullName");
				if (name.equalsIgnoreCase(fullName)) {
					check = true;
				}
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in kiemTraHoTen for userID=" + userID + ": " + e.getMessage());
			e.printStackTrace();
		}
		return check;
	}

	public boolean updateCTV(String age, String cccd, String gender, String degree, String stk, String nh, String userID) {
		boolean res = false;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE user SET age = ?, cccd = ?, degree = ?, numbank = ? WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, Integer.parseInt(age));
			st.setString(2, cccd);
			st.setString(3, degree);
			st.setString(4, stk);
			st.setString(5, userID);
			int res2 = st.executeUpdate();
			if (res2 > 0) {
				res = true;
			}
			System.out.println("Updated CTV for userID=" + userID);
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in updateCTV for userID=" + userID + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public String getSoDu(String userID) {
		String ans = "";
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM user WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, userID.trim());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				ans = rs.getString("soDu");
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in getSoDu for userID=" + userID + ": " + e.getMessage());
			e.printStackTrace();
		}
		return ans;
	}

	public int insert3(User user) {
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "INSERT INTO user (userID, userName, passWord, fullName, email, phoneNumber, roleID, dateofbirth, sex, address, createAt, authenticationCode, confirmationTime, status, image, isKey, soDu, typeuser, age, cccd, degree, numbank, isDesposit) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getUserID());
			st.setString(2, user.getUserName());
			st.setString(3, user.getPassWord());
			st.setString(4, user.getFullName());
			st.setString(5, user.getEmail());
			st.setString(6, user.getPhoneNumber());
			st.setInt(7, user.getRole().getRoleID());
			st.setDate(8, user.getDateOfBirth());
			st.setString(9, user.getSex());
			st.setString(10, user.getAddress());
			st.setDate(11, user.getCreateAt());
			st.setString(12, user.getAuthenticationCode());
			st.setDate(13, user.getConfirmationTime());
			st.setInt(14, user.getStatus());
			st.setString(15, user.getImageAvatar());
			st.setString(16, user.getIsKey());
			st.setString(17, user.getSoDu());
			st.setString(18, user.getTypeUser());
			st.setInt(19, user.getAge());
			st.setString(20, user.getCccd());
			st.setString(21, user.getDegree());
			st.setString(22, user.getSoTaiKhoan() != null ? user.getSoTaiKhoan().getStk() : null);
			st.setString(23, null);
			res = st.executeUpdate();
			System.out.println("Inserted user (insert3): " + user.getUserID());
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in insert3 for userID=" + user.getUserID() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public String selectSoDuHienTai(String userID) {
		String ans = "";
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM user WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, userID.trim());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				ans = rs.getString("soDu");
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in selectSoDuHienTai for userID=" + userID + ": " + e.getMessage());
			e.printStackTrace();
		}
		return ans;
	}

	public int capNhatSoDuMoiKhiThemSP(User user, String valueOf) {
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE user SET soDu = ? WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, valueOf.trim());
			st.setString(2, user.getUserID());
			res = st.executeUpdate();
			System.out.println("Updated soDu for userID=" + user.getUserID());
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in capNhatSoDuMoiKhiThemSP for userID=" + user.getUserID() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public int updateTypeUser(String userID, int i) {
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE user SET typeuser = ? WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, String.valueOf(i));
			st.setString(2, userID.trim());
			res = st.executeUpdate();
			System.out.println("Updated typeuser for userID=" + userID);
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in updateTypeUser for userID=" + userID + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<User> selectEmployeeNotAdminNotUser() {
		ArrayList<User> lst = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM user WHERE roleID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, 4);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String userID = rs.getString("userID");
				String userName = rs.getString("userName");
				String passWord = rs.getString("passWord");
				String fullName = rs.getString("fullName");
				String email = rs.getString("email");
				String phone = rs.getString("phoneNumber");
				int roleID = rs.getInt("roleID");
				Roles role = new Roles();
				role.setRoleID(roleID);
				Roles roles = new RolesDao().selectById(role);
				if (roles == null) {
					System.out.println("No role found for roleID=" + roleID + " for userID=" + userID);
					roles = new Roles(roleID, "Unknown");
				}
				Date dateOfBirth = rs.getDate("dateofbirth");
				String sex = rs.getString("sex");
				String address = rs.getString("address");
				Date createAt = rs.getDate("createAt");
				String authenticationCode = rs.getString("authenticationCode");
				Date confirmationTime = rs.getDate("confirmationTime");
				int status = rs.getInt("status");
				String image = rs.getString("image");
				String isKey = rs.getString("isKey");
				String soDu = rs.getString("soDu");
				String typeUser = rs.getString("typeuser");
				int age = rs.getInt("age");
				String cccd = rs.getString("cccd");
				String degree = rs.getString("degree");
				String numBank = rs.getString("numbank");
				User user = new User(userID, userName, passWord, fullName, email, phone, roles, dateOfBirth, sex, address,
						createAt, authenticationCode, confirmationTime, status, image, isKey, soDu, typeUser, age, cccd,
						degree, numBank != null ? new SoTaiKhoan(numBank) : null);
				lst.add(user);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in selectEmployeeNotAdminNotUser: " + e.getMessage());
			e.printStackTrace();
		}
		return lst;
	}

	public int setUpRolesAndDesposit(String userID) {
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE user SET roleID = ?, isDesposit = ? WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, 3);
			st.setString(2, "Đang cọc");
			st.setString(3, userID);
			res = st.executeUpdate();
			System.out.println("Updated role and deposit for userID=" + userID);
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in setUpRolesAndDesposit for userID=" + userID + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public int capNhatNhanVienMoi(String userID) {
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE user SET status = ?, isKey = ? WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, 1);
			st.setString(2, "Hoạt động");
			st.setString(3, userID);
			res = st.executeUpdate();
			System.out.println("Updated nhan vien moi for userID=" + userID);
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in capNhatNhanVienMoi for userID=" + userID + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public String selectSoTaiKhoan(String userID) {
		String ans = "";
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM user WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, userID);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				ans = rs.getString("numbank");
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in selectSoTaiKhoan for userID=" + userID + ": " + e.getMessage());
			e.printStackTrace();
		}
		return ans;
	}

	public int hoanTienLaiChoCtv(String userID, String valueOf) {
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE user SET soDu = ? WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, valueOf.trim());
			st.setString(2, userID.trim());
			res = st.executeUpdate();
			System.out.println("Updated soDu for CTV userID=" + userID);
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in hoanTienLaiChoCtv for userID=" + userID + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public int restartCtvIsNullAndReturnCus(String userID) {
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE user SET roleID = ?, age = ?, cccd = ?, degree = ?, numbank = ?, isDesposit = ? WHERE userID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, 2);
			st.setInt(2, 0);
			st.setString(3, null);
			st.setString(4, null);
			st.setString(5, null);
			st.setString(6, null);
			st.setString(7, userID.trim());
			res = st.executeUpdate();
			System.out.println("Reset CTV for userID=" + userID);
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			System.err.println("Error in restartCtvIsNullAndReturnCus for userID=" + userID + ": " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}
}