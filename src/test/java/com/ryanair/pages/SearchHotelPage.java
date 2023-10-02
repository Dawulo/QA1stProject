package com.ryanair.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchHotelPage {

    @FindBy(xpath = "//div[text()='hotele']")
    private WebElement hotels;
    @FindBy(xpath = "//input[@id='input-button__locations-or-properties']")
    private WebElement cityName;
    @FindBy(xpath = "//div[@class='input-button__input ng-star-inserted']")
    private WebElement checkIn;
    @FindBy(xpath = "(//div[@class='calendar-body__cell' and @data-id='2023-10-23'])[2]")
    private WebElement checkInDate;
    @FindBy(xpath = "(//div[@class='input-button__input ng-star-inserted'])[2]")
    private WebElement checkOut;
    @FindBy(xpath = "(//div[@class='calendar-body__cell' and @data-id='2023-10-27'])[2]")
    private WebElement checkOutDate;
    @FindBy(xpath = "//div[@class='input-button__input input-button__display-value--truncate-text ng-star-inserted']")
    private WebElement passengers;
    @FindBy(xpath = "(//div[@class='counter__button-wrapper--enabled'])[3]")
    private WebElement adults;
    @FindBy(xpath = "(//div[@class='counter__button-wrapper--enabled'])[6]")
    private WebElement children;
    @FindBy(xpath = "(//select[@class='room-picker__select b2' and @name='childrenselect'])[2]")
    private WebElement childrenAge;
    @FindBy(xpath = "(//button[@class='rooms_done-button ry-button--anchor-blue ry-button--anchor'])[2]")
    private WebElement passengersButton;
    @FindBy(xpath = "//button[@class='rooms-search-widget__start-search ry-button--gradient-yellow']")
    private WebElement searchHotelButton;
    private WebDriver driver;

    public SearchHotelPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public void setCity (String city) {
        hotels.click();
        cityName.sendKeys(city);
        String xpathCity = String.format("//div[@class='location__name b2' and starts-with(text(),'%s')]",city);
        driver.findElements(By.xpath(xpathCity)).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
    }
    public void setDate() {
        checkIn.click();
        checkInDate.click();
        checkOut.click();
        checkOutDate.click();
    }
        public void setPassengers (int addAdults, int addChildren, String kidsAge) {
        passengers.click();
        addTravellers(adults, addAdults);
        addTravellers(children, addChildren);
        childrenAge.sendKeys(kidsAge);
        String passengersButtonXpath = "//button[@class='rooms_done-button ry-button--anchor-blue ry-button--anchor']";
        driver.findElements(By.xpath(passengersButtonXpath)).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        searchHotelButton.click();
    }
    public void addTravellers (WebElement travellerButton, int numOfTravellers) {
        for (int i = 0; i < numOfTravellers; i++) {
            travellerButton.click();
        }
    }

}
