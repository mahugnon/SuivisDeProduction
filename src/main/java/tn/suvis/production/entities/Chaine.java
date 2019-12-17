package tn.suvis.production.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
@Entity
public class Chaine implements Serializable{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private Long idChaine;
private String nom;
private String description;
private int nombrePostTravail;
@ManyToOne
@JoinColumn(name="CODE_CHEF")
private Users chefChaine;
@ManyToOne
@JoinColumn(name="CODE_CONTRE_GROUPE")
private ContreGroupe contreGroupe;
public Chaine() {
	super();
	
}
public Chaine(String nom, String description, int nombrePostTravail) {
	super();
	this.nom = nom;
	this.description = description;
	this.nombrePostTravail = nombrePostTravail;
}
public Long getIdChaine() {
	return idChaine;
}
public void setIdChaine(Long idChaine) {
	this.idChaine = idChaine;
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
public int getNombrePostTravail() {
	return nombrePostTravail;
}
public void setNombrePostTravail(int nombrePostTravail) {
	this.nombrePostTravail = nombrePostTravail;
}
@JsonIgnore
public Users getChefChaine() {
	return chefChaine;
}
@JsonSetter
public void setChefChaine(Users chefChaine) {
	this.chefChaine = chefChaine;
}
public ContreGroupe getContreGroupe() {
	return contreGroupe;
}
public void setContreGroupe(ContreGroupe contreGroupe) {
	this.contreGroupe = contreGroupe;
}
@Override
public String toString() {
	return "Chaine [idChaine=" + idChaine + ", nom=" + nom + ", description=" + description + ", nombrePostTravail="
			+ nombrePostTravail + "]";
}





}
