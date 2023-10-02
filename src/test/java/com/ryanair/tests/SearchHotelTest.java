package com.ryanair.tests;

import com.ryanair.pages.ResultsPage;
import com.ryanair.pages.SearchHotelPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class SearchHotelTest extends BaseTest {
 @Test
    public void getInfoTest() {
  SearchHotelPage searchHotelPage = new SearchHotelPage(driver);
          searchHotelPage.setCity("Warszawa");
          searchHotelPage.setDate();
          searchHotelPage.setPassengers(1,1,"4");
  ResultsPage resultsPage = new ResultsPage(driver);
          List<String> hotels = resultsPage.getHotels();

  Assert.assertEquals(" EXCLUSIVE Aparthotel Warszawa ", hotels.get(0));
 }

 @Test
 public void getInfoWithoutCityTest() {
  driver.findElement(By.xpath("//div[text()='hotele']")).click();
  driver.findElements(By.xpath("//div[@class='input-button__input ng-star-inserted']"))
          .stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
  driver.findElements(By.xpath("//div[@class='calendar-body__cell' and @data-id='2023-10-23']"))
          .stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
  driver.findElements(By.xpath("(//div[@class='input-button__input ng-star-inserted'])[2]"))
          .stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
  driver.findElement(By.xpath("(//div[@class='calendar-body__cell' and @data-id='2023-10-27'])[2]")).click();
  driver.findElement(By.xpath("//div[@class='input-button__input input-button__display-value--truncate-text ng-star-inserted']")).click();
  driver.findElement(By.xpath("(//div[@class='counter__button-wrapper--enabled'])[3]")).click();
  driver.findElement(By.xpath("(//div[@class='counter__button-wrapper--enabled'])[6]")).click();
  driver.findElements(By.xpath("//select[@class='room-picker__select b2' and @name='childrenselect']"))
          .stream().filter(WebElement::isDisplayed).findFirst().ifPresent(el -> el.sendKeys("4"));
  driver.findElements(By.xpath("//button[@class='rooms_done-button ry-button--anchor-blue ry-button--anchor']"))
          .stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
  driver.findElement(By.xpath("//button[@class='rooms-search-widget__start-search ry-button--gradient-yellow']")).click();

  Assert.assertTrue(driver.findElement(By.xpath("//span[@class='_container icon-20 input-button__error-icon']")).isDisplayed(), "The city error not found");
 }
}
