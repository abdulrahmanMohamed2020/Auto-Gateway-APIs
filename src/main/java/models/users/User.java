package models.users;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "jwtAccessToken",
        "jwtRefreshToken",
        "user"
})
@Getter
@Setter
@ToString
public class User {

    @JsonProperty("jwtAccessToken")
    public String jwtAccessToken;
    @JsonProperty("jwtRefreshToken")
    public String jwtRefreshToken;
    @JsonProperty("user")
    public UserData userData;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
}