package pages;

import io.github.densudas.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static support.DriverFactory.getDriver;

public class SignupPage {
    private WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    private Actions actions = new Actions(getDriver());

    // ShadowRootSearch to access the shadow DOM
    ShadowRootSearch shadowRootSearch = new ShadowRootSearch(getDriver());

    public void open(String pageUrl) {
        getDriver().get(pageUrl);
    }

    public void letPageLoad(String xpath) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public void waitForElementToBePresent(String cssSelector) {

        try {
            // Attempt to locate the element inside the shadow root
            WebElement element = wait.until(driver ->
                    {
                        try {
                            return shadowRootSearch.findElement(By.cssSelector(cssSelector));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
            System.out.println(element.getText());
        } catch (NoSuchElementException e) {
            System.err.println("Element not found within the shadow DOM: " + cssSelector);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An error occurred while accessing the shadow DOM: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void clickOnElement(String cssSelector) {
        tryToFindInShadowContent(cssSelector).click();
    }

    public void typeIntoInputField(String input, String cssSelector) {
        WebElement element = tryToFindInShadowContent(cssSelector);
        element.sendKeys(input);
    }

    public void clickAwayFromElement(String cssSelector) {
        WebElement element = tryToFindElement(cssSelector);
        actions.moveToElement(element).click().perform();
        actions.moveByOffset(100, 100).click().perform();
    }

    public void lookForErrorMessage(String expectedErrorMessage, String cssSelector) {
        WebElement inputSlot = tryToFindElement(cssSelector);

        // Attempt to find the error message span
        WebElement errorMessageSpan = inputSlot.findElement(By.xpath("./ancestor::div[@class='v-input__control']//span[@class='k-text']"));

        // Check if the error message span is found and not empty
        if (errorMessageSpan != null) {
            String actualErrorMessage = errorMessageSpan.getText().trim();
            assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage);
        } else {
            throw new AssertionError("Error message element not found for input slot: " + cssSelector);
        }
    }

    public void lookForPopupErrorMessage(String expectedErrorMessage) throws Exception {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-pw='app-widget-notifications-item']")));

        // Locate the shadow host
        WebElement shadowHost = getDriver().findElement(By.cssSelector("div[data-wi='notifications']"));

        // Navigate into the shadow DOM layers
        WebElement notificationsWrapper = shadowRootSearch.findElement(shadowHost, By.cssSelector("div[data-w='app-widget-notifications-wrapper']"));

        WebElement notificationItem = shadowRootSearch.findElement(notificationsWrapper, By.cssSelector("div[data-wi='notification-item']"));
        WebElement notificationTextContainer = shadowRootSearch.findElement(notificationItem, By.cssSelector("div[data-w='app-widget-notifications-item']"));

        // Now locate the span within the final shadow DOM layer
        WebElement notificationText = shadowRootSearch.findElement(notificationTextContainer, By.cssSelector("span.k-text"));

        // Assert the text content
        String actualText = notificationText.getText();
        assertThat(actualText).isEqualTo(expectedErrorMessage);
    }

    public void assertAgreementError() throws Exception {
        // Locate the checkbox by ID
        WebElement checkboxIcon = shadowRootSearch.findElement(By.cssSelector("#input-177"));

        // Move up to the previous-sibling <span> element
        WebElement parentSpan = checkboxIcon.findElement(By.xpath("preceding-sibling::span"));

        // Assert that the error class is present on the previous-sibling <span>
        String iconClassAttribute = parentSpan.getAttribute("class");
        assertThat(iconClassAttribute).contains("error--text");
    }

    public WebElement tryToFindInShadowContent (String cssSelector) {
        try {
            return shadowRootSearch.findElement(By.cssSelector(cssSelector));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement tryToFindElement(String cssSelector) {
        try {
            // Locate the element within the shadow DOM using CSS selector
            return shadowRootSearch.findElement(By.cssSelector(cssSelector));
        } catch (Exception e) {
            throw new RuntimeException("Element not found using CSS selector: " + cssSelector, e);
        }
    }
}