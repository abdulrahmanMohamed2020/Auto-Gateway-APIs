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
        "version",
        "path",
        "created",
        "folderId",
        "dataEncryptionKey",
        "walletId",
        "name",
        "ownerId",
        "updated",
        "storageProvider",
        "fileId",
        "userId",
        "description",
        "url",
        "publicUrl"
})
@Getter
@Setter
public class File {

    @JsonProperty("version")
    public String version;
    @JsonProperty("path")
    public String path;
    @JsonProperty("folderId")
    public String folderId;
    @JsonProperty("created")
    public String created;
    @JsonProperty("dataEncryptionKey")
    public DataEncryptionKey dataEncryptionKey;
    @JsonProperty("walletId")
    public String walletId;
    @JsonProperty("name")
    public String name;
    @JsonProperty("ownerId")
    public String ownerId;
    @JsonProperty("updated")
    public String updated;
    @JsonProperty("storageProvider")
    public String storageProvider;
    @JsonProperty("fileId")
    public String fileId;
    @JsonProperty("userId")
    public String userId;
    @JsonProperty("description")
    public String description;
    @JsonProperty("url")
    public String url;
    @JsonProperty("publicUrl")
    public String publicUrl;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
}