package lesson.two.task4.tests;

import lesson.two.task4.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Comparator;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getContactHelper().returnToHome();
        if (!app.getContactHelper().isThereAnContact()) {
            app.getContactHelper().createContact(new ContactData("FirstNameExample", "MiddleNameExample", "LastNameExample", "TestCompany", "TestAddress", "84951112233", "89995554433", "88009876543", "testmail1@mail.com", "testmail2@email.com", "testmail3@mailtest.com"));
        }
        app.getContactHelper().returnToHome();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initContactModification(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"FirstNameExample1", "MiddleNameExample1", "LastNameExample1", "TestCompany1", "TestAddress1", "84951112231", "89995554431", "88009876541", "test1mail1@mail.com", "test1mail2@email.com", "test1mail3@mailtest.com");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }


}
