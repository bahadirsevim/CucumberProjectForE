package PageOjectModels;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

import java.io.IOException;
import java.util.List;

public class BasePage {
    private WebDriver driver = Driver.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);

    public void click(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public boolean isBroken(List<WebElement> items, String attributeName) throws IOException {
        boolean isBroken = false;
        for (WebElement item:items) {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(item.getAttribute(attributeName));
            HttpResponse response = client.execute(request);
            int statusCode = response.getCode();
            if (statusCode != 200){
                isBroken = true;
                System.out.println("Image is broken: "+item.getAttribute("src"));
            }
        }
        return isBroken;
    }


}
