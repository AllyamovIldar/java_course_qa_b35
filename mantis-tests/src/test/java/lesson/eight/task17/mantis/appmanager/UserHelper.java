package lesson.eight.task17.mantis.appmanager;

import lesson.eight.task17.mantis.model.MailMessage;
import lesson.eight.task17.mantis.model.UserData;
import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;

import java.util.List;

public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager app) {
        super(app);
    }

    public String findConfirmationLink(List<MailMessage> mailMessages, String mail) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(mail)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    public void logByAdmin() {
        wd.get(app.getProperty("web.baseUrl"));
        type(By.name("username"), app.getProperty("web.adminLogin"));
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(By.cssSelector("input[type='submit']"));
    }

    public void updatingPassword(UserData user) {
        app.goTo().settingUserPage();
        click(By.xpath("//a[contains(@href,'user_id=" + user.getId() + "')]"));
        click(By.xpath("//input[@value='Сбросить пароль']"));
    }

    public void confirmPasswordUpdateFromEmailLink(String userName, String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("realname"), userName);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//*[@id=\"account-update-form\"]/fieldset/span/button/span"));
    }
}