package tn.suvis.production.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class Alert implements Serializable {
@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
private Long id;
private int premereEscale;
private int duexiemeEscale;
private int troisiemeEscale;
private String info;
private Date date;
@ManyToOne
@JoinColumn(name="ID_RECEPT")
private Users users;
public Users getUsers() {
	return users;
}
public void setUsers(Users users) {
	this.users = users;
}
public Alert() {
	super();
	// TODO Auto-generated constructor stub
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public int getPremereEscale() {
	return premereEscale;
}
public void setPremereEscale(int premereEscale) {
	this.premereEscale = premereEscale;
}
public int getDuexiemeEscale() {
	return duexiemeEscale;
}
public void setDuexiemeEscale(int duexiemeEscale) {
	this.duexiemeEscale = duexiemeEscale;
}
public int getTroisiemeEscale() {
	return troisiemeEscale;
}
public void setTroisiemeEscale(int troisiemeEscale) {
	this.troisiemeEscale = troisiemeEscale;
}
public String getInfo() {
	return info;
}
public void setInfo(String info) {
	this.info = info;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}

}
