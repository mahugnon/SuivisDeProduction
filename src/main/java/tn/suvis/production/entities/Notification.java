package tn.suvis.production.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
@Entity
public class Notification implements Serializable{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private Long code;
	@ManyToOne
	@JoinColumn(name="CODE_ARRET")
private Arret arret;
	@ManyToOne
	@JoinColumn(name="CODE_USER_DEST")
private Users user;
private Date dateNotification;
private String contenu;
public Notification() {
	super();
}
public Notification(Arret arret, Users user, Date dateNotification, String contenu) {
	super();
	this.arret = arret;
	this.user = user;
	this.dateNotification = dateNotification;
	this.contenu = contenu;
}
public Long getCode() {
	return code;
}
public void setCode(Long code) {
	this.code = code;
}
public Arret getArret() {
	return arret;
}
public void setArret(Arret arret) {
	this.arret = arret;
}
@JsonIgnore
public Users getUser() {
	return user;
}
@JsonSetter
public void setUser(Users user) {
	this.user = user;
}
public Date getDateNotification() {
	return dateNotification;
}
public void setDateNotification(Date dateNotification) {
	this.dateNotification = dateNotification;
}
public String getContenu() {
	return contenu;
}
public void setContenu(String contenu) {
	this.contenu = contenu;
}
}
