package constants;

public class EndPoints {

    private EndPoints() {}

    // Endpoints of users
    public static final String CREATE_USER = "/users";
    public static final String GET_USER = "/users/{userId}";
    public static final String UPDATE_USER = "/users/{userId}";
    public static final String DELETE_USER = "/users/{userId}";
    public static final String LOGIN = "/users/login";
    public static final String VERIFY_LOGIN = "/users/login/verify";

    // Endpoints of nfts
    public static final String GET_ALL_NFTS_OF_AN_USER = "/nfts/list";
    public static final String CREATE_NFT = "/nfts";
    public static final String GET_SINGLE_NFT = "/nfts/{nftId}";
    public static final String UPDATE_NFT = "/nfts/update/{nftId}";
    public static final String DELETE_NFT = "/nfts/{nftId}";
    public static final String GIFT_NFT = "/nfts/{nftId}/gift";
    public static final String CLAIM_NFT = "/nfts/{nftId}/claim";

    // Endpoints of apps
    public static final String GET_ALL_APPS = "/apps";
    public static final String GET_APP = "/apps/{appId}";
    public static final String UPDATE_APP = "/apps/{appId}";
    public static final String DELETE_APP = "/apps/{appId}";
    public static final String CREATE_APP = "/apps";

    // Endpoints of app categories
    public static final String GET_ALL_APP_CATEGORIES = "/apps/categories/list";
    public static final String GET_APP_CATEGORY = "/apps/categories/{categoryId}";
    public static final String UPDATE_APP_CATEGORY = "/apps/categories/{categoryId}";
    public static final String DELETE_APP_CATEGORY = "/apps/categories/{categoryId}";
    public static final String CREATE_APP_CATEGORY = "/apps/categories";

    // Endpoints of files
    public static final String SHARE_FILE = "/files/share";
    public static final String ACCEPT_SHARE = "/files/acceptShare";
    public static final String ENCRYPT_FILE = "/files/encrypt";
    public static final String DECRYPT_FILE = "/files/decrypt";
    public static final String GRANT_ACCESS_PERMISSION = "/files/grantPermission";
    public static final String REVOKE_ACCESS_PERMISSION = "/files/revokePermission";
    public static final String LIST_FILE_ACCESS = "/files/listAccess"; // all users that have access to a file
    public static final String LIST_SHARED_FILES = "/files/sharedFiles"; // all the files shared with a userData
    public static final String LIST_FILES_SHARED = "/files/filesShared"; // all the files a userData has shared
    public static final String UPDATE_A_FILE = "/files/{fileId}";
    public static final String COPY_A_FILE = "/files/{fileId}/copy";
    public static final String CREATE_A_FILE = "/wallets/{walletId}/storage";
    public static final String GET_A_FILE = "/wallets/{walletId}/files/{fileId}";
    public static final String LIST_USER_FILES = "/files";
    public static final String DELETE_A_FILE = "/files/{fileId}";
    public static final String CREATE_FOLDER = "/wallets/{walletId}/storage/folders";

    // Endpoints of file hashing
    public static final String GENERATE_HASH = "/hashes";
    public static final String VERIFY_HASH = "/hashes/verify";

    // Endpoints of wallets
    public static final String CREATE_WALLET = "/wallets";
    public static final String FETCH_WALLETS_BY_USERID = "/wallets";
    public static final String VERIFY_WALLET = "/wallets/verify/{walletId}/{userId}";
    public static final String DELETE_WALLET = "/wallets/{walletId}";
    public static final String GET_WALLET = "/wallets/{walletId}";
    public static final String UPDATE_WALLET = "/wallets/{walletId}";

    // Endpoints of contacts
    public static final String CREATE_CONTACT = "/contacts/{userId}";
    public static final String UPDATE_CONTACT = "/contacts/{contactId}";
    public static final String GET_CONTACT = "/contacts/{contactId}";
    public static final String DELETE_CONTACT = "/contacts/{contactId}";
    public static final String IMPORT_CONTACTS = "/contacts/{userId}/import";
    public static final String GET_LIST_OF_CONTACTS = "/contacts/list";
    public static final String BLOCK_CONTACT = "/contacts/{contactId}/block";
    public static final String INVITE_CONTACT = "/contacts/{contactId}/invite";
    public static final String ADD_CONTACT_TO_FAVORITE_LIST = "/contacts/{contactId}/favorite";
    public static final String GET_CONTACT_PHOTO = "/contacts/{contactId}/photo";

}
