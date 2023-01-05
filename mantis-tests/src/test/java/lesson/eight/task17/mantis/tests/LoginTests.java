package lesson.eight.task17.mantis.tests;

import lesson.eight.task17.mantis.appmanager.HttpSession;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.Login("administrator", "root"));
        assertTrue(session.isLoggedInAs("administrator"));
    }
}