package testsPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TCs {

    @Test
    public void TC1() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("");

    }
}
