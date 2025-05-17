package com.projectttweb.webphone.model;

import java.sql.Date;

public class User {
private String userID;
private String userName;
private String passWord;
private String fullName;
private String email;
private String phoneNumber;
private Roles role;
private Date dateOfBirth;
private String sex;
private String address;
private Date createAt;
private String authenticationCode;
private Date confirmationTime;
private int status;
private String imageAvatar;
private String isKey;
private String soDu;
private String typeUser;
private int age;
private String cccd;
private String degree;
private SoTaiKhoan soTaiKhoan;
private String desposit;
private String isLoginGoogle;

	/**
	 * Constructs a User object with all available user attributes, including identification, personal details, role, account information, deposit, and Google login status.
	 *
	 * @param userID unique identifier for the user
	 * @param userName username for authentication
	 * @param passWord password for authentication
	 * @param fullName user's full name
	 * @param email user's email address
	 * @param phoneNumber user's phone number
	 * @param role user's role within the system
	 * @param dateOfBirth user's date of birth
	 * @param sex user's gender
	 * @param address user's address
	 * @param createAt account creation date
	 * @param authenticationCode code used for user authentication
	 * @param confirmationTime time when the account was confirmed
	 * @param status account status indicator
	 * @param imageAvatar URL or path to the user's avatar image
	 * @param isKey status indicating if the user is active
	 * @param soDu user's account balance
	 * @param typeUser type or category of the user
	 * @param age user's age
	 * @param cccd user's identification number
	 * @param degree user's degree or qualification
	 * @param soTaiKhoan user's account information
	 * @param desposit user's deposit amount
	 * @param isLoginGoogle indicates if the user logged in via Google
	 */
	public User(String userID, String userName, String passWord, String fullName, String email, String phoneNumber, Roles role, Date dateOfBirth, String sex, String address, Date createAt, String authenticationCode, Date confirmationTime, int status, String imageAvatar, String isKey, String soDu, String typeUser, int age, String cccd, String degree, SoTaiKhoan soTaiKhoan, String desposit, String isLoginGoogle) {
		this.userID = userID;
		this.userName = userName;
		this.passWord = passWord;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.dateOfBirth = dateOfBirth;
		this.sex = sex;
		this.address = address;
		this.createAt = createAt;
		this.authenticationCode = authenticationCode;
		this.confirmationTime = confirmationTime;
		this.status = status;
		this.imageAvatar = imageAvatar;
		this.isKey = isKey;
		this.soDu = soDu;
		this.typeUser = typeUser;
		this.age = age;
		this.cccd = cccd;
		this.degree = degree;
		this.soTaiKhoan = soTaiKhoan;
		this.desposit = desposit;
		this.isLoginGoogle = isLoginGoogle;
	}

	/**
	 * Constructs a User with the specified identification, personal, role, account, and authentication details, including Google login status.
	 *
	 * @param userID the unique identifier for the user
	 * @param userName the user's login name
	 * @param passWord the user's password
	 * @param fullName the user's full name
	 * @param email the user's email address
	 * @param phoneNumber the user's phone number
	 * @param role the user's role
	 * @param dateOfBirth the user's date of birth
	 * @param sex the user's gender
	 * @param address the user's address
	 * @param createAt the account creation date
	 * @param authenticationCode the code used for authentication
	 * @param confirmationTime the time of account confirmation
	 * @param status the user's account status
	 * @param imageAvatar the user's avatar image
	 * @param isKey indicates if the user is active
	 * @param soDu the user's account balance
	 * @param typeUser the type of user
	 * @param isLoginGoogle indicates if the user logged in via Google
	 */
	public User(String userID, String userName, String passWord, String fullName, String email, String phoneNumber, Roles role, Date dateOfBirth, String sex, String address, Date createAt, String authenticationCode, Date confirmationTime, int status, String imageAvatar, String isKey, String soDu, String typeUser, String isLoginGoogle) {
		this.userID = userID;
		this.userName = userName;
		this.passWord = passWord;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.dateOfBirth = dateOfBirth;
		this.sex = sex;
		this.address = address;
		this.createAt = createAt;
		this.authenticationCode = authenticationCode;
		this.confirmationTime = confirmationTime;
		this.status = status;
		this.imageAvatar = imageAvatar;
		this.isKey = isKey;
		this.soDu = soDu;
		this.typeUser = typeUser;
		this.isLoginGoogle = isLoginGoogle;
	}

	/**
 * Constructs a User with the specified identification, personal, role, and account details.
 *
 * Initializes a User instance with provided values for user credentials, personal information, role, account status, and metadata.
 */
public User(String userID, String userName, String passWord, String fullName, String email, String phoneNumber,
				Roles role, Date dateOfBirth, String sex, String address, Date createAt, String authenticationCode,
				Date confirmationTime, int status, String imageAvatar, String isKey, String soDu, String typeUser) {
	this.userID = userID;
	this.userName = userName;
	this.passWord = passWord;
	this.fullName = fullName;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.role = role;
	this.dateOfBirth = dateOfBirth;
	this.sex = sex;
	this.address = address;
	this.createAt = createAt;
	this.authenticationCode = authenticationCode;
	this.confirmationTime = confirmationTime;
	this.status = status;
	this.imageAvatar = imageAvatar;
	this.isKey = isKey;
	this.soDu = soDu;
	this.typeUser = typeUser;
}




public User(String userID, String userName, String passWord, String fullName, String email, String phoneNumber,
		Roles role, Date dateOfBirth, String sex, String address, Date createAt, String authenticationCode,
		Date confirmationTime, int status, String imageAvatar, String isKey, String soDu, String typeUser, int age,
		String cccd, String degree, SoTaiKhoan numBank) {
	this.userID = userID;
	this.userName = userName;
	this.passWord = passWord;
	this.fullName = fullName;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.role = role;
	this.dateOfBirth = dateOfBirth;
	this.sex = sex;
	this.address = address;
	this.createAt = createAt;
	this.authenticationCode = authenticationCode;
	this.confirmationTime = confirmationTime;
	this.status = status;
	this.imageAvatar = imageAvatar;
	this.isKey = isKey;
	this.soDu = soDu;
	this.typeUser = typeUser;
	this.age = age;
	this.cccd = cccd;
	this.degree = degree;
	this.soTaiKhoan = numBank;
}




public User(String userID, String userName, String passWord, String fullName, String email, String phoneNumber, Roles role,
		Date dateOfBirth, String sex, String address, Date createAt, String imageAvatar) {
	super();
	this.userID = userID;
	this.userName = userName;
	this.passWord = passWord;
	this.fullName = fullName;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.role = role;
	this.dateOfBirth = dateOfBirth;
	this.sex = sex;
	this.address = address;
	this.createAt = createAt;
	this.imageAvatar = imageAvatar;
	this.isKey = "Hoạt động";
	this.soDu = "0";
	this.typeUser = "1";
}
public User(String userID, String userName, String passWord, String fullName, String email, String phoneNumber, Roles role,
		Date dateOfBirth, String sex, String address, Date createAt, String imageAvatar, String isKey) {
	super();
	this.userID = userID;
	this.userName = userName;
	this.passWord = passWord;
	this.fullName = fullName;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.role = role;
	this.dateOfBirth = dateOfBirth;
	this.sex = sex;
	this.address = address;
	this.createAt = createAt;
	this.imageAvatar = imageAvatar;
	this.isKey = isKey;
	this.soDu = "0";
	this.typeUser = "1";
}

public User() {
	super();
}

public User(String userID, String userName, String passWord, String email, String phoneNumber, Roles role,
		Date createAt, String imageAvatar) {
	this.userID = userID;
	this.userName = userName;
	this.passWord = passWord;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.role = role;
	this.createAt = createAt;
	this.imageAvatar = imageAvatar;
}
public String getUserID() {
	return userID;
}
public void setUserID(String userID) {
	this.userID = userID;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassWord() {
	return passWord;
}
public void setPassWord(String passWord) {
	this.passWord = passWord;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public Roles getRole() {
	return role;
}
public void setRole(Roles role) {
	this.role = role;
}
public Date getCreateAt() {
	return createAt;
}
public void setCreateAt(Date createAt) {
	this.createAt = createAt;
}
public String getAuthenticationCode() {
	return authenticationCode;
}
public void setAuthenticationCode(String authenticationCode) {
	this.authenticationCode = authenticationCode;
}
public Date getConfirmationTime() {
	return confirmationTime;
}
public void setConfirmationTime(Date confirmationTime) {
	this.confirmationTime = confirmationTime;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public String getImageAvatar() {
	return imageAvatar;
}
public void setImageAvatar(String imageAvatar) {
	this.imageAvatar = imageAvatar;
}


public Date getDateOfBirth() {
	return dateOfBirth;
}


public void setDateOfBirth(Date dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
}


public String getSex() {
	return sex;
}


public void setSex(String sex) {
	this.sex = sex;
}


public String getAddress() {
	return address;
}


public void setAddress(String address) {
	this.address = address;
}


public String getFullName() {
	return fullName;
}


public void setFullName(String fullName) {
	this.fullName = fullName;
}


public String getIsKey() {
	return isKey;
}


public void setIsKey(String isKey) {
	this.isKey = isKey;
}


public String getSoDu() {
	return soDu;
}


public void setSoDu(String soDu) {
	this.soDu = soDu;
}



public String getTypeUser() {
	return typeUser;
}



public void setTypeUser(String typeUser) {
	this.typeUser = typeUser;
}




public int getAge() {
	return age;
}




public void setAge(int age) {
	this.age = age;
}




public String getCccd() {
	return cccd;
}




public void setCccd(String cccd) {
	this.cccd = cccd;
}




public String getDegree() {
	return degree;
}




public void setDegree(String degree) {
	this.degree = degree;
}




public SoTaiKhoan getSoTaiKhoan() {
	return soTaiKhoan;
}




/**
 * Sets the user's associated account information.
 *
 * @param soTaiKhoan the account information to associate with the user
 */
public void setSoTaiKhoan(SoTaiKhoan soTaiKhoan) {
	this.soTaiKhoan = soTaiKhoan;
}

	/**
	 * Returns the deposit amount associated with the user.
	 *
	 * @return the user's deposit amount
	 */
	public String getDesposit() {
		return desposit;
	}

	/****
	 * Sets the deposit amount for the user.
	 *
	 * @param desposit the deposit amount to assign
	 */
	public void setDesposit(String desposit) {
		this.desposit = desposit;
	}

	/****
	 * Returns the Google login status of the user.
	 *
	 * @return a string indicating whether the user is logged in with Google
	 */
	public String getIsLoginGoogle() {
		return isLoginGoogle;
	}

	/****
	 * Sets the flag indicating whether the user is logged in via Google.
	 *
	 * @param isLoginGoogle a string representing the Google login status
	 */
	public void setIsLoginGoogle(String isLoginGoogle) {
		this.isLoginGoogle = isLoginGoogle;
	}
}
