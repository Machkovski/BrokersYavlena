package tests;

import assertions.YavlenaBrokersAssertions;
import pages.YavlenaBrokers;
import actions.ScreenshotsUtil;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import io.qameta.allure.Attachment;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

import jdk.jfr.Description;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class YavlenaBrokerstPage {

    private WebDriver driver;
    private YavlenaBrokers yavlenaBrokers;
    private ScreenshotsUtil screenshotsUtil;

    @BeforeClass
    void Setup() {
//maximize
        driver = new ChromeDriver();
        driver.get("https://www.yavlena.com/en/broker?city=Sofia");
        driver.manage().window().maximize();
        yavlenaBrokers = new YavlenaBrokers(driver);
        yavlenaBrokers.clickUnderstoodCookies();
    }

    @Test(priority = 1)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that broker search is successful")
    @Step("Search broker by name and verify details")
    public void testBrokerSearch() throws InterruptedException {

        List<String> brokerNames = yavlenaBrokers.getBrokerNames();
        for (String brokerName : brokerNames) {
            yavlenaBrokers.searchBroker(brokerName);
            YavlenaBrokers.waitForABrokerToBeLoaded();
            YavlenaBrokersAssertions.assertOnlyOneBrokerDisplayed(yavlenaBrokers);
            YavlenaBrokersAssertions.verifyBrokerDetails(yavlenaBrokers, driver);
            YavlenaBrokers.clickClearButton();
        }
    }

    @AfterClass
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

    @AfterMethod
    public void captureScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            String screenshotPath = screenshotsUtil.captureScreenshot(driver, result.getName());
            attachScreenshotToAllure(screenshotPath);
        }
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] attachScreenshotToAllure(String screenshotPath) {
        try {
            return Files.readAllBytes(Paths.get(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
