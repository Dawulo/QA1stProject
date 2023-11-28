package com.ryanair.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class SignUpPage {
    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    String randEmail = "tests" + (int) (Math.random() * 1000) + "@tests.com";
    String randWrongEmail = "tests" + (int) (Math.random() * 1000) + "tests.com";
    String userPassword = "Password123";
    @FindBy(xpath = "//button[@class='header-menu-item ng-star-inserted']//span[text()='Rejestracja']")
    private WebElement signUp;
    @FindBy(xpath = "//input[@name='email']")
    private WebElement email;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;
    @FindBy(xpath = "//button[@class='auth-submit__button ry-button--gradient-yellow']")
    private WebElement signUpButton;
    @FindBy(xpath = "//span[@class='body-l-lg body-l-sm ng-star-inserted']")
    private WebElement acceptAlert;

    public void signUp() {
        signUp.click();
        email.sendKeys(randEmail);
        password.sendKeys(userPassword);
        signUpButton.click();
    }

    public void signUpWrongEmail() {
        signUp.click();
        email.sendKeys(randWrongEmail);
        password.sendKeys(userPassword);
        signUpButton.click();
    }

    public void signUpClick() {
        signUp.click();
    }

    public void signUpEmpty() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='auth-submit__button ry-button--gradient-yellow']")));
        wait.withTimeout(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//button[@class='auth-submit__button ry-button--gradient-yellow']")).click();
        List<String> errors = driver.findElements(By.xpath("//span[@class='body-l-lg body-l-sm ng-star-inserted _error']"))
                .stream().map(WebElement::getText).collect(Collectors.toList());
    }
}