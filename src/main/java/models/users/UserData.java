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
        "userId",
        "walletName",
        "firstName",
        "lastName",
        "status",
        "email",
        "phone",
        "countryCode",
        "created",
        "isPhoneVerified",
        "isEmailVerified",
        "wallets",
        "contacts",
        "files",
        "nftCollections"
})
@Getter
@Setter
public class UserData {

    @JsonProperty("userId")
    public String userId;
    @JsonProperty("walletName")
    public String walletName;
    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("lastName")
    public String lastName;
    @JsonProperty("status")
    public String status;
    @JsonProperty("email")
    public String email;
    @JsonProperty("phone")
    public String phone;
    @JsonProperty("countryCode")
    public String countryCode;
    @JsonProperty("created")
    public String created;
    @JsonProperty("isPhoneVerified")
    public Boolean isPhoneVerified;
    @JsonProperty("isEmailVerified")
    public Boolean isEmailVerified;
    @JsonProperty("wallets")
    public List<Object> wallets = null;
    @JsonProperty("contacts")
    public List<Object> contacts = null;
    @JsonProperty("files")
    public List<Object> files = null;
    @JsonProperty("nftCollections")
    public List<Object> nftCollections = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
}