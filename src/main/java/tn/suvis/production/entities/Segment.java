package tn.suvis.production.entities;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Segment implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSegment;
	private String nom;
	@OneToMany(mappedBy = "segment")
	private Collection<ContreGroupe> contreGroupes = new ArrayList<ContreGroupe>();
	@OneToMany(mappedBy = "segment")
	private Collection<Post> posts;
	@ManyToOne
	@JoinColumn(name = "CODE_MMRT")
	private MMRT mmrt;
	@ManyToOne
	@JoinColumn(name = "CODE_BMRT")
	private Users bmrt;

	public Segment() {
		super();

	}

	public Segment(String nom) {
		super();
		this.nom = nom;
	}

	public Long getIdSegment() {
		return idSegment;
	}

	public void setIdSegment(Long idSegment) {
		this.idSegment = idSegment;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@JsonIgnore
	public Collection<Post> getPosts() {
		return posts;
	}

	@JsonSetter
	public void setPosts(Collection<Post> posts) {
		this.posts = posts;
	}

	@JsonIgnore
	public Users getBmrt() {
		return bmrt;
	}

	@JsonSetter
	public void setBmrt(Users bmrt) {
		this.bmrt = bmrt;
	}

	@JsonIgnore
	public Collection<ContreGroupe> getContreGroupe() {
		return contreGroupes;
	}

	@JsonSetter
	public void setContreGroupe(Collection<ContreGroupe> contreGroupes) {
		this.contreGroupes = contreGroupes;
	}

	@JsonIgnore
	public MMRT getMmrt() {
		return mmrt;
	}

	@JsonSetter
	public void setMmrt(MMRT mmrt) {
		this.mmrt = mmrt;
	}

	@Override
	public String toString() {
		return "Segment [idSegment=" + idSegment + ", nom=" + nom + "]";
	}

	

}
