package lesson.eight.task17.mantis.tests;

import lesson.eight.task17.mantis.model.MailMessage;
import lesson.eight.task17.mantis.model.UserData;
import lesson.eight.task17.mantis.model.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordUpdatingTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void ensurePrecondition() throws IOException {
        app.mail().start();
        if (app.db().users().size() == 0) {
            long now = System.currentTimeMillis();
            String userName = String.format("user%s", now);
            String password = "password";
            String mail = String.format("user%s@localhost.localadmin", now);
            app.registration().start(userName, mail);
            List<MailMessage> mailMessageList = app.mail().waitForMail(2, 10000);
            String confirmationLink = app.user().findConfirmationLink(mailMessageList, mail);
            app.registration().finish(confirmationLink, password);
        }
    }

    @Test
    public void testPasswordUpdate() throws IOException {
        Users users = app.db().users();
        UserData user = users.iterator().next();
        app.user().logByAdmin();
        app.user().updatingPassword(user);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.user().findConfirmationLink(mailMessages, user.getEmail());
        String newPassword = "test";
        app.user().confirmPasswordUpdateFromEmailLink(user.getUserName(), confirmationLink, newPassword);
        assertTrue(app.newSession().Login(user.getUserName(), newPassword));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}