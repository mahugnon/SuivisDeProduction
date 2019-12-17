package tn.suvis.production.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
@Entity
public class Post implements Serializable{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
private String nom;
private String description;
private Date date;
@ManyToOne
@JoinColumn(name="CODE_CONTRE_MAITRE")
private Users users;
@ManyToOne
@JoinColumn(name="CODE_SEGMENT")
private Segment segment;
public Post() {
	super();
	
}
public Post(String nom, String description, Date date) {
	super();
	this.nom = nom;
	this.description = description;
	this.date = date;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
@JsonIgnore
public Users getUsers() {
	return users;
}
@JsonSetter
public void setUsers(Users users) {
	this.users = users;
}
@JsonIgnore
public Segment getSegment() {
	return segment;
}
@JsonSetter
public void setSegment(Segment segment) {
	this.segment = segment;
}


}
