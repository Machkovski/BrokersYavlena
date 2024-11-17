package assertions;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pages.YavlenaBrokers;

import java.time.Duration;

import java.util.List;


import static org.testng.AssertJUnit.assertEquals;

public class YavlenaBrokersAssertions {

    private static WebDriver driver;
    public YavlenaBrokersAssertions(WebDriver driver) {}
    private final YavlenaBrokers yavlenaBrokers = new YavlenaBrokers(driver);

    public static void verifyBrokerDetails(YavlenaBrokers yavlenaBrokers, WebDriver driver) throws InterruptedException {
        try {
            if (yavlenaBrokers.isBrokerDetailsButtonDisplayed()) {

                yavlenaBrokers.clickDetailsButton();
                new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div/div[1]/div/div[2]/div/div/div/div/div[2]/a[1]")));
                Assert.assertNotNull(yavlenaBrokers.getBrokerAddress(), "Broker address is null!");
                Assert.assertNotNull(yavlenaBrokers.getBrokerTelephone(), "Broker landline is null!");
                if (yavlenaBrokers.isBrokerMobileDisplayed()) {
                    Assert.assertNotNull(yavlenaBrokers.getBrokerMobile(), "Broker mobile is null!");
                }
                Assert.assertNotNull(yavlenaBrokers.getBrokerProperties(), "Broker properties are not present!");
            } else {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            }
        } catch (TimeoutException e){
            System.out.println("Element was not displayed within the specified time:");
        }
    }

    public static void assertOnlyOneBrokerDisplayed(YavlenaBrokers yavlenaBrokers) throws InterruptedException {
        List<String> brokers = yavlenaBrokers.getBrokerNames();
        if (brokers.size() == 1) {
            assertEquals("Expected only one broker to be displayed, found: " + brokers.size(), 1, brokers.size());
        }

        else {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        }
    }
}
