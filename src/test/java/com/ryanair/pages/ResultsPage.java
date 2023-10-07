package com.ryanair.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ResultsPage {

    public ResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h4[@class='accommodation-card-body__property-name c-very-dark-grey']")
    private List<WebElement> hotelResults;

    public List<String> getHotels() {
        return hotelResults.stream().map(el -> el.getAttribute("textContent")).collect(Collectors.toList());
    }
}
