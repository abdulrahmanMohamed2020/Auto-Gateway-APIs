package models.users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "errors"
})
@Getter
@Setter
public class ErrorResponseBody {

    @JsonProperty("errors")
    public List<Error> errors = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
}