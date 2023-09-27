import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class SearchHotelTest {
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

     List<String> hotels = driver.findElements(By.xpath("//h4[@class='accommodation-card-body__property-name c-very-dark-grey']"))
             .stream().map(el -> el.getAttribute("textContent")).collect(Collectors.toList());

  Assert.assertEquals(" EXCLUSIVE Aparthotel Warszawa ", hotels.get(0));
  Assert.assertEquals(" Hostel Warszawa Powstaniec ", hotels.get(2));
 }
}
