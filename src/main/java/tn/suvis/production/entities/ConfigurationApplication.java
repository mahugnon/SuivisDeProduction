package tn.suvis.production.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
@Entity
public class ConfigurationApplication implements Serializable{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private int escale1;
	private int escale2;
	private int escale3;
	private int escale4;
	private Date dateConfig;
	@OneToOne
	@JoinColumn(name="CODE_USER")
	private Users user;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getEscale1() {
		return escale1;
	}
	public void setEscale1(int escale1) {
		this.escale1 = escale1;
	}
	public int getEscale2() {
		return escale2;
	}
	public void setEscale2(int escale2) {
		this.escale2 = escale2;
	}
	public int getEscale3() {
		return escale3;
	}
	public void setEscale3(int escale3) {
		this.escale3 = escale3;
	}
	public Date getDateConfig() {
		return dateConfig;
	}
	public void setDateConfig(Date dateConfig) {
		this.dateConfig = dateConfig;
	}
	@JsonIgnore
	public Users getUser() {
		return user;
	}
	@JsonSetter
	public void setUser(Users user) {
		this.user = user;
	}
	public ConfigurationApplication() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getEscale4() {
		return escale4;
	}
	public void setEscale4(int escale4) {
		this.escale4 = escale4;
	}
	@Override
	public String toString() {
		return "ConfigurationApplication [id=" + id + ", escale1=" + escale1 + ", escale2=" + escale2 + ", escale3="
				+ escale3 + ", escale4=" + escale4 + ", dateConfig=" + dateConfig + ", user=" + user + "]";
	}
	
}
