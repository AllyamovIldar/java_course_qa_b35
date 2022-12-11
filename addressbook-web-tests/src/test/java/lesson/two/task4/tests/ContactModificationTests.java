package lesson.two.task4.tests;

import lesson.two.task4.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().returnToHome();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData("FirstNameExample", "MiddleNameExample", "LastNameExample", "TestCompany", "TestAddress", "84951112233", "89995554433", "88009876543", "testmail1@mail.com", "testmail2@email.com", "testmail3@mailtest.com"));
        }
        app.contact().returnToHome();
    }

    @Test
    public void testContactModification() {
        int before = app.contact().getContactCount();
        ContactData contact = new ContactData("FirstNameExample1", "MiddleNameExample1", "LastNameExample1", "TestCompany1", "TestAddress1", "84951112231", "89995554431", "88009876541", "test1mail1@mail.com", "test1mail2@email.com", "test1mail3@mailtest.com");
        int index = before - 1;
        app.contact().modify(contact, index);
        int after = app.contact().getContactCount();
        Assert.assertEquals(after, before);
    }
}