import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FirstUITestRefactored {

    public static final String BASE_URL = "https://github.com/";
    static WebDriver driver;

    @BeforeAll
    static void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/hyildirim/Documents/informatique/project/testing/chromedriver");
        ChromeOptions options = new ChromeOptions().addArguments("start-fullscreen");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterAll
    static void cleanUp() {
        driver.close();
    }

    @Test
    void userNameisCorrectOnOverviewTab() {
        String user = "andrejs-ps";
        driver.get(BASE_URL + user);

        //Act
        String actualUserName = driver.findElement(By.className("p-nickname")).getText();

        //Assert
        Assertions.assertEquals(user, actualUserName);
    }

    @Test
    void repoLinkGoesToCorrectRepo() throws InterruptedException {
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
    }

    @Test
    void repositoryCountIsCorrect(){

        //Act
        driver.get("https://github.com/andrejs-ps?tab=repositories");

        List<WebElement> repos = driver.findElements(By.xpath("//div[@id='user-repositories-list']//li"));

        //Assert
        Assertions.assertEquals(21, repos.size());
    }

}
