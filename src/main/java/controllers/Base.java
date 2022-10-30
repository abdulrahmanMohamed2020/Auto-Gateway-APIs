package controllers;

import utils.ConfigManager;
import core.RestAssuredHelper;

public class Base {

    private static final String BASE_URL = ConfigManager.getInstance().getString("baseUrl");
    protected static final RestAssuredHelper restAssuredHelper = new RestAssuredHelper(BASE_URL);

    protected Base() {}
}