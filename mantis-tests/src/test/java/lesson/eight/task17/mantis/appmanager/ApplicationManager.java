package lesson.eight.task17.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;

public class ApplicationManager {
    private final Properties properties;
    WebDriver wd;
    private Browser browser;

    public ApplicationManager(Browser browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        if (Objects.equals(browser, Browser.CHROME)) {
            // Драйвер для Chrome взять отсюда (https://chromedriver.storage.googleapis.com/index.html?path=106.0.5249.61/) и закинуть в папку по пути переменной среды PATH, например в эту (C:\Windows\System32).
            System.setProperty("webdriver.chrome.driver", "C:\\Windows\\System32\\chromedriver.exe");
            wd = new ChromeDriver();
        } else if (Objects.equals(browser, Browser.FIREFOX)) {
            // Драйвер для Firefox взять отсюда (https://github.com/mozilla/geckodriver/releases) и закинуть в папку по пути переменной среды PATH, например в эту (C:\Windows\System32).
            System.setProperty("webdriver.gecko.driver", "C:\\Windows\\System32\\geckodriver.exe");
            wd = new FirefoxDriver();
        }
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wd.get(properties.getProperty("web.baseUrl"));
        // login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    /*
    public void login(String username, String password) {
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }
    */
    public void stop() {
        // wd.findElement(By.linkText("Logout")).click();
        wd.quit();
    }
}
