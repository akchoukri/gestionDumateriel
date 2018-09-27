package com.ymagis.pojo;
import java.util.HashMap;
        import java.util.Map;
        import com.fasterxml.jackson.annotation.JsonAnyGetter;
        import com.fasterxml.jackson.annotation.JsonAnySetter;
        import com.fasterxml.jackson.annotation.JsonIgnore;
        import com.fasterxml.jackson.annotation.JsonInclude;
        import com.fasterxml.jackson.annotation.JsonProperty;
        import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "idCategorie",
        "nomCategorie"
})
public class CategoryPojo {

    @JsonProperty("idCategorie")
    private Integer idCategorie;
    @JsonProperty("nomCategorie")
    private String nomCategorie;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("idCategorie")
    public Integer getIdCategorie() {
        return idCategorie;
    }

    @JsonProperty("idCategorie")
    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    @JsonProperty("nomCategorie")
    public String getNomCategorie() {
        return nomCategorie;
    }

    @JsonProperty("nomCategorie")
    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
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