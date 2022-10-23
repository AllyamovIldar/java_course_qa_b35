package lesson.two.task4;

import java.time.Duration;

import org.testng.annotations.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class GroupAndAddressCreationTests {
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

    @Test
    public void testAddressCreation() throws Exception{
        gotoAddressPage();
        fillAddressFormNames(new AddressFormNamesData("FirstNameExample", "MiddleNameExample", "LastNameExample"));
        fillAddressFormCompany(new AddressFormCompanyData("TestCompany"));
        fillAddressFormAddress(new AddressFormAddressData("TestAddress"));
        fillAddressFormPhones(new AddressFormPhonesData("84951112233", "89995554433", "88009876543"));
        fillAddressFormEmails(new AddressFormEmailsData("testmail1@mail.com", "testmail2@email.com", "testmail3@mailtest.com"));
        submitAddressCreation();
        returnToHomePage();
    }

    private void returnToHomePage() {
        wb.findElement(By.linkText("home page")).click();
    }

    private void submitAddressCreation() {
        wb.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    private void fillAddressFormEmails(AddressFormEmailsData addressFormEmailsData) {
        wb.findElement(By.name("email")).click();
        wb.findElement(By.name("email")).clear();
        wb.findElement(By.name("email")).sendKeys(addressFormEmailsData.getEmail());
        wb.findElement(By.name("email2")).click();
        wb.findElement(By.name("email2")).clear();
        wb.findElement(By.name("email2")).sendKeys(addressFormEmailsData.getEmail2());
        wb.findElement(By.name("email3")).click();
        wb.findElement(By.name("email3")).clear();
        wb.findElement(By.name("email3")).sendKeys(addressFormEmailsData.getEmail3());
    }

    private void fillAddressFormPhones(AddressFormPhonesData addressFormPhonesData) {
        wb.findElement(By.name("home")).click();
        wb.findElement(By.name("home")).clear();
        wb.findElement(By.name("home")).sendKeys(addressFormPhonesData.getHomePhone());
        wb.findElement(By.name("mobile")).click();
        wb.findElement(By.name("mobile")).clear();
        wb.findElement(By.name("mobile")).sendKeys(addressFormPhonesData.getMobilePhone());
        wb.findElement(By.name("work")).click();
        wb.findElement(By.name("work")).clear();
        wb.findElement(By.name("work")).sendKeys(addressFormPhonesData.getWorkPhone());
    }

    private void fillAddressFormAddress(AddressFormAddressData addressFormAddressData) {
        wb.findElement(By.name("address")).click();
        wb.findElement(By.name("address")).clear();
        wb.findElement(By.name("address")).sendKeys(addressFormAddressData.getAddress());
    }

    private void fillAddressFormCompany(AddressFormCompanyData addressFormCompanyData) {
        wb.findElement(By.name("company")).click();
        wb.findElement(By.name("company")).clear();
        wb.findElement(By.name("company")).sendKeys(addressFormCompanyData.getCompany());
    }

    private void fillAddressFormNames(AddressFormNamesData addressFormNamesData) {
        wb.findElement(By.name("firstname")).click();
        wb.findElement(By.name("firstname")).clear();
        wb.findElement(By.name("firstname")).sendKeys(addressFormNamesData.getFirstname());
        wb.findElement(By.name("middlename")).click();
        wb.findElement(By.name("middlename")).clear();
        wb.findElement(By.name("middlename")).sendKeys(addressFormNamesData.getMiddlename());
        wb.findElement(By.name("lastname")).click();
        wb.findElement(By.name("lastname")).clear();
        wb.findElement(By.name("lastname")).sendKeys(addressFormNamesData.getLastname());
    }

    private void gotoAddressPage() {
        wb.findElement(By.linkText("add new")).click();
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
