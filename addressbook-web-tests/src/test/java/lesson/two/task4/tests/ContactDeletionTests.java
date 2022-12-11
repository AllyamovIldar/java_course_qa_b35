package lesson.two.task4.tests;

import lesson.two.task4.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.getContactHelper().returnToHome();
        if (!app.getContactHelper().isThereAnContact()) {
            app.getContactHelper().createContact(new ContactData("FirstNameExample", "MiddleNameExample", "LastNameExample", "TestCompany", "TestAddress", "84951112233", "89995554433", "88009876543", "testmail1@mail.com", "testmail2@email.com", "testmail3@mailtest.com"));
        }
        app.getContactHelper().returnToHome();
    }
    @Test
    public void testContactDeletion() {
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().returnToHome();
        app.getContactHelper().selectContact(before - 1);
        app.getContactHelper().initContactDeletion();
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
    }
}
