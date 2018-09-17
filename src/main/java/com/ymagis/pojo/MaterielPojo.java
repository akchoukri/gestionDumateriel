package com.ymagis.pojo;

import java.util.Date;

public class MaterielPojo{

	private Long idMateriel;
	private String reference;
	private String designation;
	private Date dateAjoutMateriel;
	private boolean disponible;
	private String etatMateriel;
	private int quantite;
	private String nomCategorie;
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
	public String getNomCategorie() {
		return nomCategorie;
	}
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
	public MaterielPojo(String reference, String designation, Date dateAjoutMateriel,
			boolean disponible, String etatMateriel, int quantite, String nomCategorie) {
		super();
		this.reference = reference;
		this.designation = designation;
		this.dateAjoutMateriel = dateAjoutMateriel;
		this.disponible = disponible;
		this.etatMateriel = etatMateriel;
		this.quantite = quantite;
		this.nomCategorie = nomCategorie;
	}
	
}
