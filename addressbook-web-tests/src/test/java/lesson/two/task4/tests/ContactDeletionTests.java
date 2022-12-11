package lesson.two.task4.tests;

import lesson.two.task4.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().returnToHome();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData("FirstNameExample", "MiddleNameExample", "LastNameExample", "TestCompany", "TestAddress", "84951112233", "89995554433", "88009876543", "testmail1@mail.com", "testmail2@email.com", "testmail3@mailtest.com"));
        }
        app.contact().returnToHome();
    }

    @Test
    public void testContactDeletion() {
        int before = app.contact().getContactCount();
        app.contact().returnToHome();
        int index = before - 1;
        app.contact().delete(index);
        int after = app.contact().getContactCount();
        Assert.assertEquals(after, before - 1);
    }
}
