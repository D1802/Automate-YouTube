package demo;

import java.util.List;

import java.util.logging.Level;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase {

    ChromeDriver driver;
    SoftAssert softAssert = new SoftAssert();

    @BeforeSuite(enabled = true)
    public void createDriver() {

        System.out.println("Create a Driver");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);

        options.setCapability("goog:loggingPrefs", logs);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
    }

    @Test(enabled = true)
    public void testCase01() {
        WebDriverWait wait = new  WebDriverWait(driver, java.time.Duration.ofSeconds(30));

        basicActions.navigateTo(driver, "https://www.youtube.com/");
        Assert.isTrue(driver.getCurrentUrl().contains("youtube"), "You are Not in Correct URL");

        WebElement about = basicActions.findeTheElement(driver,
                By.xpath("//div[@id='guide-links-primary']/a[text()='About']"));

        // Use JavaScriptExecutor to scroll the element into view
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", about);

        if(wait.until(ExpectedConditions.visibilityOf(about)).isDisplayed())
            basicActions.button_clikeble(driver, about);

        Assert.isTrue(driver.getCurrentUrl().contains("about"), "Url is Not Display the About");
        
        WebElement titleElement = basicActions.findeTheElement(driver, By.xpath("(//section[@class='ytabout__content']//p)[1]"));
        js.executeScript("arguments[0].scrollIntoView(true);", titleElement);
        String titleText = titleElement.getText();
        System.out.println("About: "+titleText);
    }

    @Test(enabled = true)
    public void testCase02(){

        try {
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
            basicActions.navigateTo(driver, "https://www.youtube.com/");
    
            JavascriptExecutor js = (JavascriptExecutor) driver;
    
            WebElement movies = basicActions.findeTheElement(driver, By.xpath("//yt-formatted-string[text()='Movies']"));
            js.executeScript("arguments[0].scrollIntoView(true);", movies);
            basicActions.button_clikeble(driver, movies);
    
           WebElement next = basicActions.findeTheElement(driver, By.xpath("//button[@aria-label='Next']"));
           
           while (next.isDisplayed()) {
            basicActions.button_clikeble(driver, next);
           }
           List<WebElement> imgposter = driver.findElements(By.xpath("//a[@id='thumbnail']//img[contains(@src,'movieposter')]"));
           wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@id='thumbnail']//img[contains(@src,'movieposter')])["+imgposter.size()+"]")));
    
           WebElement grade = basicActions.findeTheElement(driver, By.xpath("//span[@title='The Wolf of Wall Street']/ancestor::ytd-grid-movie-renderer//div[@aria-label='A']"));
           wait.until(ExpectedConditions.visibilityOf(grade));
         
           softAssert.assertEquals(grade.getText(), "A"," Movie is Not Mature");
           
           WebElement movieType = basicActions.findeTheElement(driver, By.xpath("(//span[@title='The Wolf of Wall Street']/ancestor::ytd-grid-movie-renderer//span)[4]"));
           wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@id='thumbnail']//img[contains(@src,'movieposter')])["+imgposter.size()+"]")));
           String[] movieTP = movieType.getText().split(" ");
           System.out.println("Movie is "+movieTP[0]);
           softAssert.assertEquals(movieTP[0], "Comedy","Movie is "+movieTP[0]);
           softAssert.assertEquals(movieTP[0], "Animation","Animation not have a movie");
          

    
           //softAssert.assertAll();
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception: "+e.getMessage());
        }

    }


    @Test(enabled = true)
    public void testCase03(){
        try {
            
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
            basicActions.navigateTo(driver, "https://www.youtube.com/");
    
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement music = basicActions.findeTheElement(driver, By.xpath("//yt-formatted-string[text()='Music']"));
            js.executeScript("arguments[0].scrollIntoView(true);", music);
            basicActions.button_clikeble(driver, music);

            WebElement parentmusic = basicActions.findeTheElement(driver, By.xpath("(//div[@id='title-text']//a/span)[1]"));
            js.executeScript("arguments[0].scrollIntoView(true);", parentmusic);

            WebElement next = basicActions.findeTheElement(driver, By.xpath("(//button[@aria-label='Next'])[1]"));

           

            while (next.isDisplayed()) {
                basicActions.button_clikeble(driver, next);
            }

            WebElement  trackList = basicActions.findeTheElement(driver, By.xpath("(//p[@id='video-count-text'])[11]"));
            wait.until(ExpectedConditions.visibilityOf(trackList));
            String[] MusicTrack = trackList.getText().trim().split(" ");
            
            softAssert.assertEquals(MusicTrack[0], 50,"TrackList is Not as Expected");
            System.out.println("Music TrackList: "+MusicTrack[0]);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Track List is Not same as 50"+e.getMessage());
        }
    }
    @Test(enabled = true)
    public void testCase04(){
        try {
            int sum =0;
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
        basicActions.navigateTo(driver, "https://www.youtube.com/");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement news = basicActions.findeTheElement(driver, By.xpath("//yt-formatted-string[text()='News']"));
        js.executeScript("arguments[0].scrollIntoView(true);", news);
        basicActions.button_clikeble(driver, news);

        WebElement newsHeadline = basicActions.findeTheElement(driver, By.xpath("//span[text()='Latest news posts']"));
        js.executeScript("arguments[0].scrollIntoView(true);", newsHeadline);
        wait.until(ExpectedConditions.visibilityOf(newsHeadline));

        //List<WebElement> newsText = driver.findElements(By.xpath("//yt-formatted-string[@id='home-content-text']//span[1]"));
        List<WebElement> newsText = driver.findElements(By.id("author-text"));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//yt-formatted-string[@id='home-content-text']//span[1])[3]")));
        List<WebElement> votCount = driver.findElements(By.xpath("//span[@id='vote-count-middle']"));


        for (int i = 0; i < 3; i++) {
            String vot = votCount.get(i).getText();
            //System.out.println(vot);
            int x = Integer.parseInt(vot);
            sum += x;
            System.out.println((i+1)+": "+newsText.get(i).getText());
        }
        System.out.println("Sum of Review: "+sum);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        
    }

    @Test(enabled = true, dataProvider = "excelData", dataProviderClass = DP.class)
    public void testCase05(String data){
        try {
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            basicActions.navigateTo(driver, "https://www.youtube.com/");
            int sum = 0;
            WebElement search = basicActions.findeTheElement(driver, By.xpath("//input[@id='search']"));
            basicActions.enter_text(search, data);
            search.sendKeys(Keys.ENTER);

            List<WebElement> views = basicActions.findeTheElements(driver, By.xpath("//div[@id='metadata-line']//span[1]"));

            for (int i = 0; i < views.size(); i++) {

                if (!views.get(i).getText().isEmpty()) {

                    
                String viewsArray = views.get(i).getText();
                String[] viewsCount = viewsArray.split(" ");
                sum += convertToNumber(viewsCount[0]);
                js.executeScript("arguments[0].scrollIntoView(true);", views.get(i));
                if (sum > 100000000) {
                    System.out.println(sum);
                    break;
                }
                    
                }


            }

            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    public static long convertToNumber(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        char lastChar = input.charAt(input.length() - 1);
        String numberPart = input.substring(0, input.length() - 1);
        int multiplier = 1;

        switch (lastChar) {
            case 'K':
            case 'k':
                multiplier = 1_000;
                break;
            case 'M':
            case 'm':
                multiplier = 1_000_000;
                break;
            // Add more cases for other suffixes if needed
            default:
                numberPart = input; // no suffix, just a number
                multiplier = 1;
                break;
        }

        try {
            double value = Double.parseDouble(numberPart);
            return (long) (value * multiplier);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input format", e);
        }
    }

    @AfterSuite(enabled = true)
    public void closeDriver() {
        System.out.println("Close Driver");
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

}
