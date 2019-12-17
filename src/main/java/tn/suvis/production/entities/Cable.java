package tn.suvis.production.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
@Entity
public class Cable implements Serializable{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long reference;
	private String tempsExecution;
	@ManyToOne
	@JoinColumn(name="CODE_CHAINE_RATTACH")
	private Chaine rattacherA;
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="CHAINE_POSSIBLE", joinColumns=@JoinColumn(name="REF_CABLE"),
	inverseJoinColumns=@JoinColumn(name="CODE_POSSIBLE_CHAINE"))
	private Collection<Chaine> possibleRattachement;
	@OneToMany(mappedBy="cable",fetch=FetchType.LAZY)
	private Collection<OpChargementCable> chargements;
	public Cable() {
		super();
	}
	public Cable(String tempsExecution) {
		super();
		this.tempsExecution = tempsExecution;
	}
	public Long getReference() {
		return reference;
	}
	public void setReference(Long reference) {
		this.reference = reference;
	}
	public String getTempsExecution() {
		return tempsExecution;
	}
	public void setTempsExecution(String tempsExecution) {
		this.tempsExecution = tempsExecution;
	}
	public Chaine getRattacherA() {
		return rattacherA;
	}
	public void setRattacherA(Chaine rattacherA) {
		this.rattacherA = rattacherA;
	}
	public Collection<Chaine> getPossibleRattachement() {
		return possibleRattachement;
	}
	public void setPossibleRattachement(Collection<Chaine> possibleRattachement) {
		this.possibleRattachement = possibleRattachement;
	}
	@JsonIgnore
	public Collection<OpChargementCable> getChargements() {
		return chargements;
	}
	@JsonSetter
	public void setChargements(Collection<OpChargementCable> chargements) {
		this.chargements = chargements;
	}
	
	
	

}
