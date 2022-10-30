package datageneration;

import com.github.javafaker.Faker;
import models.contacts.ContactJSON;
import models.contacts.Email;
import models.contacts.Phone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactDataGeneration {

    private ContactDataGeneration() {}
    private static final Faker faker = new Faker();

    public static ContactJSON createContact() {
        ContactJSON contactJSON = new ContactJSON();
        contactJSON.setFirstName(faker.letterify("????????????"));
        contactJSON.setLastName(faker.letterify("?????????????"));
        contactJSON.setContactStatus("invited");
        contactJSON.setJobTitle(faker.letterify("????????? ???????"));

        Email email = new Email();
        email.setAddress(faker.bothify("?????###@test.com"));
        email.setType("corporative");

        Phone phone = new Phone();
        phone.setNumber(faker.bothify("+##########"));
        phone.setType("mobile");

        contactJSON.setPhone(Collections.singletonList(phone));
        contactJSON.setEmail(Collections.singletonList(email));

        return contactJSON;
    }

    public static List<ContactJSON> importContacts(int numberOfContacts) {
        List<ContactJSON> contactJSONList = new ArrayList<>();

        for (int i=0 ; i<numberOfContacts ; i++) {
            ContactJSON contactJSON = new ContactJSON();

            Phone phone = new Phone();
            Email email = new Email();

            contactJSON.setFirstName(faker.letterify("????????????"));
            contactJSON.setLastName(faker.letterify("?????????????"));
            contactJSON.setContactStatus("invited");
            contactJSON.setJobTitle(faker.letterify("????????? ???????"));

            email.setAddress(faker.bothify("?????###@test.com"));
            email.setType("corporative");

            phone.setNumber(faker.bothify("+##########"));
            phone.setType("mobile");

            contactJSON.setPhone(Collections.singletonList(phone));
            contactJSON.setEmail(Collections.singletonList(email));

            contactJSONList.add(contactJSON);
        }
        return contactJSONList;
    }

    public static ContactJSON createContactWithFirstNameMoreThan26Character() {
        ContactJSON contactJSON = new ContactJSON();
        contactJSON.setFirstName(faker.letterify("???????????????????????????"));
        contactJSON.setLastName(faker.letterify("?????????????"));
        contactJSON.setContactStatus("invited");
        contactJSON.setJobTitle(faker.letterify("????????? ???????"));

        return contactJSON;
    }

    public static ContactJSON createContactWithFirstNameLessThan2Character() {
        ContactJSON contactJSON = new ContactJSON();
        contactJSON.setFirstName(faker.letterify("?"));
        contactJSON.setLastName(faker.letterify("?????????????"));
        contactJSON.setContactStatus("invited");
        contactJSON.setJobTitle(faker.letterify("????????? ???????"));

        return contactJSON;
    }

    public static ContactJSON createContactWithFirstNameInNumbers() {
        ContactJSON contactJSON = new ContactJSON();
        contactJSON.setFirstName(faker.letterify("#######"));
        contactJSON.setLastName(faker.letterify("?????????????"));
        contactJSON.setContactStatus("invited");
        contactJSON.setJobTitle(faker.letterify("????????? ???????"));

        return contactJSON;
    }

    public static ContactJSON createContactWithFirstNameAsSpaces() {
        ContactJSON contactJSON = new ContactJSON();
        contactJSON.setFirstName("   ");
        contactJSON.setLastName(faker.letterify("?????????????"));
        contactJSON.setContactStatus("invited");
        contactJSON.setJobTitle(faker.letterify("????????? ???????"));

        return contactJSON;
    }

    public static ContactJSON createContactWithEmptyFirstName() {
        ContactJSON contactJSON = new ContactJSON();
        contactJSON.setFirstName("");
        contactJSON.setLastName(faker.letterify("?????????????"));
        contactJSON.setContactStatus("invited");
        contactJSON.setJobTitle(faker.letterify("????????? ???????"));

        return contactJSON;
    }

    public static ContactJSON createContactWithLastNameMoreThan26Character() {
        ContactJSON contactJSON = new ContactJSON();
        contactJSON.setFirstName(faker.letterify("????????????"));
        contactJSON.setLastName(faker.letterify("???????????????????????????"));
        contactJSON.setContactStatus("invited");
        contactJSON.setJobTitle(faker.letterify("????????? ???????"));

        return contactJSON;
    }

    public static ContactJSON createContactWithLastNameLessThan2Character() {
        ContactJSON contactJSON = new ContactJSON();
        contactJSON.setFirstName(faker.letterify("????????"));
        contactJSON.setLastName(faker.letterify("?"));
        contactJSON.setContactStatus("invited");
        contactJSON.setJobTitle(faker.letterify("????????? ???????"));

        return contactJSON;
    }

    public static ContactJSON createContactWithLastNameInNumbers() {
        ContactJSON contactJSON = new ContactJSON();
        contactJSON.setFirstName(faker.letterify("????????"));
        contactJSON.setLastName(faker.letterify("#######"));
        contactJSON.setContactStatus("invited");
        contactJSON.setJobTitle(faker.letterify("????????? ???????"));

        return contactJSON;
    }

    public static ContactJSON createContactWithLastNameAsSpaces() {
        ContactJSON contactJSON = new ContactJSON();
        contactJSON.setFirstName(faker.letterify("?????????????"));
        contactJSON.setLastName("  ");
        contactJSON.setContactStatus("invited");
        contactJSON.setJobTitle(faker.letterify("????????? ???????"));

        return contactJSON;
    }

    public static ContactJSON createContactWithEmptyLastName() {
        ContactJSON contactJSON = new ContactJSON();
        contactJSON.setFirstName(faker.letterify("?????????????"));
        contactJSON.setLastName("");
        contactJSON.setContactStatus("invited");
        contactJSON.setJobTitle(faker.letterify("????????? ???????"));

        return contactJSON;
    }

    public static ContactJSON createContactWithoutFirstName() {
        ContactJSON contactJSON = new ContactJSON();
        contactJSON.setLastName(faker.letterify("?????????????"));
        contactJSON.setContactStatus("invited");
        contactJSON.setJobTitle(faker.letterify("????????? ???????"));

        return contactJSON;
    }

    public static ContactJSON createContactWithInvalidEmailFormat() {
        ContactJSON contactJSON = new ContactJSON();
        contactJSON.setFirstName(faker.letterify("???????"));
        contactJSON.setLastName(faker.letterify("????????"));
        contactJSON.setContactStatus("invited");
        contactJSON.setJobTitle(faker.letterify("????????? ???????"));

        Email email = new Email();
        email.setAddress(faker.bothify("????###test.com"));
        email.setType("corporative");

        contactJSON.setEmail(Collections.singletonList(email));

        return contactJSON;
    }

    public static ContactJSON createContactWithEmptyEmail() {
        ContactJSON contactJSON = new ContactJSON();
        contactJSON.setFirstName(faker.letterify("???????"));
        contactJSON.setLastName(faker.letterify("????????"));
        contactJSON.setContactStatus("invited");
        contactJSON.setJobTitle(faker.letterify("????????? ???????"));
        contactJSON.setEmail(Collections.singletonList(new Email()));

        return contactJSON;
    }

    public static ContactJSON createContactWithInvalidPhoneFormat() {
        ContactJSON contactJSON = new ContactJSON();
        contactJSON.setFirstName(faker.letterify("???????"));
        contactJSON.setLastName(faker.letterify("????????"));
        contactJSON.setContactStatus("invited");
        contactJSON.setJobTitle(faker.letterify("????????? ???????"));

        Phone phone = new Phone();
        phone.setNumber(faker.bothify("+#####?#####"));
        phone.setType("mobile");

        contactJSON.setPhone(Collections.singletonList(phone));

        return contactJSON;
    }

    public static ContactJSON createContactWithEmptyPhone() {
        ContactJSON contactJSON = new ContactJSON();
        contactJSON.setFirstName(faker.letterify("???????"));
        contactJSON.setLastName(faker.letterify("????????"));
        contactJSON.setContactStatus("invited");
        contactJSON.setJobTitle(faker.letterify("????????? ???????"));
        contactJSON.setPhone(Collections.singletonList(new Phone()));

        return contactJSON;
    }

    public static ContactJSON createContactWithPhoneMoreThan14Number() {
        ContactJSON contactJSON = new ContactJSON();
        contactJSON.setFirstName(faker.letterify("???????"));
        contactJSON.setLastName(faker.letterify("????????"));
        contactJSON.setContactStatus("invited");
        contactJSON.setJobTitle(faker.letterify("????????? ???????"));

        Phone phone = new Phone();
        phone.setNumber(faker.bothify("+###############"));
        phone.setType("mobile");

        contactJSON.setPhone(Collections.singletonList(phone));

        return contactJSON;
    }

    public static ContactJSON createContactWithPhoneEquals14Number() {
        ContactJSON contactJSON = new ContactJSON();
        contactJSON.setFirstName(faker.letterify("???????"));
        contactJSON.setLastName(faker.letterify("????????"));
        contactJSON.setContactStatus("invited");
        contactJSON.setJobTitle(faker.letterify("????????? ???????"));

        Phone phone = new Phone();
        phone.setNumber(faker.bothify("+##############"));
        phone.setType("mobile");

        contactJSON.setPhone(Collections.singletonList(phone));

        return contactJSON;
    }
}
