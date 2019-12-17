package tn.suvis.production.model;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.springframework.security.core.GrantedAuthority;

import tn.suvis.production.entities.Role;
import tn.suvis.production.entities.Users;

public class SuivisProductionForm {
private String nomUtilisateur;
private Users user;
private Collection<? extends GrantedAuthority> roles;
private List<Users> listUser;
public SuivisProductionForm() {
	super();
	// TODO Auto-generated constructor stub
}

public String getNomUtilisateur() {
	return nomUtilisateur;
}

public void setNomUtilisateur(String nomUtilisateur) {
	this.nomUtilisateur = nomUtilisateur;
}



public Collection<? extends GrantedAuthority> getRoles() {
	return roles;
}

public void setRoles(Collection<? extends GrantedAuthority> collection) {
	this.roles = collection;
}

public List<Users> getListUser() {
	return listUser;
}

public void setListUser(List<Users> listUser) {
	this.listUser = listUser;
}

public Users getUser() {
	return user;
}

public void setUser(Users user) {
	this.user = user;
}



}
