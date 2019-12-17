package tn.suvis.production.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@Table(name = "users")
public class Users implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long idUser;
	private String firstName;
	private String lastName;
	private Date debutEmploi;
	private Date finEmploi;
	@Column(name = "user_name")
	private String userName;
	private String password;

	private boolean actived;

	@Column(name = "sexe")
	private String sexe;

	@Column(name = "date_de_naissance")
	private Date dateDeNaissance;

	@Column(name = "email")
	private String adresseMail;

	private String adresse;
	private String telephone;
	@Lob
	private byte[] photo;
	private boolean firsTime;
	@ManyToMany
	@JoinTable(name = "user_role")
	private Collection<Role> roles = new ArrayList<Role>();
	@OneToMany(mappedBy = "emetteur")
	private Collection<Message> messageEmit;
	@ManyToMany(mappedBy = "recepteurs")
	private Collection<Message> messageRecu;
	@ManyToMany(mappedBy = "estVuPar")
	private Collection<Message> messageVu;
	@OneToMany(mappedBy = "users")
	private Collection<Alert> alerts;

	@OneToMany(mappedBy = "users")
	private Collection<Post> posts;
	@OneToMany(mappedBy = "chefChaine")
	private Collection<Chaine> chaines = new ArrayList<Chaine>();
	@OneToMany(mappedBy = "smrt")
	private Collection<PlantSection> plantSections = new ArrayList<PlantSection>();
	@OneToMany(mappedBy="mmrt")
	private Collection<MMRT> mmrts = new ArrayList<MMRT>();
	@OneToMany(mappedBy = "bmrt")
	private Collection<Segment> segmentBmrt = new ArrayList<Segment>();
	@OneToMany(mappedBy="contreMaitre")
	private Collection<ContreGroupe> contreGroupes = new ArrayList<ContreGroupe>();

	@JsonIgnore
	public Collection<Alert> getAlerts() {
		return alerts;
	}

	public void setAlerts(Collection<Alert> alerts) {
		this.alerts = alerts;
	}

	@JsonIgnore
	public Collection<Message> getMessageEmit() {
		return messageEmit;
	}

	@JsonSetter
	public void setMessageEmit(Collection<Message> messageEmit) {
		this.messageEmit = messageEmit;
	}

	@JsonIgnore
	public Collection<Message> getMessageRecu() {
		return messageRecu;
	}

	@JsonSetter
	public void setMessageRecu(Collection<Message> messageRecu) {
		this.messageRecu = messageRecu;
	}

	@JsonIgnore
	public Collection<Message> getMessageVu() {
		return messageVu;
	}

	@JsonSetter
	public void setMessageVu(Collection<Message> messageVu) {
		this.messageVu = messageVu;
	}

	public Users() {
		super();

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAdresseMail() {
		return adresseMail;
	}

	public void setAdresseMail(String adresseMail) {
		this.adresseMail = adresseMail;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	@JsonSetter
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public boolean isFirsTime() {
		return firsTime;
	}

	public void setFirsTime(boolean firsTime) {
		this.firsTime = firsTime;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@JsonIgnore
	public Collection<Post> getPosts() {
		return posts;
	}

	@JsonSetter
	public void setPost(Collection<Post> posts) {
		this.posts = posts;
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
	public Collection<PlantSection> getPlantSections() {
		return plantSections;
	}

	@JsonSetter
	public void setPlantSections(Collection<PlantSection> plantSections) {
		this.plantSections = plantSections;
	}

	public Date getDebutEmploi() {
		return debutEmploi;
	}

	public void setDebutEmploi(Date debutEmploi) {
		this.debutEmploi = debutEmploi;
	}

	public Date getFinEmploi() {
		return finEmploi;
	}

	public void setFinEmploi(Date finEmploi) {
		this.finEmploi = finEmploi;
	}

	@JsonSetter
	public void setPosts(Collection<Post> posts) {
		this.posts = posts;
	}
@JsonIgnore
	public Collection<MMRT> getMmrts() {
		return mmrts;
	}
@JsonSetter
	public void setMmrts(Collection<MMRT> mmrts) {
		this.mmrts = mmrts;
	}
@JsonIgnore
	public Collection<Segment> getSegmentBmrt() {
		return segmentBmrt;
	}
@JsonSetter
	public void setSegmentBmrt(Collection<Segment> segmentBmrt) {
		this.segmentBmrt = segmentBmrt;
	}
@JsonIgnore
	public Collection<ContreGroupe> getContreGroupes() {
		return contreGroupes;
	}
@JsonSetter
	public void setContreGroupes(Collection<ContreGroupe> contreGroupes) {
		this.contreGroupes = contreGroupes;
	}

	@Override
	public String toString() {
		return "Users [idUser=" + idUser + ", firstName=" + firstName + ", lastName=" + lastName + ", debutEmploi="
				+ debutEmploi + ", finEmploi=" + finEmploi + ", userName=" + userName + ", password=" + password
				+ ", actived=" + actived + ", sexe=" + sexe + ", dateDeNaissance=" + dateDeNaissance + ", adresseMail="
				+ adresseMail + ", adresse=" + adresse + ", telephone=" + telephone +  ", firsTime=" + firsTime + ", roles=" + roles + "]";
	}

	
}