package com.example.mybatistest.Notice;

public class UserBean {
	
	private String Id;
	private String Password;
	private String NickName;
	
	public String getId() {
		return Id;
	}
	
	public void SetId(String Id) {
		this.Id = Id;
	}
	
	public String getPassword() {
		return Password;
	}
	
	public void SetPassword(String Password) {
		this.Password = Password;
	}
	
	public String getNickName() {
		return NickName;
	}
	
	public void SetNickName(String NickName) {
		this.NickName = NickName;
	}

}
