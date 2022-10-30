package datageneration;

import com.github.javafaker.Faker;
import models.files.File;

public class FileDataGeneration {

    private FileDataGeneration() {}
    private static final Faker faker = new Faker();

    public static File createFileData() {
        File fileData = new File();
        fileData.setName(faker.bothify("???????#"));
        fileData.setPath(faker.bothify("/???????/???????.png"));
        fileData.setDescription(faker.bothify("???????? ??????? ###"));
        fileData.setStorageProvider("s3");

        return fileData;
    }

    public static File createFileDataWithoutName() {
        File fileData = new File();
        fileData.setPath(faker.bothify("/???????/???????.png"));
        fileData.setDescription(faker.bothify("???????? ??????? ###"));
        fileData.setStorageProvider("s3");

        return fileData;
    }
}
