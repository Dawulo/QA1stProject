package com.ryanair.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ryanair.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    public static ExtentSparkReporter extentSparkReporter;
    public static ExtentReports extentReports;
    @BeforeSuite
    public void beforeSuite(){
        extentSparkReporter = new ExtentSparkReporter("src/index.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
}
    @AfterSuite
    public void afterSuite(){
        extentReports.flush();
    }
    @BeforeMethod
    public void setup() throws IOException {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get("https://www.ryanair.com/pl/pl");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.findElement(By.xpath("//button[text()='Tak, zgadzam się']")).click();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
