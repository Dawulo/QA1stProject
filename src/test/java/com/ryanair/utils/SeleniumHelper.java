package com.ryanair.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class SeleniumHelper {
    private static String takeScreenshot(WebDriver driver) throws IOException {
        int randNum = (int) (Math.random()*1000);
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        String path = "src/test/resources/screenshots" + randNum + ".png";
        FileUtils.copyFile(file, new File(path));
        return path;
        }
       public static Media getScreenshot (WebDriver driver) throws IOException{
        String path = takeScreenshot(driver);
        return MediaEntityBuilder.createScreenCaptureFromPath(path).build();
       }
}
