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

import java.time.Duration;
import java.util.List;

public class CodeBehindTestcases {

    //WebDriver driver;
    WebDriver driver;

    WebDriver getDriver() {
        return driver;
    }

    public void openURL(String url) {

        switch (url) {
            case "https://duckduckgo.com/" -> {
                String logo_element = "(//*[@title='Learn about DuckDuckGo'])[2]/img";
                By logoElement = By.xpath(logo_element);

                driver.get(url);
                element_wait(logoElement);
            }
            case "http://the-internet.herokuapp.com/checkboxes", "http://the-internet.herokuapp.com/upload" -> {
                String content_element = "content";
                By logoElement = By.id(content_element);
                driver.get(url);
                element_wait(logoElement);
            }
            case "https://www.w3schools.com/html/html_tables.asp" -> {
                String content_element = "w3-logo";
                By logoElement = By.id(content_element);
                driver.get(url);
                element_wait(logoElement);
            }
            default -> System.out.println("NO URL ENTERED !!!");
        }


    }

    public String element_uploadDocument(String filePath, By fileInputElement, By fileSubmitElement, By uploadedFile) {
        driver.findElement(fileInputElement).sendKeys(filePath);
        driver.findElement(fileSubmitElement).click();
        WebElement successMessage = element_wait(uploadedFile);

        return successMessage.getText();
    }

    public WebElement element_wait(By elementToWait) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(ExpectedConditions.presenceOfElementLocated(
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

//    public boolean checkBox_isChecked(By element, int boxNumber) {
//        return element_checkFromList(element, boxNumber);
//    }
//     public boolean element_checkFromList(By element, int listResultNumber) {}


    public boolean element_checkBoxIsChecked(By element, int boxNumber) {
        boolean flag_isChecked = false;
        int index = boxNumber - 1;
        List<WebElement> options = driver.findElements(element);

        // if box is not checked, click to check then return true means checked
        if (!options.get(index).isSelected()) {
            options.get(index).click();
            flag_isChecked = true;
        } else if (options.get(index).isSelected()) {
            // if box is already checked before, then return true means checked
            flag_isChecked = true;

        }

        return flag_isChecked;
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

    public String validateCountryForTheCompany(By countryElement, By CompanyElement, String ExpectedCompanyName) {
        String flag_countryText = "Wrong Expected Company Name";
        String getCompanyText = driver.findElement(CompanyElement).getText();
        String getCountryText = driver.findElement(countryElement).getText();

        if (getCompanyText.matches(ExpectedCompanyName)) {
            flag_countryText = getCountryText;
        }
        return flag_countryText;
    }

    public enum myBrowsersList {
        CHROME,
        FIREFOX,
        EDGE
    }


}
