
package com.cucumber.testng.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "full_text",
    "display_text_range",
    "entities"
})
public class ExtendedTweet {

    @JsonProperty("full_text")
    private String fullText;
    @JsonProperty("display_text_range")
    private List<Integer> displayTextRange = null;
    @JsonProperty("entities")
    private Entities entities;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("full_text")
    public String getFullText() {
        return fullText;
    }

    @JsonProperty("full_text")
    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    @JsonProperty("display_text_range")
    public List<Integer> getDisplayTextRange() {
        return displayTextRange;
    }

    @JsonProperty("display_text_range")
    public void setDisplayTextRange(List<Integer> displayTextRange) {
        this.displayTextRange = displayTextRange;
    }

    @JsonProperty("entities")
    public Entities getEntities() {
        return entities;
    }

    @JsonProperty("entities")
    public void setEntities(Entities entities) {
        this.entities = entities;
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
