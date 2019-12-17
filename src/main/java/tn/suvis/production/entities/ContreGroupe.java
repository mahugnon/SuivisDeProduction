package tn.suvis.production.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class ContreGroupe implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	private String nom;
	private Date dateCreation;
	@ManyToOne
	@JoinColumn(name = "CONTRE_MAITRE")
	private Users contreMaitre;
	@ManyToOne
	@JoinColumn(name = "CODE_SEGEMENT")
	private Segment segment;
	@OneToMany(mappedBy = "contreGroupe")
	private Collection<Chaine> chaines = new ArrayList<Chaine>();

	public ContreGroupe() {
		super();
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
@JsonIgnore
	public Users getContreMaitre() {
		return contreMaitre;
	}
@JsonSetter
	public void setContreMaitre(Users contreMaitre) {
		this.contreMaitre = contreMaitre;
	}
@JsonIgnore
	public Collection<Chaine> getChaines() {
		return chaines;
	}
@JsonSetter
	public void setChaines(Collection<Chaine> chaines) {
		this.chaines = chaines;
	}
@JsonIgnore
	public Segment getSegment() {
		return segment;
	}
@JsonSetter
	public void setSegment(Segment segment) {
		this.segment = segment;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Override
	public String toString() {
		return "ContreGroupe [code=" + code + ", nom=" + nom + ", dateCreation=" + dateCreation + "]";
	}

	
}
