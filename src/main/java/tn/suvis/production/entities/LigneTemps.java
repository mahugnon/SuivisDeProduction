package tn.suvis.production.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class LigneTemps implements Serializable{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private Long code;
private Date debutFonction;
private Date finFonction;
private String nomFonction;
private String datailFonction;
@ManyToOne
@JoinColumn(name="CODE_USER")
private Users user;
public LigneTemps() {
	super();
}
public Long getCode() {
	return code;
}
public void setCode(Long code) {
	this.code = code;
}
public Date getDebutFonction() {
	return debutFonction;
}
public void setDebutFonction(Date debutFonction) {
	this.debutFonction = debutFonction;
}
public Date getFinFonction() {
	return finFonction;
}
public void setFinFonction(Date finFonction) {
	this.finFonction = finFonction;
}
public String getNomFonction() {
	return nomFonction;
}
public void setNomFonction(String nomFonction) {
	this.nomFonction = nomFonction;
}
public String getDatailFonction() {
	return datailFonction;
}
public void setDatailFonction(String datailFonction) {
	this.datailFonction = datailFonction;
}
public Users getUser() {
	return user;
}
public void setUser(Users user) {
	this.user = user;
}


}
