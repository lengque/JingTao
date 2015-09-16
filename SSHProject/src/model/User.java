package model;

import java.sql.Timestamp;

public class User {
	private String userId;
	private String userName;
	private String password;
	//基本信息
	private String IdCardNo;
	private String realName;
	private Integer gender;
    private String telphone;
    private String email;
    private String address;
    //后台维护信息
    private Integer role;
    private Integer grade;
    private Integer status;
    private Timestamp createTime;
    private Timestamp updateTime;
  
    public String getUserName() {  
        return userName;  
    }  
  
    public void setUserName(String userName) {  
        this.userName = userName;  
    }  
  
    public String getPassword() {  
        return password;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }  
  
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	//idCardNo
	public String getIdCardNo() {
		return IdCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		IdCardNo = idCardNo;
	}

	//role
	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
}
