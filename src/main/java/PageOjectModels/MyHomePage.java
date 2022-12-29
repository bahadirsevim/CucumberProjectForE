package PageOjectModels;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.io.IOException;
import java.util.List;

public class MyHomePage extends BasePage{
    WebDriver driver;

    public MyHomePage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void scrollToEnd(){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollBy(0,6000)");
    }

    @FindBy(css= "article.post-block")
    private List<WebElement> articles;

    @FindBy(css= "article.post-block img")
    private List<WebElement> articleImages;

    public boolean HasABrokenImage(){
        try {
            return isBroken(articleImages, "src");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FindBy(css= "span.river-byline__authors")
    private List<WebElement> authors;

    public boolean hasAnAuthor(){
        boolean hasAuthor = true;
        for (WebElement author:authors) {
            String authorName = author.getText();
            if (authorName.isEmpty()){
                hasAuthor = false;
            }
        }
        return hasAuthor;
    }

    @FindBy(tagName = "html")
    private WebElement html;

    public void pressSpace(){
        html.sendKeys(Keys.SPACE);
    }

    @FindBy(css= "a.post-block__title__link")
    private List<WebElement> articleTitles;

    public void clickAnArticle(){
        click(articleTitles.get(3));
    }

    @FindBy(css= "h1.article__title")
    private WebElement articleTitle;

    public boolean isBrowserAndArticleTitleSame(){
        String browserTitleText = driver.getTitle().replace(" | TechCrunch", "");
        String articleTitleText = articleTitle.getText();
        return articleTitleText.equals(browserTitleText);
    }

    @FindBy(css= ".article__content-wrap p a")
    private List<WebElement> linksInArticle;

    public boolean HasBrokenLinksInArticle(){
        try {
            return isBroken(linksInArticle, "href");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
