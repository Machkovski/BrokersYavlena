package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import java.util.List;
import java.util.NoSuchElementException;
public class YavlenaBrokers {

    private static WebDriver driver;

    @FindBy(xpath = "//h6[@class='MuiTypography-root MuiTypography-h6 mui-style-fnahqf']")
    private List<WebElement> brokerNames;

    @FindBy(xpath = "/html/body/div[3]/div[1]/div[1]/div/div[2]/div/button")
    private WebElement detailsButton;

    @FindBy(id = "broker-keyword")
    private WebElement searchField;

    @FindBy(xpath = "/html/body/div[3]/div/div[1]/div/div[2]/div/div/div/div/div[1]/span")
    private WebElement brokerAddress;

    @FindBy(xpath = "/html/body/div[3]/div/div[1]/div/div[2]/div/div/div/div/div[2]/a[1]")
    private WebElement brokerTelephone;

    @FindBy(xpath = "/html/body/div[3]/div/div[1]/div/div[2]/div/div/div/div/div[2]/a[2]")
    private WebElement brokerMobile;

    @FindBy(xpath = "/html/body/div[3]/div/div[1]/div/div[2]/a[2]")
    private WebElement brokerProperties;

    @FindBy(xpath = "/html/body/div[5]/button")
    private WebElement understoodCookies;

    public YavlenaBrokers(WebDriver driver) {
        YavlenaBrokers.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<String> getBrokerNames() {
        return brokerNames.stream().map(WebElement::getText).toList();
    }

    public static WebElement waitForElementToAppear(WebDriver driver, By locator, int timeoutInSeconds, int pollingIntervalInMillis) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingIntervalInMillis))
                .ignoring(NoSuchElementException.class);

        return wait.until(driverInstance -> {
            WebElement element = driverInstance.findElement(locator);
            return element.isDisplayed() ? element : null;
        });
    }

    //Tried different types of waits, if-else statements, while loop, fluent wait with polling and some custom-made waits, but none of them resulted to wait enough
    //for the broker's card to be displayed in a separate view. Since details button is visible on the start before we search for the broker,
    //it clicks the button immediately resulting in test-fail. Left one of the waits above. The thread.sleep is the worst scenario, but it seemed
    public void clickDetailsButton() throws InterruptedException {
        Thread.sleep(10000);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(detailsButton)).click();

    }

    public void searchBroker(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        int attempts = 0;
        boolean isAccessed = false;

        while (attempts < 3 && !isAccessed) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(searchField));
                searchField.sendKeys(name);
                isAccessed = true;
            } catch (StaleElementReferenceException e) {
                searchField = driver.findElement(By.id("broker-keyword"));
                attempts++;
            }
        }
    }

    public boolean isBrokerMobileDisplayed() {
        try {
            return brokerMobile.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isBrokerDetailsButtonDisplayed() {
        try {
            if (!(detailsButton.isDisplayed()))
                new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(detailsButton));
            return true;
        } catch (NoSuchElementException e){
            System.out.println("Details was not displayed within the specified time:");
            return false;
        }
    }

    public void clickUnderstoodCookies() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(understoodCookies));
        understoodCookies.click();
    }
    public String getBrokerAddress() {
        return brokerAddress.getText();
    }

    public String getBrokerTelephone() {
        return brokerTelephone.getText();
    }

    public String getBrokerMobile() {
        return brokerMobile.getText();
    }

    public String getBrokerProperties() {
        return brokerProperties.getText();
    }

    public static void clickClearButton() {
        WebElement clearButton = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/div[4]/button"));
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(clearButton)).click();
    }

    public static void waitForABrokerToBeLoaded()  {
        try{
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath("/html/body/div[3]/div[1]/div[1]/div/div[2]/div/button"))));
        } catch (WebDriverException e) {
        }
    }
}
