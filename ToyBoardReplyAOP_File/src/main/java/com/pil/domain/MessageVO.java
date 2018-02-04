package com.pil.domain;

import java.util.Date;

public class MessageVO {
	private Integer no;
	private String targetid;
	private String sender;
	private String message;
	private Date opendate;
	private Date senddate;

	public Integer getId() {
		return no;
	}
	public void setId(Integer no) {
		this.no = no;
	}
	public String getTargetid() {
		return targetid;
	}
	public void setTargetid(String targetid) {
		this.targetid = targetid;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getOpendate() {
		return opendate;
	}
	public void setOpendate(Date opendate) {
		this.opendate = opendate;
	}
	public Date getSenddate() {
		return senddate;
	}
	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}
	public String toString() {
		return "no: "+no+", targetid: " + targetid + ", sender: " +sender + ", message: "+message;
	}
}
