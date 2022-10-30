package models.nfts;

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
        "title",
        "description",
        "filePath",
        "fileId",
        "tags",
        "categoryId",
        "senderWalletId",
        "ownerWalletId",
        "ownerId",
        "receiverWalletId",
        "nftId",
        "status",
        "transactionId",
        "created",
        "updated"
})

@Getter
@Setter
public class NftData {

    @JsonProperty("title")
    public String title;
    @JsonProperty("description")
    public String description;
    @JsonProperty("filePath")
    public String filePath;
    @JsonProperty("fileId")
    public String fileId;
    @JsonProperty("tags")
    public List<String> tags = null;
    @JsonProperty("categoryId")
    public String categoryId;
    @JsonProperty("senderWalletId")
    public String senderWalletId;
    @JsonProperty("ownerId")
    public String ownerId;
    @JsonProperty("ownerWalletId")
    public String ownerWalletId;
    @JsonProperty("receiverWalletId")
    public String receiverWalletId;
    @JsonProperty("nftId")
    public String nftId;
    @JsonProperty("status")
    public String status;
    @JsonProperty("transactionId")
    public String transactionId;
    @JsonProperty("created")
    public Long created;
    @JsonProperty("updated")
    public Long updated;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
}