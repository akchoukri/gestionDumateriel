package com.ymagis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIE")
public class Categorie implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CAT")
	private int idCategorie;
	@Column(name = "NOM_CAT")
	private String nomCategorie;

	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	public Categorie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Categorie(String nomCategorie) {
		super();
		this.nomCategorie = nomCategorie;
	}

}
