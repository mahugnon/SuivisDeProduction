package tn.suvis.production.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class OpChargementCable implements Serializable{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private Long code;
	@ManyToOne
	@JoinColumn(name="CODE_AUTHEUR")
private Users autheurChargement;
	@ManyToOne
	@JoinColumn(name="CODE_CHAINE")
private Chaine chaine;
	@ManyToOne
	@JoinColumn(name="CODE_CABLE")
private Cable cable;
public OpChargementCable() {
	super();
}
public Long getCode() {
	return code;
}
public void setCode(Long code) {
	this.code = code;
}
public Users getAutheurChargement() {
	return autheurChargement;
}
public void setAutheurChargement(Users autheurChargement) {
	this.autheurChargement = autheurChargement;
}
public Chaine getChaine() {
	return chaine;
}
public void setChaine(Chaine chaine) {
	this.chaine = chaine;
}
public Cable getCable() {
	return cable;
}
public void setCable(Cable cable) {
	this.cable = cable;
}

}
