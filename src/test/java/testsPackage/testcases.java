package testsPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class testcases {

    WebDriver driver;

//        @BeforeMethod
//    public void setup()
//    {
//
//
//    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void TC1() {
        //●	Open Google Chrome
        //●	Navigate to [https://duckduckgo.com/]
        //●	Assert that the page title is [Google]
        //●	Close Google Chrome
        //----------------------------------------------

        //Testdata
        String expectedPageTitle = "Google";

        //Steps
        openBrowser(myBrowsersList.CHROME);
        openDuckURL();

        //Assertion
        Assert.assertEquals(getPageTitle(), expectedPageTitle);

    }

    @Test
    public void TC2() {
        //●	Open Google Chrome
        //●	Navigate to [https://duckduckgo.com/]
        //●	Assert that the DuckDuckGo logo is displayed
        //●	Close Google Chrome
        //----------------------------------------------

        // Objects identification
        String logo_element = "(//*[@title='Learn about DuckDuckGo'])[2]/img";
        By logoElement = By.xpath(logo_element);

        //Steps
        openBrowser(myBrowsersList.CHROME);
        openDuckURL();

        //Assertion
        Assert.assertTrue(element_isDisplayed(logoElement));

    }


    @Test
    public void TC3() {
        //●	Open Google Chrome
        //●	Navigate to [https://duckduckgo.com/]
        //●	Search for [Selenium WebDriver]
        //●	Assert that the link of the first result is [https://www.selenium.dev/documentation/webdriver/]
        //●	Close Google Chrome
        //----------------------------------------------

        // Testdata
        String search_element = "searchbox_input";
        String searchValue = "Selenium WebDriver";
        String expectedLink = "https://www.selenium.dev/documentation/webdriver/";

        //Object identification
        By searchElement = By.id(search_element);
        By searchButtonElement = By.cssSelector("button[aria-label='Search']");
        By firstResultElement = By.xpath("(//*[@data-testid='result-title-a'])[1]");

        // Steps
        openBrowser(myBrowsersList.CHROME);
        openDuckURL();
        element_write(searchElement, searchValue);
        element_click(searchButtonElement);

        // Assert the first result link
        element_wait(firstResultElement);
        String actualLink = element_getDomAttribute(firstResultElement, "href");

        Assert.assertEquals(actualLink, expectedLink, "First result link doesn't match expected");
        System.out.println("Test passed! First result link matches expected.");
    }

    @Test
    public void TC4() {
        //●	Open Mozilla Firefox
        //●	Navigate to [https://duckduckgo.com/]
        //●	Search for [TestNG]
        //●	Assert that the text of the fourth result is [TestNG Tutorial]
        //●	Close Mozilla Firefox
        //----------------------------------------------

        // Testdata
        String search_element = "searchbox_input";
        String searchValue = "TestNG";
        String expectedResult = "TestNG Tutorial";
        String searchlist = "//*[@id='searchbox_homepage']//li";
        int listResultNumber = 4;

        // Elements
        By search_Element = By.id(search_element);
        By search_List = By.xpath(searchlist);

        //Steps
        openBrowser(myBrowsersList.FIREFOX);
        openDuckURL();
        element_click(search_Element);
        element_write(search_Element, searchValue);
        element_wait(search_List);

        // Assert
        String actualResult = element_selectFromList(search_List, listResultNumber);
        Assert.assertEquals(actualResult, expectedResult,
                "List result value NOT Matched, The expected is ->  " + expectedResult +
                        " but The Actual is ->  " + actualResult);
        System.out.println("Test passed! First result link matches expected.");

    }

    @Test
    public void TC5() {
        //●	Open Google Chrome
        //●	Navigate to [https://duckduckgo.com/]
        //●	Search for [Cucumber IO]
        //●	Assert that the link of the second result contains [https://www.linkedin.com]
        //●	Close Google Chrome
        //----------------------------------------------

        // Testdata
        String search_element = "searchbox_input";
        String searchValue = "Cucumber IO";
        String expectedLink = "https://www.linkedin.com";

        // Elements
        By searchElement = By.id(search_element);
        By searchButtonElement = By.cssSelector("button[aria-label='Search']");
        By secondResultElement = By.xpath("(//*[@data-testid='result-title-a'])[2]");

        //  Steps
        openBrowser(myBrowsersList.CHROME);
        openDuckURL();
        element_write(searchElement, searchValue);
        element_click(searchButtonElement);

        // Assert
        element_wait(secondResultElement);
        String actualLink = element_getDomAttribute(secondResultElement, "href");

        Assert.assertTrue(actualLink.contains(expectedLink),
                "Second result link NOT Matched, The expected is " + expectedLink +
                        " The Actual is " + actualLink);
        System.out.println("Test passed! Second result link matches expected.");


    }

    //---------------Methods---------------//
    public void openDuckURL() {
        String duck_URL = "https://duckduckgo.com/";

        String logo_element = "(//*[@title='Learn about DuckDuckGo'])[2]/img";
        By logoElement = By.xpath(logo_element);

        driver.get(duck_URL);
        element_wait(logoElement);

    }

    public void element_wait(By elementToWait) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(
                elementToWait));
    }

    public void openBrowser(myBrowsersList browserName) {
        switch (browserName) {
            case myBrowsersList.EDGE:
                driver = new EdgeDriver();
                break;
            case myBrowsersList.FIREFOX:
                driver = new FirefoxDriver();
                break;
            case myBrowsersList.CHROME:
                driver = new ChromeDriver();
                break;
            default:
                driver = new InternetExplorerDriver();
                break;
        }
        driver.manage().window().maximize();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean element_isDisplayed(By element) {
        return driver.findElement(element).isDisplayed();
    }

    public void element_write(By element, String value) {
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(value);
    }

    public void element_click(By element) {
        driver.findElement(element).click();
    }

    public String element_getDomAttribute(By element, String attributeValue) {
        return driver.findElement(element).getDomAttribute(attributeValue);
    }

    public String element_selectFromList(By element, int listResultNumber) {
        /*
        // Locate the dropdown element
        WebElement dropdown = driver.findElement(element);

        // Create Select object
        Select select = new Select(dropdown);

        // Get all options
        List<WebElement> options = select.getOptions();

        // Get the 4th option (index 3 since lists are 0-based)
        WebElement OptionNumber = options.get(listResultNumber-1);
        String OptionNumberValue = OptionNumber.getText();
        System.out.println("Fourth option: " + OptionNumberValue);
        return  OptionNumberValue;
        */

        List<WebElement> options = driver.findElements(element);
        String OptionNumberValue = options.get(listResultNumber).getText();
        System.out.println("Selected option is : " + OptionNumberValue);
        return OptionNumberValue;

    }

    public enum myBrowsersList {
        CHROME,
        FIREFOX,
        EDGE,
        IE
    }
}

