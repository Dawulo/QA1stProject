import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;


public class SingUpTest {
 @Test
    public void getInfo() {
     WebDriverManager.chromedriver().setup();
     WebDriver driver = new ChromeDriver();
     driver.manage().window().maximize();
     driver.get("https://www.ryanair.com/pl/pl");
     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
     String randomEmail = "tests" + (int) (Math.random() * 1000) + "@tests.com";

     driver.findElement(By.xpath("//button[text()='Tak, zgadzam się']")).click();
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
}
