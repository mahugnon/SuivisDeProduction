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
public class MMRT implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	private String nom;
	private Date dateCreation;
	@ManyToOne
	@JoinColumn(name = "CODE_MMRT")
	private Users mmrt;
	@OneToMany(mappedBy = "mmrt")
	private Collection<Segment> segments = new ArrayList<Segment>();
	@ManyToOne
	@JoinColumn(name = "CODE_PLANT_SECTION")
	private PlantSection plantSection;

	public MMRT() {
		super();
	}

	public MMRT(String nom) {
		super();
		this.nom = nom;
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
	public Users getMmrt() {
		return mmrt;
	}

	@JsonSetter
	public void setMmrt(Users mmrt) {
		this.mmrt = mmrt;
	}

	@JsonIgnore
	public Collection<Segment> getSegments() {
		return segments;
	}

	@JsonSetter
	public void setSegments(Collection<Segment> segments) {
		this.segments = segments;
	}

	@JsonIgnore
	public PlantSection getPlantSection() {
		return plantSection;
	}

	@JsonSetter
	public void setPlantSection(PlantSection plantSection) {
		this.plantSection = plantSection;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Override
	public String toString() {
		return "MMRT [code=" + code + ", nom=" + nom + "]";
	}

}
