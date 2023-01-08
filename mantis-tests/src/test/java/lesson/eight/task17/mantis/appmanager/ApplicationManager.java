package lesson.eight.task17.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

public class ApplicationManager implements WebDriver {
    private final Properties properties;
    private WebDriver wd;
    private Browser browser;

    private RegistrationHelper registrationHelper;

    private FtpHelper ftp;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper;
    private NavigationHelper navigationHelper;
    private UserHelper userHelper;
    private SessionHelper sessionHelper;
    private DbHelper dbHelper;
    private SoapHelper soapHelper;

    public ApplicationManager(Browser browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        dbHelper = new DbHelper();
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;

    }

    public SessionHelper sessionHelper() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

    public UserHelper user() {
        if (userHelper == null) {
            userHelper = new UserHelper(this);
        }
        return userHelper;
    }

    public FtpHelper ftp() {
        if (ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public WebDriver getDriver() {
        if (wd == null) {
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

        }
        return wd;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesHelper james() {
        if (jamesHelper == null) {
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }

    public DbHelper db() {
        return dbHelper;
    }

    public NavigationHelper goTo() {
        if (navigationHelper == null) {
            navigationHelper = new NavigationHelper(this);
        }
        return navigationHelper;
    }

    public SoapHelper soap() {
        if (soapHelper == null) {
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }

    @Override
    public void get(String url) {
    }

    @Override
    public String getCurrentUrl() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return null;
    }

    @Override
    public WebElement findElement(By by) {
        return null;
    }

    @Override
    public String getPageSource() {
        return null;
    }

    @Override
    public void close() {
    }

    @Override
    public void quit() {
    }

    @Override
    public Set<String> getWindowHandles() {
        return null;
    }

    @Override
    public String getWindowHandle() {
        return null;
    }

    @Override
    public TargetLocator switchTo() {
        return null;
    }

    @Override
    public Navigation navigate() {
        return null;
    }

    @Override
    public Options manage() {
        return null;
    }
}
