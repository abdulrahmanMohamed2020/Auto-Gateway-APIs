package datageneration;

import com.github.javafaker.Faker;
import models.users.UserData;

public class UserDataGeneration {

    private UserDataGeneration() {}

    private static final Faker faker = new Faker();

    public static UserData createFullUserData() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("?????????"));
        userData.setLastName(faker.bothify("?????????"));
        userData.setWalletName(faker.bothify("????????###.testnet"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataWithMissingFirstName() {
        UserData userData = new UserData();
        userData.setLastName(faker.bothify("?????????"));
        userData.setWalletName(faker.bothify("????????###.testnet"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataWithFirstNameMoreThan50Letter() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("???????????????????????????????????????????????????"));
        userData.setLastName(faker.bothify("?????????"));
        userData.setWalletName(faker.bothify("????????###.testnet"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataWithEmptyFirstName() {
        UserData userData = new UserData();
        userData.setFirstName("");
        userData.setLastName(faker.bothify("?????????"));
        userData.setWalletName(faker.bothify("????????###.testnet"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataWithFirstNameHasSpaces() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("????? ?????"));
        userData.setLastName(faker.bothify("?????????"));
        userData.setWalletName(faker.bothify("????????###.testnet"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataWithFirstNameHasHyphen() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("?????-?????"));
        userData.setLastName(faker.bothify("?????????"));
        userData.setWalletName(faker.bothify("????????###.testnet"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataWithLastNameMoreThan50Letter() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("?????????"));
        userData.setLastName(faker.bothify("???????????????????????????????????????????????????"));
        userData.setWalletName(faker.bothify("????????###.testnet"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataWithEmptyLastName() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("?????????"));
        userData.setLastName("");
        userData.setWalletName(faker.bothify("????????###.testnet"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataWithLastNameHasSpaces() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("??????????"));
        userData.setLastName(faker.bothify("?????? ????"));
        userData.setWalletName(faker.bothify("????????###.testnet"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataWithLastNameHasHyphen() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("??????????"));
        userData.setLastName(faker.bothify("?????-????"));
        userData.setWalletName(faker.bothify("????????###.testnet"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataWithWalletNameMoreThan50Letter() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("?????????"));
        userData.setLastName(faker.bothify("?????????"));
        userData.setWalletName(faker.bothify("??????????????????????????????????????????????.testnet"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataWithEmptyWalletName() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("?????????"));
        userData.setLastName(faker.bothify("?????????"));
        userData.setWalletName("");
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataWithWalletNameHasSpaces() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("??????????"));
        userData.setLastName(faker.bothify("??????????"));
        userData.setWalletName(faker.bothify("????? ???###.testnet"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataWithWalletNameUsedBefore(String walletName) {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("??????????"));
        userData.setLastName(faker.bothify("??????????"));
        userData.setWalletName(walletName);
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataWithWalletNameDoesNotEndWithCorrectSuffix() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("??????????"));
        userData.setLastName(faker.bothify("??????????"));
        userData.setWalletName(faker.bothify("????????###"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataWithInvalidEmailFormat() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("?????????"));
        userData.setLastName(faker.bothify("?????????"));
        userData.setWalletName(faker.bothify("????????###.testnet"));
        userData.setEmail(faker.bothify("?????##test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataWithEmailUsedBefore(String email) {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("??????????"));
        userData.setLastName(faker.bothify("??????????"));
        userData.setWalletName(faker.bothify("????????###.testnet"));
        userData.setEmail(email);
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataWithMissingEmailAndPhone() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("??????????"));
        userData.setLastName(faker.bothify("?????????"));
        userData.setWalletName(faker.bothify("????????###.testnet"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataWithPhoneUsedBefore(String phone) {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("??????????"));
        userData.setLastName(faker.bothify("??????????"));
        userData.setWalletName(faker.bothify("????????###.testnet"));
        userData.setEmail(faker.bothify("??????##@test.com"));
        userData.setPhone(phone);
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataWithCountryCodeMoreThan4Letters() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("?????????"));
        userData.setLastName(faker.bothify("?????????"));
        userData.setWalletName(faker.bothify("????????###.testnet"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+12345");

        return userData;
    }

    public static UserData createUserDataWithCountryCodeLessThan2Letters() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("?????????"));
        userData.setLastName(faker.bothify("?????????"));
        userData.setWalletName(faker.bothify("????????###.testnet"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+");

        return userData;
    }

    public static UserData createUserDataWithCountryCodeDoesNotStartWithCorrectPrefix() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("?????????"));
        userData.setLastName(faker.bothify("?????????"));
        userData.setWalletName(faker.bothify("????????###.testnet"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("12");

        return userData;
    }

    public static UserData createUserDataWithoutCountryCode() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("?????????"));
        userData.setLastName(faker.bothify("?????????"));
        userData.setWalletName(faker.bothify("????????###.testnet"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));

        return userData;
    }

    public static UserData createUserDataWithPhoneLessThan10Numbers() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("??????????"));
        userData.setLastName(faker.bothify("?????????"));
        userData.setWalletName(faker.bothify("????????###.testnet"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("#########"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataWithPhoneMoreThan15Number() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("??????????"));
        userData.setLastName(faker.bothify("?????????"));
        userData.setWalletName(faker.bothify("????????###.testnet"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("################"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataWithPhoneEquals15Number() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("??????????"));
        userData.setLastName(faker.bothify("?????????"));
        userData.setWalletName(faker.bothify("????????###.testnet"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("###############"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataWithPhoneEquals10Numbers() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("??????????"));
        userData.setLastName(faker.bothify("?????????"));
        userData.setWalletName(faker.bothify("????????###.testnet"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataForUpdate() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("?????????"));
        userData.setLastName(faker.bothify("?????????"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+2");

        return userData;
    }
}
