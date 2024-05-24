package demo;

import java.util.List;

import org.apache.hc.client5.http.impl.auth.SystemDefaultCredentialsProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v116.v116CdpInfo;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
    }

    @Test(enabled = false)
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

    @Test(enabled = false)
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


    @Test(enabled = false)
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

        List<WebElement> newsText = driver.findElements(By.xpath("//yt-formatted-string[@id='home-content-text']//span[1]"));
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

    @AfterSuite(enabled = true)
    public void closeDriver() {
        System.out.println("Close Driver");
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

}
