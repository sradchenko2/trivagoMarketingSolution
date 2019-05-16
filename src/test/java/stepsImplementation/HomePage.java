package stepsImplementation;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class HomePage {

    WebDriver driver;
    String coolestCityName;

    @Before
    public void beforeScenario()
    {
        System.setProperty("webdriver.gecko.driver", "\\geckodriver.exe");  //please use your qeckodriver path for firefox
        driver=new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://room5.trivago.com");
    }

    @Given ("^user is on homepage$")
    public void user_is_on_homepage(){
        System.out.println("User is on HomePage");
        verifyPageLoad();
        //Verify by link that we are at the main page (there could be any other HomePage definition, in case if page will be redirected to other url)
        driver.getCurrentUrl().equalsIgnoreCase("https://magazine.trivago.com");
    }

    @When("^user tap on ReadMore button$")
    public void user_tap_on_ReadMore_button() throws Throwable{
        System.out.println("User tap on ReadMore button");
        Thread.sleep(3000);
        coolestCityName = getCoolestHotelName().toLowerCase();
        driver.findElement(By.xpath("//*[@id='app']/div[3]/div/div[1]/div/a/div[2]/div/div[3]")).click();
    }
    @Then("^coolestHotels page displayed$")
    public void coolestHotels_page_displayed() throws Throwable{
        System.out.println("CoolestHotels page displayed");
        Thread.sleep(3000);
        //Verify that opened page url contain city name from previuos page Banner text, where we taped ReadMore button
        Assert.assertTrue(driver.getCurrentUrl().contains(coolestCityName), "opened page doesn't match with expected");
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }

    public String getCoolestHotelName(){
        String bannerText = driver.findElement(By.xpath("//*[@id='app']/div[3]/div/div[1]/div/a/div[2]/div/div[2]/span")).getText();
        String cityText = StringUtils.substringAfter(bannerText, "Coolest Hotels in ");
        return cityText;
    }

    public void verifyPageLoad(){
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS) ;
        new WebDriverWait(driver, 20).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}
