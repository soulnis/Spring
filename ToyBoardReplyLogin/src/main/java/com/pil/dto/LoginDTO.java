package com.pil.dto;

public class LoginDTO {
	private String userid;
	private String userpw;
	private boolean userCookie;
//	private String userCookie;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
//	public String isUseCookie() {
//		return userCookie;
//	}
//	public void setUseCookie(String userCookie) {
//		this.userCookie = userCookie;
//	}
	public boolean isUseCookie() {
		return userCookie;
	}
	public void setUseCookie(boolean userCookie) {
		this.userCookie = userCookie;
	}
	@Override
	public String toString() {
		return "LoginDTO [userid=" + userid + ", userpw=" + userpw + ", userCookie=" + userCookie + "]";
	}
}
