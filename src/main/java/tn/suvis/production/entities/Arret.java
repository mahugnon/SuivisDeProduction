package tn.suvis.production.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Arret  implements Serializable{
@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id ;
	private Date debut;
	private Date fin;
	private Long idSegment;
	private Long idChaine;
	private String status;
	private String raison;
	public Arret() {
		super();
	}
	
	
	public Arret(Long id, Date debut, Date fin, Long idSegment, Long idChaine, String status, String raison) {
		super();
		this.id = id;
		this.debut = debut;
		this.fin = fin;
		this.idSegment = idSegment;
		this.idChaine = idChaine;
		this.status = status;
		this.raison = raison;
	}


	public Arret(Date debut, Date fin, Long idSegment, Long idChaine, String status, String raison) {
		super();
	
		this.debut = debut;
		this.fin = fin;
		this.idSegment = idSegment;
		this.idChaine = idChaine;
		this.status = status;
		this.raison = raison;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDebut() {
		return debut;
	}
	public void setDebut(Date debut) {
		this.debut = debut;
	}
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}
	public Long getIdSegment() {
		return idSegment;
	}
	public void setIdSegment(Long idSegment) {
		this.idSegment = idSegment;
	}
	public Long getIdChaine() {
		return idChaine;
	}
	public void setIdChaine(Long idChaine) {
		this.idChaine = idChaine;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRaison() {
		return raison;
	}
	public void setRaison(String raison) {
		this.raison = raison;
	}

	@Override
	public String toString() {
		return "Arret [id=" + id + ", debut=" + debut + ", fin=" + fin + ", idSegment=" + idSegment + ", idChaine="
				+ idChaine + ", status=" + status + ", raison=" + raison + "]";
	}
	
}
