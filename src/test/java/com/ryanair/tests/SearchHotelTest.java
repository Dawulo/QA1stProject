package com.ryanair.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ryanair.pages.SearchHotelPage;
import com.ryanair.utils.ExcelReader;
import com.ryanair.utils.SeleniumHelper;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class SearchHotelTest extends BaseTest {

    @Test
    public void getInfoTest() throws IOException {
        ExtentTest test = extentReports.createTest("Search hotel test");
        SearchHotelPage searchHotelPage = new SearchHotelPage(driver);
        List<String> hotels = searchHotelPage
                .setHotel()
                .setCity()
                .setDate()
                .setPassengers(1, 1, "4")
                .performSearch().getHotels();
        test.log(Status.PASS,"Search hotel test done", SeleniumHelper.getScreenshot(driver));
        Assert.assertEquals(" Warsaw Apartments Magnolie ", hotels.get(0));
        test.log(Status.PASS, "Assertions pass", SeleniumHelper.getScreenshot(driver));
    }

    @Test
    public void getInfoWithoutCityTest() throws IOException {
        ExtentTest test = extentReports.createTest("Search hotel test without city");
        SearchHotelPage searchHotelPage = new SearchHotelPage(driver);
        searchHotelPage
                .setHotel()
                .setDate()
                .setPassengers(1, 1, "4")
                .performSearch();
        test.log(Status.PASS,"Search hotel without city test done", SeleniumHelper.getScreenshot(driver));
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='_container icon-20 input-button__error-icon']")).isDisplayed(), "The city error not found");
        test.log(Status.PASS, "Assertions without city test case pass", SeleniumHelper.getScreenshot(driver));
    }
    @Test(dataProvider = "data")
    public void getInfoTesWithDataProvider(String city, String hotel) throws IOException {
        ExtentTest test = extentReports.createTest("Search hotel without city test");
        SearchHotelPage searchHotelPage = new SearchHotelPage(driver);
        List<String> hotels = searchHotelPage
                .setHotel()
                .setCity(city)
                .setDate()
                .setPassengers(1, 1, "4")
                .performSearch().getHotels();
        test.log(Status.PASS,"Search hotel from .xlsx file test done", SeleniumHelper.getScreenshot(driver));
        Assert.assertEquals(hotel, hotels.get(0));
        test.log(Status.PASS, "Assertions test case pass", SeleniumHelper.getScreenshot(driver));
    }
    @DataProvider
    public Object[][] data() throws IOException {
        return ExcelReader.readExcel("testData.xlsx");
    }
}
