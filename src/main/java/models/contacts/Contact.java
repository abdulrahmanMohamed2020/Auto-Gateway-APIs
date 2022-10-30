package models.contacts;

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
        "message",
        "data"
})
@Getter
@Setter
public class Contact {

    @JsonProperty("message")
    public String message;
    @JsonProperty("data")
    public ContactData data;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
}