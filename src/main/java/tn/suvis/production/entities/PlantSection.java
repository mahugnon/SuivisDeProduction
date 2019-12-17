package tn.suvis.production.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

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
public class PlantSection implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String entreprise;
	private Date dateCreation;
	@ManyToOne
	@JoinColumn(name = "CODE_SMRT")
	private Users smrt;
	@OneToMany(mappedBy = "plantSection")
	private Collection<MMRT> mmrts;

	public PlantSection() {
		super();

	}

	public PlantSection(String nom, String entreprise, Date dateCreation) {
		super();
		this.nom = nom;
		this.entreprise = entreprise;
		this.dateCreation = dateCreation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@JsonIgnore
	public Users getSmrt() {
		return smrt;
	}

	@JsonSetter
	public void setSmrt(Users smrt) {
		this.smrt = smrt;
	}

	@JsonIgnore
	public Collection<MMRT> getMmrts() {
		return mmrts;
	}

	@JsonSetter
	public void setMmrts(Collection<MMRT> mmrts) {
		this.mmrts = mmrts;
	}

	@Override
	public String toString() {
		return "PlantSection [id=" + id + ", nom=" + nom + ", entreprise=" + entreprise + ", dateCreation="
				+ dateCreation + "]";
	}

}
