package tn.suvis.production.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class OpDechargerCable implements Serializable{
private Long code;
private Users auteur;
private Date date;
private Cable cables;
private Chaine chaine;
public OpDechargerCable() {
	super();
	// TODO Auto-generated constructor stub
}
public Long getCode() {
	return code;
}
public void setCode(Long code) {
	this.code = code;
}
public Users getAuteur() {
	return auteur;
}
public void setAuteur(Users auteur) {
	this.auteur = auteur;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public Cable getCables() {
	return cables;
}
public void setCables(Cable cables) {
	this.cables = cables;
}
public Chaine getChaine() {
	return chaine;
}
public void setChaine(Chaine chaine) {
	this.chaine = chaine;
}

}
