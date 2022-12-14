package lesson.two.task4.tests;

import lesson.two.task4.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().returnToHome();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstname("FirstNameExample").withMiddlename("MiddleNameExample").withLastname("LastNameExample").withCompany("TestCompany").withAddress("TestAddress").withHomePhone("84951112233").withMobilePhone("89995554433").withWorkPhone("88009876543").withEmail("testmail1@mail.com").withEmail2("testmail2@email.com").withEmail3("testmail3@mailtest.com"));
        }
        app.contact().returnToHome();
    }

    @Test
    public void testContactModification() {
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData().withFirstname("FirstNameExample1").withMiddlename("MiddleNameExample1").withLastname("LastNameExample1").withCompany("TestCompany1").withAddress("TestAddress1").withHomePhone("84951112231").withMobilePhone("89995554431").withWorkPhone("88009876541").withEmail("test1mail1@mail.com").withEmail2("test1mail2@email.com").withEmail3("test1mail3@mailtest.com");
        int index = before.size() - 1;
        app.contact().modify(contact, index);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}