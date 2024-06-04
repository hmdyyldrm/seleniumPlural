package com.framework.uitests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class OverviewTabTests extends BaseTestClass{

    String user = "andrejs-ps";

    @BeforeEach
    void overviewTabSetup() {
        driver.get(BASE_URL + user);
    }

    @AfterEach
    void localCleanup() {
        //nothing to do here now
    }
    @Test
    void userNameisCorrectOnOverviewTab() {
        //Act
        String actualUserName = driver.findElement(By.className("p-nickname")).getText();

        //Assert
        Assertions.assertEquals(user, actualUserName);
    }

    @Test
    void repoLinkGoesToCorrectRepo() throws InterruptedException {
        //Act
        String repo = "automated-tests-in-java-with-fluent-interface-using-webdriver-selenium";
        WebElement repoLink = driver.findElement(By.linkText(repo));
        repoLink.click();

        Thread.sleep(2000);
        String actualUrl = driver.getCurrentUrl();

        //Assert
        Assertions.assertEquals("https://github.com/andrejs-ps/" + repo, actualUrl);
    }
}
