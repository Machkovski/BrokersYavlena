package actions;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotsUtil {

    private static WebDriver driver;
        public static String captureScreenshot(WebDriver driver, String screenshotName) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            System.out.println("Taking a ss");
            // Construct a platform-independent path
//            String folderPath = "screenshots" + File.separator;
            String folderPath = "/Users/tomchemachkovski/Documents/ProbaWeb/target/screenshots/" + File.separator;
            String destinationPath = folderPath + screenshotName + ".png";
//            String destinationPath = "/Users/tomchemachkovski/Documents/ProbaWeb/target" + ".png";

            try {
                Files.createDirectories(Paths.get(folderPath)); // Ensure directory exists
                Files.copy(source.toPath(), Paths.get(destinationPath)); // Save the screenshot
            } catch (IOException e) {
                e.printStackTrace();
            }
            return destinationPath;
        }
}
