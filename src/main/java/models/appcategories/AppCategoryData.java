package models.appcategories;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "categoryId",
        "name",
        "description",
        "active",
        "created",
        "updated"
})
@Getter
@Setter
public class AppCategoryData {

    @JsonProperty("categoryId")
    public String categoryId;
    @JsonProperty("name")
    public String name;
    @JsonProperty("description")
    public String description;
    @JsonProperty("active")
    public Boolean active;
    @JsonProperty("created")
    public Long created;
    @JsonProperty("updated")
    public Long updated;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
}