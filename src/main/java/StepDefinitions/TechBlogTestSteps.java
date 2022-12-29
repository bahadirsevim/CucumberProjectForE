package StepDefinitions;

import PageOjectModels.MyHomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utilities.Driver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TechBlogTestSteps {

    private WebDriver driver;
    MyHomePage homePage = new MyHomePage()
;
    @Given("Navigate to website")
    public void navigate_to_website() {
        driver = Driver.getDriver();
        driver.manage().window().maximize();
        driver.get("https://techcrunch.com/");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    @And("Verify has author")
    public void verify_has_author() {
        homePage.scrollToEnd();
        Assert.assertTrue(homePage.hasAnAuthor());
    }
    @And("Verify has image")
    public void verify_has_image() {
        homePage.scrollToEnd();
        Assert.assertFalse(homePage.HasABrokenImage());
    }

    @Given("Click an article")
    public void clickAnArticle() {
        homePage.pressSpace();
        homePage.pressSpace();
        homePage.clickAnArticle();
    }

    @And("Verify browser title and news title is same")
    public void verify_browser_title_and_news_title_is_same() {
        Assert.assertTrue(homePage.isBrowserAndArticleTitleSame());
    }
    @And("Verify the links within the news content")
    public void verify_the_links_within_the_news_content() {
        homePage.scrollToEnd();
        Assert.assertFalse(homePage.HasBrokenLinksInArticle());
    }

}
