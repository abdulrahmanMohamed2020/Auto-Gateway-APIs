package controllers.headers;

import java.util.HashMap;
import java.util.Map;

public class HeaderProvider {

    private HeaderProvider() {}

    public static Map<String, String> bearerToken(String userBearerToken) {

        Map<String,String> token = new HashMap<>();
        token.put("Authorization", userBearerToken);

        return token;
    }
}
