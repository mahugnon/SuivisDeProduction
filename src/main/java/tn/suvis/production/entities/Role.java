package tn.suvis.production.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Role implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idRole;
	private String roleName;
	@ManyToMany(mappedBy="roles")
	private Collection<Users> users= new ArrayList<Users>();
	
	public Role() {
		super();
	}
	public Long getIdRole() {
		return idRole;
	}
	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@JsonIgnore
	public Collection<Users> getUsers() {
		return users;
	}
	@JsonSetter
	public void setUsers(Collection<Users> users) {
		this.users = users;
	}
	@Override
	public String toString() {
		return  roleName ;
	}

	
	
}

