package lesson.two.task4.tests;

import lesson.two.task4.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase {
    @Test
    public void testContactDeletion() {
        app.getContactHelper().returnToHome();
        if (!app.getContactHelper().isThereAnContact()) {
            app.getContactHelper().createContact(new ContactData("FirstNameExample", "MiddleNameExample", "LastNameExample", "TestCompany", "TestAddress", "84951112233", "89995554433", "88009876543", "testmail1@mail.com", "testmail2@email.com", "testmail3@mailtest.com"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initContactDeletion();
        app.getContactHelper().dialogAccept();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}