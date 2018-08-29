package com.ymagis.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENTS")
public class Client implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idClient;
	@Column(name = "NOM")
	private String nomClient;
	@Column(name = "PRENOM")
	private String prenomClient;
	@Column(name = "ADRESS")
	private String adressClient;
	@Column(name = "EMAIL")
	private String emailClient;
	@Column(name = "TEL")
	private String telClient;
	@Column(name = "DATE_NAISSANCE")
	private Date dateNaissanceClient;

	@OneToMany(mappedBy = "client", fetch=FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	private List<Emprunt> emprunts;

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getPrenomClient() {
		return prenomClient;
	}

	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}

	public String getAdressClient() {
		return adressClient;
	}

	public void setAdressClient(String adressClient) {
		this.adressClient = adressClient;
	}

	public String getEmailClient() {
		return emailClient;
	}

	public void setEmailClient(String emailClient) {
		this.emailClient = emailClient;
	}

	public Date getDateNaissanceClient() {
		return dateNaissanceClient;
	}

	public void setDateNaissanceClient(Date dateNaissanceClient) {
		this.dateNaissanceClient = dateNaissanceClient;
	}

	public String getTelClient() {
		return telClient;
	}

	public void setTelClient(String telClient) {
		this.telClient = telClient;
	}

	public List<Emprunt> getEmprunts() {
		return emprunts;
	}

	public void setEmprunts(List<Emprunt> emprunts) {
		this.emprunts = emprunts;
	}

	public Client() {

	}

	public Client(String nomClient, String prenomClient, String adressClient, String emailClient, String telClient,
			Date dateNaissanceClient) {
		super();
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.adressClient = adressClient;
		this.emailClient = emailClient;
		this.telClient = telClient;
		this.dateNaissanceClient = dateNaissanceClient;
	}

	public Client(String nomClient) {
		super();
		this.nomClient = nomClient;
	}

}
