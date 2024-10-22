import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Leave {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "\"C:\\Windows\\System32\\drivers\\msedgedriver.exe\"");  // Corrected here
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to OrangeHRM demo login page
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test
    public void testAddLeaveEntitlement() throws InterruptedException {
        // Log in
        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        username.sendKeys("Admin");
        password.sendKeys("admin123");
        loginButton.click();

        // Navigate to the Leave Entitlement page
        Thread.sleep(2000);  // Replace with explicit wait if necessary
        WebElement leaveMenu = driver.findElement(By.xpath("//span[text()='Leave']"));
        leaveMenu.click();

        Thread.sleep(2000); // Wait for menu to expand
        WebElement entitlementsMenu = driver.findElement(By.xpath("//a[contains(@href, '/leave/addLeaveEntitlement')]"));
        entitlementsMenu.click();

        // Fill in the form
        Thread.sleep(2000);
        WebElement employeeNameField = driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
        employeeNameField.sendKeys("Linda Anderson");

        Thread.sleep(2000);
        WebElement employeeOption = driver.findElement(By.xpath("//div[@role='option']//span[text()='Linda Anderson']"));
        employeeOption.click();

        WebElement leaveTypeDropdown = driver.findElement(By.xpath("//div[contains(@class, 'oxd-select-text')]"));
        leaveTypeDropdown.click();

        WebElement leaveTypeOption = driver.findElement(By.xpath("//span[text()='CAN - Bereavement']"));
        leaveTypeOption.click();

        WebElement entitlementField = driver.findElement(By.xpath("//input[@name='entitlement[entitlement]']"));
        entitlementField.sendKeys("10");

        // Submit the form
        WebElement saveButton = driver.findElement(By.xpath("//button[@type='submit']"));
        saveButton.click();

        // Wait for the page to process the submission
        Thread.sleep(2000);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
