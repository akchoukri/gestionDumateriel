 package com.ymagis.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="MATERIEL")
public class Materiel implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idMateriel;
	private String reference;
	private String designation;
	private Date dateAjoutMateriel;
	private boolean disponible;
	private String etatMateriel;
	private int quantite;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "ID_CAT")
	private Categorie categorie;
	
	
	 @ManyToMany(fetch = FetchType.LAZY,
	            cascade = {
	                CascadeType.PERSIST,
	                CascadeType.MERGE
	            },mappedBy = "materiels")
	 
	private List<Emprunt> emprunts;

	public Long getIdMateriel() {
		return idMateriel;
	}

	public void setIdMateriel(Long idMateriel) {
		this.idMateriel = idMateriel;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	
	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getDateAjoutMateriel() {
		return dateAjoutMateriel;
	}

	public void setDateAjoutMateriel(Date dateAjoutMateriel) {
		this.dateAjoutMateriel = dateAjoutMateriel;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getEtatMateriel() {
		return etatMateriel;
	}

	public void setEtatMateriel(String etatMateriel) {
		this.etatMateriel = etatMateriel;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Emprunt> getEmprunts() {
		return emprunts;
	}

	public void setEmprunts(List<Emprunt> emprunts) {
		this.emprunts = emprunts;
	}

	public Materiel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Materiel(String reference) {
		super();
		this.reference = reference;
	}

	public Materiel(String reference, String designation, Date dateAjoutMateriel, boolean disponible,
			String etatMateriel, int quantite) {
		super();
		this.reference = reference;
		this.designation = designation;
		this.dateAjoutMateriel = dateAjoutMateriel;
		this.disponible = disponible;
		this.etatMateriel = etatMateriel;
		this.quantite = quantite;
	}

	@Override
	public String toString() {
		return "Materiel [idMateriel=" + idMateriel + ", reference=" + reference + ", designation=" + designation
				+ ", dateAjoutMateriel=" + dateAjoutMateriel + ", disponible=" + disponible + ", etatMateriel="
				+ etatMateriel + ", quantite=" + quantite + "]";
	}


	
	
}
