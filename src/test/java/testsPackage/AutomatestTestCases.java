package testsPackage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class AutomatestTestCases {

    // Classes objects
    CodeBehindTestcases codeBehindTCs;

    // URLs Data
    String URL_duck = "https://duckduckgo.com/";
    String URL_heroCheckBox = "http://the-internet.herokuapp.com/checkboxes";
    String URL_w3_htmlTable = "https://www.w3schools.com/html/html_tables.asp";
    String URL_heroUpload = "http://the-internet.herokuapp.com/upload";
    String URL_jqueryuiDragDrop ="https://jqueryui.com/resources/demos/droppable/default.html";

    //TC1 Data //
    String TC1_expectedPageTitle = "Google";
    //TC2 Data //
    String TC2_logo_locator = "(//*[@title='Learn about DuckDuckGo'])[2]/img";
    By TC2_ByLogoElement = By.xpath(TC2_logo_locator);
    // TC3 Data //
    String TC3_search_locator = "searchbox_input";
    String TC3_searchValue = "Selenium WebDriver";
    String TC3_expectedLink = "https://www.selenium.dev/documentation/webdriver/";
    By TC3_BySearchElement = By.id(TC3_search_locator);
    By TC3_BySearchButtonElement = By.cssSelector("button[aria-label='Search']");
    By TC3_ByFirstResultElement = By.xpath("(//*[@data-testid='result-title-a'])[1]");
    // TC4 Data //
    String TC4_search_Locator = "searchbox_input";
    String TC4_searchValue = "TestNG";
    String TC4_expectedResult = "TestNG Tutorial";
    String TC4_searchList = "//*[@id='searchbox_homepage']//li";
    int TC4_listResultNumber = 4;
    By TC4_BySearch_Element = By.id(TC4_search_Locator);
    By TC4_BySearch_List = By.xpath(TC4_searchList);
    // TC5 Data //
    String TC5_search_Locator = "searchbox_input";
    String TC5_searchValue = "Cucumber IO";
    String TC5_expectedLink = "https://www.linkedin.com";
    By TC5_BySearchElement = By.id(TC5_search_Locator);
    By TC5_BySearchButtonElement = By.cssSelector("button[aria-label='Search']");
    By TC5_BySecondResultElement = By.xpath("(//*[@data-testid='result-title-a'])[2]");
    // TC6 Data //
    String TC6_checkboxes_Locator = "//input[@type='checkbox']";
    By TC6_ByCheckBoxesElement = By.xpath(TC6_checkboxes_Locator);
    // TC7 Data //
    String TC7_Company_Locator = "//*[@id='customers']//td[contains(text(),'Ernst Handel')]";
    By TC7_ByCompanyElement = By.xpath(TC7_Company_Locator);
    String TC7_Country_Locator = "//*[@id='customers']//td[contains(text(),'Ernst Handel')]/../*[3]";
    By TC7_ByCountryElement = By.xpath(TC7_Country_Locator);
    String TC7_expectedCountryName = "Austria";
    String TC7_expectedCompanyName = "Ernst Handel";
    // TC8 Data //
    String TC8_fileName = "sampleImage.PNG";
    String TC8_filePath = "C:/Users/SherifHamdy/Documents/GitHub/AutomatestTasks/src/test/resources/images/" + TC8_fileName;
    By ByFileInputElement = By.id("file-upload");
    By ByFileSubmitElement = By.id("file-submit");
    By BySuccessMessageElement = By.id("uploaded-files");
    // TC9 Data //
    String TC9_draggable_Locator = "draggable";
    By TC9_BydraggableElement = By.id(TC9_draggable_Locator);
    String TC9_droppable_Locator = "droppable";
    By TC9_ByDroppableElement = By.id(TC9_droppable_Locator);

    public AutomatestTestCases() {
        codeBehindTCs = new CodeBehindTestcases();
    }

    @AfterMethod
    public void tearDown() {
        // codeBehindTCs.driver.quit();
        //codeBehindTCs.getDriver().quit();

        // Close the browser
        if (codeBehindTCs.driver != null) {
            codeBehindTCs.driver.quit();
        }
    }


    @Test
    public void TC1() {
        //●	Open Google Chrome
        //●	Navigate to [https://duckduckgo.com/]
        //●	Assert that the page title is [Google]
        //●	Close Google Chrome
        //-----Steps-----------------------------------------
        codeBehindTCs.openBrowser(CodeBehindTestcases.myBrowsersList.CHROME);
        codeBehindTCs.openURL(URL_duck);

        //Assertion
        Assert.assertEquals(codeBehindTCs.getPageTitle(), TC1_expectedPageTitle);

    }


    @Test
    public void TC2() {
        //●	Open Google Chrome
        //●	Navigate to [https://duckduckgo.com/]
        //●	Assert that the DuckDuckGo logo is displayed
        //●	Close Google Chrome
        //-----Steps-----------------------------------------
        codeBehindTCs.openBrowser(CodeBehindTestcases.myBrowsersList.CHROME);
        codeBehindTCs.openURL(URL_duck);

        //Assertion
        Assert.assertTrue(codeBehindTCs.element_isDisplayed(TC2_ByLogoElement));
        System.out.println("Test Passed! Logo is Displayed");

    }


    @Test
    public void TC3() {
        //●	Open Google Chrome
        //●	Navigate to [https://duckduckgo.com/]
        //●	Search for [Selenium WebDriver]
        //●	Assert that the link of the first result is [https://www.selenium.dev/documentation/webdriver/]
        //●	Close Google Chrome
        //-----Steps-----------------------------------------
        codeBehindTCs.openBrowser(CodeBehindTestcases.myBrowsersList.CHROME);
        codeBehindTCs.openURL(URL_duck);
        codeBehindTCs.element_write(TC3_BySearchElement, TC3_searchValue);
        codeBehindTCs.element_click(TC3_BySearchButtonElement);

        // Assertion
        codeBehindTCs.element_wait(TC3_ByFirstResultElement);
        String actualLink = codeBehindTCs.element_getDomAttribute(TC3_ByFirstResultElement, "href");

        Assert.assertEquals(actualLink, TC3_expectedLink, "First result link doesn't match expected");
        System.out.println("Test Passed! Actual -> " + actualLink + " & Expected -> " + TC3_expectedLink);
    }

    @Test
    public void TC4() {
        //●	Open Mozilla Firefox
        //●	Navigate to [https://duckduckgo.com/]
        //●	Search for [TestNG]
        //●	Assert that the text of the fourth result is [TestNG Tutorial]
        //●	Close Mozilla Firefox
        //-----Steps-----------------------------------------
        codeBehindTCs.openBrowser(CodeBehindTestcases.myBrowsersList.FIREFOX);
        codeBehindTCs.openURL(URL_duck);
        codeBehindTCs.element_click(TC4_BySearch_Element);
        codeBehindTCs.element_write(TC4_BySearch_Element, TC4_searchValue);
        codeBehindTCs.element_wait(TC4_BySearch_List);

        // Assertion
        String actualResult = codeBehindTCs.element_selectFromList(TC4_BySearch_List, TC4_listResultNumber);
        Assert.assertEquals(actualResult, TC4_expectedResult, "List result value NOT Matched, The expected is ->  " + TC4_expectedResult + " but The Actual is ->  " + actualResult);
        System.out.println("Test passed! First result link matches expected.");

    }

    @Test
    public void TC5() {
        //●	Open Google Chrome
        //●	Navigate to [https://duckduckgo.com/]
        //●	Search for [Cucumber IO]
        //●	Assert that the link of the second result contains [https://www.linkedin.com]
        //●	Close Google Chrome
        //-----Steps-----------------------------------------
        codeBehindTCs.openBrowser(CodeBehindTestcases.myBrowsersList.CHROME);
        codeBehindTCs.openURL(URL_duck);
        codeBehindTCs.element_write(TC5_BySearchElement, TC5_searchValue);
        codeBehindTCs.element_click(TC5_BySearchButtonElement);

        // Assert
        codeBehindTCs.element_wait(TC5_BySecondResultElement);
        String actualLink = codeBehindTCs.element_getDomAttribute(TC5_BySecondResultElement, "href");

        Assert.assertTrue(actualLink.contains(TC5_expectedLink), "Second result link NOT Matched, The expected is " + TC5_expectedLink + " The Actual is " + actualLink);
        System.out.println("Test passed! Second result link matches expected.");


    }

    @Test
    public void TC6() {
        //●	Open Google Chrome
        //●	Navigate to [http://the-internet.herokuapp.com/checkboxes]
        //●	Check Checkbox 1
        //●	Assert that both Checkboxes are checked
        //●	Close Google Chrome
        //-----Steps-----------------------------------------
        codeBehindTCs.openBrowser(CodeBehindTestcases.myBrowsersList.CHROME);
        codeBehindTCs.openURL(URL_heroCheckBox);
        // Assertion
        Assert.assertTrue(codeBehindTCs.element_checkBoxIsChecked(TC6_ByCheckBoxesElement, 1), "First checkbox should be checked");
        Assert.assertTrue(codeBehindTCs.element_checkBoxIsChecked(TC6_ByCheckBoxesElement, 2), "Second checkbox should be checked");
        System.out.println("Test passed! FIRST & SECOND Checkboxes are checked.");

    }

    @Test
    public void TC7() {
        //●	Open Google Chrome
        //●	Navigate to [https://www.w3schools.com/html/html_tables.asp]
        //●	Assert that the Country for the Company [Ernst Handel] is [Austria]
        //●	Close Google Chrome
        //-----Steps-----------------------------------------
        codeBehindTCs.openBrowser(CodeBehindTestcases.myBrowsersList.CHROME);
        codeBehindTCs.openURL(URL_w3_htmlTable);
        // Assertion
        String actualCountryResult = codeBehindTCs.validateCountryForTheCompany(TC7_ByCountryElement, TC7_ByCompanyElement, TC7_expectedCompanyName);
        Assert.assertEquals(actualCountryResult, TC7_expectedCountryName);

        System.out.println("Test passed! First result link matches expected.." + "Actual Country Name -> " + actualCountryResult + " ,  Expected Company Name -> " + TC7_expectedCompanyName + " & Expected Country Name -> " + TC7_expectedCountryName);

    }

    @Test
    public void TC8() {
        //●	Open Google Chrome
        //●	Navigate to [http://the-internet.herokuapp.com/upload]
        //●	Upload a small image file
        //●	Assert that the file was uploaded successfully
        //-----Steps-----------------------------------------
        codeBehindTCs.openBrowser(CodeBehindTestcases.myBrowsersList.CHROME);
        codeBehindTCs.openURL(URL_heroUpload);
        // Assertion
        String actualUploadedFileName = codeBehindTCs.element_uploadDocument(TC8_filePath, ByFileInputElement, ByFileSubmitElement, BySuccessMessageElement);
        //Assert.assertTrue(uploadedFileName.equals(TC8_fileName),"File upload failed. Actual -> " + uploadedFileName + " & Expected -> " + TC8_fileName);
        Assert.assertEquals(TC8_fileName, actualUploadedFileName, "File upload failed. Actual -> " + actualUploadedFileName + " & Expected -> " + TC8_fileName);

        System.out.println("File uploaded successfully: " + actualUploadedFileName);
    }

    @Test
    public void TC9()
    {
        String URL_jqueryuiDragDrop ="https://jqueryui.com/resources/demos/droppable/default.html";

        //●	Open Google Chrome
        //●	Navigate to [https://jqueryui.com/resources/demos/droppable/default.html]
        //●	Drag [Drag me to my target] and drop it on [Drop here]
        //●	Assert that the text has been changed to [Dropped!]

        //-----Steps-----------------------------------------
        codeBehindTCs.openBrowser(CodeBehindTestcases.myBrowsersList.CHROME);
        codeBehindTCs.openURL(URL_jqueryuiDragDrop);

        String droppableText = codeBehindTCs.element_DragDrop(TC9_BydraggableElement,TC9_ByDroppableElement);

        Assert.assertEquals(droppableText, "Dropped!",
                "Drag and drop failed. Expected text 'Dropped!' but found: " + droppableText);

        System.out.println("Drag and drop successful. Text changed to: " + droppableText);

    }
}

