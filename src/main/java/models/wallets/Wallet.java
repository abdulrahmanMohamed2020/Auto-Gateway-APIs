package models.wallets;

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
        "status",
        "created",
        "walletId",
        "transactionId",
        "isBlockchainVerified",
        "kycProvider",
        "balance",
        "publicKey",
        "updated",
        "walletName",
        "storageProvider",
        "userId",
        "blockchainHash"
})

@Getter
@Setter
public class Wallet {

    @JsonProperty("status")
    public String status;
    @JsonProperty("created")
    public String created;
    @JsonProperty("walletId")
    public String walletId;
    @JsonProperty("transactionId")
    public String transactionId;
    @JsonProperty("isBlockchainVerified")
    public String isBlockchainVerified;
    @JsonProperty("kycProvider")
    public String kycProvider;
    @JsonProperty("balance")
    public String balance;
    @JsonProperty("publicKey")
    public String publicKey;
    @JsonProperty("updated")
    public String updated;
    @JsonProperty("walletName")
    public String walletName;
    @JsonProperty("storageProvider")
    public String storageProvider;
    @JsonProperty("userId")
    public String userId;
    @JsonProperty("blockchainHash")
    public String blockchainHash;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
}