package com.ryanair.tests;

import com.ryanair.pages.SearchHotelPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchHotelTest extends BaseTest {
    @Test
    public void getInfoTest() {
        SearchHotelPage searchHotelPage = new SearchHotelPage(driver);
        List<String> hotels = searchHotelPage
                .setCity("Warszawa")
                .setDate()
                .setPassengers(1, 1, "4")
                .performSearch().getHotels();

        Assert.assertEquals(" EXCLUSIVE Aparthotel Warszawa ", hotels.get(0));
    }

    @Test
    public void getInfoWithoutCityTest() {
        SearchHotelPage searchHotelPage = new SearchHotelPage(driver);
        searchHotelPage
                .setCity("")
                .setDate()
                .setPassengers(1, 1, "4")
                .performSearch();

        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='_container icon-20 input-button__error-icon']")).isDisplayed(), "The city error not found");
    }
}
