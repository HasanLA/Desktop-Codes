package com.domain.CodesToMemorize;

public class Users {
	
	//must create fields for all the values that exist within the payload entitites, whether or not you decide to set those fields.
	
	protected String userName;
	protected String role;
	protected String id;
	protected String getCreatedAt;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGetCreatedAt() {
		return getCreatedAt;
	}

	public void setGetCreatedAt(String getCreatedAt) {
		this.getCreatedAt = getCreatedAt;
	}

	Users(String userName, String role){
		userName=this.userName;
		role=this.role;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
