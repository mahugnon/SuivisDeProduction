package tn.suvis.production.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Mesure {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idTrame;
 private Long idSegment;
 private String post;
 private Long idChaine;
 private int etatArret;
 private String type;
 private int tArret;
 private double qteProduite;
 private Date date;
 private String x;
 private String y;
 private String z;
public Mesure() {
	super();
}

public Mesure( Long idSegment, String post, Long idChaine, int etatArret, String type, int tArret,
		double qteProduite, Date date) {
	super();
	this.idSegment = idSegment;
	this.post = post;
	this.idChaine = idChaine;
	this.etatArret = etatArret;
	this.type = type;
	this.tArret = tArret;
	this.qteProduite = qteProduite;
	this.date = date;
}

public Long getIdTrame() {
	return idTrame;
}
public void setIdTrame(Long idTrame) {
	this.idTrame = idTrame;
}
public Long getIdSegment() {
	return idSegment;
}
public void setIdSegment(Long idSegment) {
	this.idSegment = idSegment;
}
public String getPost() {
	return post;
}
public void setPost(String post) {
	this.post = post;
}
public Long getIdChaine() {
	return idChaine;
}
public void setIdChaine(Long idChaine) {
	this.idChaine = idChaine;
}

public int getEtatArret() {
	return etatArret;
}
public void setEtatArret(int etatArret) {
	this.etatArret = etatArret;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public int gettArret() {
	return tArret;
}
public void settArret(int tArret) {
	this.tArret = tArret;
}
public double getQteProduite() {
	return qteProduite;
}
public void setQteProduite(double qteProduite) {
	this.qteProduite = qteProduite;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getX() {
	return x;
}
public void setX(String x) {
	this.x = x;
}
public String getY() {
	return y;
}
public void setY(String y) {
	this.y = y;
}
public String getZ() {
	return z;
}
public void setZ(String z) {
	this.z = z;
}
@Override
public String toString() {
	return "Mesure [idTrame=" + idTrame + ", idSegment=" + idSegment + ", post=" + post + ", idChaine=" + idChaine
			+ ", etatArret=" + etatArret + ", type=" + type + ", tArret=" + tArret + ", qteProduite=" + qteProduite
			+ ", date=" + date + "]";
}

 
 
}
