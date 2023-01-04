package lesson.eight.task17.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends SessionHelper {
    public NavigationHelper(WebDriver wd) {
        super((ApplicationManager) wd);
    }

    public void settingUserPage() {
        click(By.xpath("//*[@id=\"menu-toggler\"]"));
        click(By.xpath("//a[contains(@href, '/manage_overview_page.php')]"));
        click(By.xpath("//a[contains(@href, '/manage_user_page.php')]"));
    }
}
