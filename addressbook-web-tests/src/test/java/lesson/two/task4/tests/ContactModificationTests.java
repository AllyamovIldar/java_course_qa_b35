package lesson.two.task4.tests;

import lesson.two.task4.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getContactHelper().returnToHome();
        if (!app.getContactHelper().isThereAnContact()) {
            app.getContactHelper().createContact(new ContactData("FirstNameExample", "MiddleNameExample", "LastNameExample", "TestCompany", "TestAddress", "84951112233", "89995554433", "88009876543", "testmail1@mail.com", "testmail2@email.com", "testmail3@mailtest.com"));
        }
        app.getContactHelper().returnToHome();
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(before - 1);
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("FirstNameExample1", "MiddleNameExample1", "LastNameExample1", "TestCompany1", "TestAddress1", "84951112231", "89995554431", "88009876541", "test1mail1@mail.com", "test1mail2@email.com", "test1mail3@mailtest.com"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHome();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
    }
}
