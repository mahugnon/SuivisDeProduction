package tn.suvis.production.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Production  implements Serializable{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private Long idProduction;
private Date date;
private Long idSegment;
private Long idContreGroupe;
private Long idmmrt;
private Long idChaine;
private String post;
private double qteProduite;
public Production() {
	super();
}
public Production(Date date, Long idSegment, Long idChaine, double qteProduite,String post) {
	super();
	this.post=post;
	this.date = date;
	this.idSegment = idSegment;
	this.idChaine = idChaine;
	this.qteProduite = qteProduite;
}
public Long getIdProduction() {
	return idProduction;
}
public void setIdProduction(Long idProduction) {
	this.idProduction = idProduction;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public Long getIdSegment() {
	return idSegment;
}
public void setIdSegment(Long idSegment) {
	this.idSegment = idSegment;
}
public Long getIdChaine() {
	return idChaine;
}
public void setIdChaine(Long idChaine) {
	this.idChaine = idChaine;
}
public double getQteProduite() {
	return qteProduite;
}
public void setQteProduite(double qteProduite) {
	this.qteProduite = qteProduite;
}

public String getPost() {
	return post;
}
public void setPost(String post) {
	this.post = post;
}
public Long getIdContreGroupe() {
	return idContreGroupe;
}
public void setIdContreGroupe(Long idContreGroupe) {
	this.idContreGroupe = idContreGroupe;
}
public Long getIdmmrt() {
	return idmmrt;
}
public void setIdmmrt(Long idmmrt) {
	this.idmmrt = idmmrt;
}


}
