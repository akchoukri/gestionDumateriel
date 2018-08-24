package com.ymagis.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPRUNT")
public class Emprunt implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EMPRUNT")
	private Long idEmprunt;
	private Date dateEmprunt;
	private Date dateRetourPrevu;
	private Date dateRetour;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "id_client")
	private Client client;

	@ManyToMany
	@JoinTable(name="emprunt_materiel",
	           joinColumns=@JoinColumn(name="idEmprunt"),
	           inverseJoinColumns=@JoinColumn(name="idMateriel")
	           )
	private List<Materiel> materiels;
	
	public Long getIdEmprunt() {
		return idEmprunt;
	}

	public void setIdEmprunt(Long idEmprunt) {
		this.idEmprunt = idEmprunt;
	}

	public Date getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public Date getDateRetourPrevu() {
		return dateRetourPrevu;
	}

	public void setDateRetourPrevu(Date dateRetourPrevu) {
		this.dateRetourPrevu = dateRetourPrevu;
	}

	public Date getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Emprunt(Date dateEmprunt, Date dateRetourPrevu, Date dateRetour) {
		super();
		this.dateEmprunt = dateEmprunt;
		this.dateRetourPrevu = dateRetourPrevu;
		this.dateRetour = dateRetour;
	}

	public Emprunt() {

	}

	@Override
	public String toString() {
		return "Emprunt [idEmprunt=" + idEmprunt + ", dateEmprunt=" + dateEmprunt + ", dateRetourPrevu="
				+ dateRetourPrevu + ", dateRetour=" + dateRetour + ", client=" + client + "]";
	}

}
