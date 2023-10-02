package com.ryanair.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;


public class SingUpTest extends BaseTest {
 @Test
    public void getInfoTest() {
     String randomEmail = "tests" + (int) (Math.random() * 1000) + "@tests.com";

     driver.findElement(By.xpath("//button[@class='header-menu-item ng-star-inserted']//span[text()='Rejestracja']")).click();
     driver.findElement(By.xpath("//input[@name='email']")).sendKeys(randomEmail);
     driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Password123");
     driver.findElement(By.xpath("//button[@class='auth-submit__button ry-button--full ry-button--flat-yellow']")).click();

     FluentWait<WebDriver> wait = new FluentWait<>(driver);
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'sprawdź swoją pocztę')]")));
     wait.withTimeout(Duration.ofSeconds(5));

     WebElement checker = driver.findElement(By.xpath("//span[@class='body-l-lg body-l-sm ng-star-inserted']"));
     Assert.assertEquals(checker.getText(), "sprawdź swoją pocztę");
 }

 @Test
 public void getInfoWrongEmailTest() {
  String randomEmail = "tests" + (int) (Math.random() * 1000) + "tests.com";

  driver.findElement(By.xpath("//button[@class='header-menu-item ng-star-inserted']//span[text()='Rejestracja']")).click();
  driver.findElement(By.xpath("//input[@name='email']")).sendKeys(randomEmail);
  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Password123");
  driver.findElement(By.xpath("//button[@class='auth-submit__button ry-button--full ry-button--flat-yellow']")).click();

  WebElement checker = driver.findElement(By.xpath("//span[@class='body-l-lg body-l-sm ng-star-inserted _error']"));
  Assert.assertEquals(checker.getText(), "Nieprawidłowy format adresu e-mail");
 }

 @Test
 public void getInfoEmptyFieldsTest() {
  driver.findElement(By.xpath("//button[@class='header-menu-item ng-star-inserted']//span[text()='Rejestracja']")).click();
  FluentWait<WebDriver> wait = new FluentWait<>(driver);
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='auth-submit__button ry-button--full ry-button--flat-yellow']")));
  wait.withTimeout(Duration.ofSeconds(5));
  driver.findElement(By.xpath("//button[@class='auth-submit__button ry-button--full ry-button--flat-yellow']")).click();

  List<String> errors = driver.findElements(By.xpath("//span[@class='body-l-lg body-l-sm ng-star-inserted _error']"))
          .stream().map(WebElement::getText).collect(Collectors.toList());
  SoftAssert softAssert = new SoftAssert();
  softAssert.assertTrue(errors.contains("Wymagany jest adres e-mail"));
  softAssert.assertTrue(errors.contains("Wymagane jest hasło"));
  softAssert.assertAll();
 }
}
