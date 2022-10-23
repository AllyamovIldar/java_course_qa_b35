package lesson.two.task4;

import java.time.Duration;

import org.testng.annotations.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class GroupCreationTests {
    private WebDriver wb;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        // Драйвер для Chrome взять отсюда (https://chromedriver.storage.googleapis.com/index.html?path=106.0.5249.61/) и закинуть в папку по пути переменной среды PATH, например в эту (C:\Windows\System32).
        System.setProperty("webdriver.chrome.driver", "C:\\Windows\\System32\\chromedriver.exe");
        wb = new ChromeDriver();
        wb.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wb.get("http://localhost/addressbook/");
        login("admin", "secret");
    }

    private void login(String username, String password) {
        wb.findElement(By.name("user")).click();
        wb.findElement(By.name("user")).clear();
        wb.findElement(By.name("user")).sendKeys(username);
        wb.findElement(By.name("pass")).click();
        wb.findElement(By.name("pass")).clear();
        wb.findElement(By.name("pass")).sendKeys(password);
        wb.findElement(By.xpath("//input[@value='Login']")).click();
    }

    @Test
    public void testGroupCreation() throws Exception {
        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("some test group from recorder", "some header from recorder", "some footer from recorder"));
        submitGroupCreation();
        returnToGroupPage();
    }

    private void returnToGroupPage() {
        wb.findElement(By.linkText("group page")).click();
    }

    private void submitGroupCreation() {
        wb.findElement(By.name("submit")).click();
    }

    private void fillGroupForm(GroupData groupData) {
        wb.findElement(By.name("group_name")).click();
        wb.findElement(By.name("group_name")).clear();
        wb.findElement(By.name("group_name")).sendKeys(groupData.getName());
        wb.findElement(By.name("group_header")).click();
        wb.findElement(By.name("group_header")).clear();
        wb.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        wb.findElement(By.name("group_footer")).click();
        wb.findElement(By.name("group_footer")).clear();
        wb.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    private void initGroupCreation() {
        wb.findElement(By.name("new")).click();
    }

    private void gotoGroupPage() {
        wb.findElement(By.linkText("groups")).click();
        wb.get("http://localhost/addressbook/group.php");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        wb.findElement(By.linkText("Logout")).click();
        wb.quit();
    }

    private boolean isElementPresent(By by) {
        try {
            wb.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            wb.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
