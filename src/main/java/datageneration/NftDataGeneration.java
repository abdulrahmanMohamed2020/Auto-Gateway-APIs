package datageneration;

import com.github.javafaker.Faker;
import models.nfts.NftData;

public class NftDataGeneration {

    private NftDataGeneration() {}

    private static final Faker faker = new Faker();

    public static NftData createNftData(String fileId, String filePath) {
        NftData nftData = new NftData();
        nftData.setTitle(faker.bothify("???????????"));
        nftData.setDescription(faker.bothify("??????????????"));
        nftData.setCategoryId("XNwyaIOPKkfc1iFngXorm");
        nftData.setFileId(fileId);
        nftData.setFilePath(filePath);

        return nftData;
    }

    public static NftData createInvalidNftData() {
        NftData nftData = new NftData();
        nftData.setTitle(faker.bothify("???????????"));
        nftData.setDescription(faker.bothify("??????????????"));
        nftData.setCategoryId("XNwyaIOPKkfc1iFngXorm");
        nftData.setFileId(faker.letterify("?????????????????"));
        nftData.setFilePath("http://example-bucket.s3-website.us-west-2.amazonaws.com/photo.jpg");

        return nftData;
    }

    public static NftData createNftDataWithEmptyTitle() {
        NftData nftData = new NftData();
        nftData.setTitle("");
        nftData.setDescription(faker.bothify("??????????????"));
        nftData.setCategoryId("XNwyaIOPKkfc1iFngXorm");
        nftData.setFilePath("http://example-bucket.s3-website.us-west-2.amazonaws.com/photo.jpg");

        return nftData;
    }

    public static NftData createNftDataWithEmptyFilePath() {
        NftData nftData = new NftData();
        nftData.setTitle(faker.bothify("???????????"));
        nftData.setDescription(faker.bothify("??????????????"));
        nftData.setCategoryId("XNwyaIOPKkfc1iFngXorm");
        nftData.setFilePath("");

        return nftData;
    }

    public static NftData createNftDataWithEmptyCategoryId() {
        NftData nftData = new NftData();
        nftData.setTitle(faker.bothify("???????????"));
        nftData.setDescription(faker.bothify("??????????????"));
        nftData.setCategoryId("");
        nftData.setFilePath("http://example-bucket.s3-website.us-west-2.amazonaws.com/photo.jpg");

        return nftData;
    }

    public static NftData createNftDataWithoutTitle() {
        NftData nftData = new NftData();
        nftData.setDescription(faker.bothify("??????????????"));
        nftData.setCategoryId("XNwyaIOPKkfc1iFngXorm");
        nftData.setFilePath("http://example-bucket.s3-website.us-west-2.amazonaws.com/photo.jpg");

        return nftData;
    }

    public static NftData createNftDataWithoutFilePath() {
        NftData nftData = new NftData();
        nftData.setTitle(faker.bothify("???????????"));
        nftData.setDescription(faker.bothify("??????????????"));
        nftData.setCategoryId("XNwyaIOPKkfc1iFngXorm");

        return nftData;
    }

    public static NftData createNftDataWithoutCategoryId() {
        NftData nftData = new NftData();
        nftData.setTitle(faker.bothify("???????????"));
        nftData.setDescription(faker.bothify("??????????????"));
        nftData.setFilePath("http://example-bucket.s3-website.us-west-2.amazonaws.com/photo.jpg");

        return nftData;
    }

    public static NftData createNftDataWithTitleMoreThan50Character() {
        NftData nftData = new NftData();
        nftData.setTitle(faker.bothify("?????????#?????????#?????????#?????????#?????????#?"));
        nftData.setDescription(faker.bothify("??????????????"));
        nftData.setCategoryId("XNwyaIOPKkfc1iFngXorm");
        nftData.setFilePath("http://example-bucket.s3-website.us-west-2.amazonaws.com/photo.jpg");

        return nftData;
    }
}
