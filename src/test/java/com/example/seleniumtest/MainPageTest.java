package com.example.seleniumtest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vali_\\Desktop\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.yavlena.com/broker/");

        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchByNameAllTheBrokers() throws InterruptedException {
        WebElement acceptCookies = driver.findElement(By.xpath("//input[@type='submit' and @class='hide-cookies-message green-btn' and @value='Разбрах']"));
        acceptCookies.click();
        WebElement loadMoreButton = driver.findElement(By.xpath("//a[@data-container='load-more-brokers' and contains(@class, 'green-btn') and contains(@class, 'load-more-results-list')]"));
        loadMoreButton.click();
        Thread.sleep(3000);
        List<WebElement> brokersList = driver.findElements(By.xpath("//div[@class='broker-data']"));
        brokersList.forEach(brokerElement -> {
            String brokerText = brokerElement.getText();
            if (brokerText.length() > 100) {
                WebElement nameElement = brokerElement.findElement(By.xpath(".//h3[@class='name']/a"));
                String brokerName = nameElement.getText();
                System.out.println("Broker Name: " + brokerName);
                WebElement searchBox = driver.findElement(By.xpath("//input[@id='searchBox' and @type='text' and @placeholder='име, район' and @class='input-search' and @data-container='broker-keyword']"));
                searchBox.clear();
                searchBox.sendKeys(brokerName);
            }
        });
    }
}
