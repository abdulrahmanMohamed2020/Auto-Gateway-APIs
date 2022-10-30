package models.files;

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
        "encrypted",
        "md5"
})
@Getter
@Setter
public class DataEncryptionKey {

    @JsonProperty("encrypted")
    public String encrypted;
    @JsonProperty("md5")
    public String md5;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
}