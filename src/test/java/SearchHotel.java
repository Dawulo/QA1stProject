import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import java.time.Duration;

public class SearchHotel {
 @Test
    public void getInfo() {
     WebDriverManager.chromedriver().setup();
     WebDriver driver = new ChromeDriver();
     driver.manage().window().maximize();
     driver.get("https://www.ryanair.com/pl/pl");
     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

     driver.findElement(By.xpath("//button[text()='Tak, zgadzam się']")).click();
     driver.findElement(By.xpath("//div[text()='hotele']")).click();
     driver.findElement(By.xpath("//input[@id='input-button__locations-or-properties']")).sendKeys("Warszawa");
     driver.findElement(By.xpath("//div[2]//div[1]/hp-room-search-location-item[1]/div[1]")).click();
     driver.close();
 }
}
