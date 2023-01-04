package lesson.eight.task17.mantis.appmanager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {

    public SessionHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        type(By.name("user"), username);
        click(By.xpath("//input[@type='submit']"));
        type(By.name("pass"), password);
        click(By.xpath("//input[@type='Login']"));
    }
}
