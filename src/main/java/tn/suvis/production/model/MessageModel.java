package tn.suvis.production.model;

import tn.suvis.production.entities.Message;

public class MessageModel {
private Message message;
private boolean vu;
private String role;
public MessageModel() {
	super();
	// TODO Auto-generated constructor stub
}
public Message getMessage() {
	return message;
}
public void setMessage(Message message) {
	this.message = message;
}
public boolean isVu() {
	return vu;
}
public void setVu(boolean vu) {
	this.vu = vu;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
}
