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
        "address",
        "type"
})
@Getter
@Setter
public class Email {

    @JsonProperty("address")
    public String address;
    @JsonProperty("type")
    public String type;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
}