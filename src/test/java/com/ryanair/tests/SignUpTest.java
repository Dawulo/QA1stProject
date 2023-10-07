package com.ryanair.tests;

import com.ryanair.pages.SignUpPage;
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


public class SignUpTest extends BaseTest {
    @Test
    public void getInfoTest() {
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUp("Password123");

        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'sprawdź swoją pocztę')]")));
        wait.withTimeout(Duration.ofSeconds(10));
        WebElement checker = driver.findElement(By.xpath("//span[@class='body-l-lg body-l-sm ng-star-inserted']"));
        Assert.assertEquals(checker.getText(), "sprawdź swoją pocztę");
    }

    @Test
    public void getInfoWrongEmailTest() {
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUpWrongEmail("Password123");

        WebElement checker = driver.findElement(By.xpath("//span[@class='body-l-lg body-l-sm ng-star-inserted _error']"));
        Assert.assertEquals(checker.getText(), "Nieprawidłowy format adresu e-mail");
    }

    @Test
    public void getInfoEmptyFieldsTest() {
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUpClick();

        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='auth-submit__button ry-button--full ry-button--flat-yellow']")));
        wait.withTimeout(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//button[@class='auth-submit__button ry-button--full ry-button--flat-yellow']")).click();
        List<String> errors = driver.findElements(By.xpath("//span[@class='body-l-lg body-l-sm ng-star-inserted _error']"))
                .stream().map(WebElement::getText).collect(Collectors.toList());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("Wymagany jest adres e-mail"));
        softAssert.assertTrue(errors.contains("Wymagane jest hasło"));
        softAssert.assertAll();
    }
}
