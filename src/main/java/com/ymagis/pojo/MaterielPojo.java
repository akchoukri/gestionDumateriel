package com.ymagis.pojo;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ymagis.model.Categorie;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"idMateriel",
		"reference",
		"designation",
		"dateAjoutMateriel",
		"disponible",
		"etatMateriel",
		"quantite"
})
public class MaterielPojo {
	@JsonProperty("idMateriel")
	private Integer idMateriel;
	@JsonProperty("categorie")
	private Categorie categorie;
	@JsonProperty("reference")
	private String reference;
	@JsonProperty("designation")
	private String designation;
	@JsonProperty("dateAjoutMateriel")
	private Date dateAjoutMateriel;
	@JsonProperty("disponible")
	private Boolean disponible;
	@JsonProperty("etatMateriel")
	private String etatMateriel;
	@JsonProperty("quantite")
	private Integer quantite;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("idMateriel")
	public Integer getIdMateriel() {
		return idMateriel;
	}

	@JsonProperty("idMateriel")
	public void setIdMateriel(Integer idMateriel) {
		this.idMateriel = idMateriel;
	}

	@JsonProperty("categorie")
	public Categorie getCategorie() {
		return categorie;
	}

	@JsonProperty("categorie")
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	@JsonProperty("reference")
	public String getReference() {
		return reference;
	}

	@JsonProperty("reference")
	public void setReference(String reference) {
		this.reference = reference;
	}

	@JsonProperty("designation")
	public String getDesignation() {
		return designation;
	}

	@JsonProperty("designation")
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@JsonProperty("dateAjoutMateriel")
	public Date getDateAjoutMateriel() {
		return dateAjoutMateriel;
	}

	@JsonProperty("dateAjoutMateriel")
	public void setDateAjoutMateriel(Date dateAjoutMateriel) {
		this.dateAjoutMateriel = dateAjoutMateriel;
	}

	@JsonProperty("disponible")
	public Boolean isDisponible() {
		return disponible;
	}

	@JsonProperty("disponible")
	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	@JsonProperty("etatMateriel")
	public String getEtatMateriel() {
		return etatMateriel;
	}

	@JsonProperty("etatMateriel")
	public void setEtatMateriel(String etatMateriel) {
		this.etatMateriel = etatMateriel;
	}

	@JsonProperty("quantite")
	public Integer getQuantite() {
		return quantite;
	}

	@JsonProperty("quantite")
	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
}