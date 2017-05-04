import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Hubaxis on 03.05.2017.
 */
public class Open_tinkoff {
    @Test
    public void navigate_to_tinkoff () throws InterruptedException {
        WebDriver driver;

        System.setProperty ("webdriver.gecko.driver", "geckodriver.exe");
        driver = new FirefoxDriver ( );

        WebDriverWait wait;
        wait = new WebDriverWait (driver, 30);

        driver.get ("https://www.tinkoff.ru");

       jumpToCommunalPpayments (driver, wait);

       String region = "html/body/div[1]/div/div[1]/div[2]/div[1]/div[2]/section/span/div[1]/div/span[2]";

        wait.until (ExpectedConditions.elementToBeClickable (By.xpath (region)));

        final String regionName = driver.findElement (By.xpath (region)).getText ( );

        if ("Москве" != regionName) {

            driver.findElement (By.xpath (region)).click ( );

            String Moscow = "//div[2]/div/div/span";

            wait.until (ExpectedConditions.elementToBeClickable (By.xpath (Moscow)));

            driver.findElement (By.xpath (Moscow)).click ( );

        }

        wait.until (ExpectedConditions.elementToBeClickable (By.xpath ("//span[2]/a/span")));

        final String Provider = driver.findElement (By.xpath ("//span[2]/a/span")).getText ( );

        driver.findElement (By.xpath ("//span[2]/a/span")).click ( );

        String debtButton = "//div[2]/div/div/div/div/div/div/button";
        wait.until (ExpectedConditions.elementToBeClickable (By.xpath (debtButton)));

        driver.findElement (By.xpath ("//li[2]/span/a/span/span")).click ( );

        String payButton = "//div[6]/div/div/div/div/button";
        wait.until (ExpectedConditions.elementToBeClickable (By.xpath (payButton)));
        driver.findElement (By.xpath (payButton)).click ( );

        jumpToCommunalPpayments (driver, wait);
        wait.until (ExpectedConditions.elementToBeClickable (By.xpath (region)));
        driver.findElement (By.xpath ("//div/span/span")).click ( );

        driver.findElement (By.xpath("//div/span/span")).sendKeys (Provider);
        wait.until (ExpectedConditions.elementToBeClickable (By.xpath ("//span/div/div/div[2]/div")));

        assertTrue(driver.findElement(By.xpath("//span/div/div/div[2]/div")).getText().contains(Provider));



    }

    public void jumpToCommunalPpayments(WebDriver driver, WebDriverWait wait)
    {
        driver.findElement(By.xpath("//div[3]/a/span")).click();
        wait.until (ExpectedConditions.elementToBeClickable (By.xpath ("//li[2]/span[2]/a/span")));
        driver.findElement (By.xpath ("//li[2]/span[2]/a/span")).click ( );
    }

}
