package tn.suvis.production.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
@Entity
public class Message implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
	@ManyToOne
	@JoinColumn(name="ID_EMET")
private Users emetteur;
	@ManyToMany
	@JoinTable(name="USER_RECEPT")
private Collection<Users> recepteurs;
	@ManyToMany
	@JoinTable(name="USER_VU_SMG")
private Collection<Users> estVuPar=new ArrayList<Users>();
private Date  date;
private String sujet;
private String contenu;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}

public Users getEmetteur() {
	return emetteur;
}
@JsonSetter
public void setEmetteur(Users emetteur) {
	this.emetteur = emetteur;
}
@JsonIgnore
public Collection<Users> getRecepteurs() {
	return recepteurs;
}
@JsonSetter
public void setRecepteurs(Collection<Users> recepteurs) {
	this.recepteurs = recepteurs;
}
@JsonIgnore
public Collection<Users> getEstVuPar() {
	return estVuPar;
}
@JsonSetter
public void setEstVuPar(Collection<Users> estVuPar) {
	this.estVuPar = estVuPar;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getContenu() {
	return contenu;
}
public void setContenu(String contenu) {
	this.contenu = contenu;
}
public Message() {
	super();
	this.date=new Date();
	
	// TODO Auto-generated constructor stub
}
public Message( String contenu,String sujet) {
	super();
	this.sujet="Kuso Connect: ";
	this.sujet=this.sujet+sujet;
	this.date = new Date();
	this.contenu = contenu;
}
public String getSujet() {
	return sujet;
}
public void setSujet(String sujet) {
	this.sujet="Kuso Connect: ";
	this.sujet =this.sujet+ sujet;
}

}
