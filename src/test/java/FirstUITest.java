import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class FirstUITest {

    @Test
    void userNameIsCorrectOnOverviewTab(){
        //Arrange
        System.setProperty("webdriver.chrome.driver", "/Users/hyildirim/Documents/informatique/project/testing/chromedriver");
        ChromeOptions options = new ChromeOptions().addArguments("start-fullscreen");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        String user = "andrejs-ps";
        driver.get("https://github.com/" + user);

        //Act
        String actualUserName = driver.findElement(By.className("p-nickname")).getText();

        //Assert
        Assertions.assertEquals(user, actualUserName);

        driver.close();
    }

    @Test
    void repoLinkGoesToCorrectRepo() throws InterruptedException {
        //Arrange
        System.setProperty("webdriver.chrome.driver", "/Users/hyildirim/Documents/informatique/project/testing/chromedriver");
        ChromeOptions options = new ChromeOptions().addArguments("start-fullscreen");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        String user = "andrejs-ps";
        driver.get("https://github.com/" + user);

        //Act
        String repo = "automated-tests-in-java-with-fluent-interface-using-webdriver-selenium";
        WebElement repoLink = driver.findElement(By.linkText(repo));
        repoLink.click();

        Thread.sleep(2000);
        String actualUrl = driver.getCurrentUrl();

        //Assert
        Assertions.assertEquals("https://github.com/andrejs-ps/" + repo, actualUrl);

        driver.close();
    }
}

