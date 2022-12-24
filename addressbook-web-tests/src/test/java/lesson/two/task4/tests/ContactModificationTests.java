package lesson.two.task4.tests;

import lesson.two.task4.model.ContactData;
import lesson.two.task4.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().returnToHome();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstName("FirstNameExample").withMiddleName("MiddleNameExample").withLastName("LastNameExample").withCompany("TestCompany").withAddress("TestAddress").withHomePhone("84951112233").withMobilePhone("89995554433").withWorkPhone("88009876543").withEmail("testmail1@mail.com").withEmail2("testmail2@email.com").withEmail3("testmail3@mailtest.com"));
        }
        app.contact().returnToHome();
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        int modifiedId = modifiedContact.getId();
        ContactData contact = new ContactData().withId(modifiedId).withFirstName("FirstNameExample1").withMiddleName("MiddleNameExample1").withLastName("LastNameExample1").withCompany("TestCompany1").withAddress("TestAddress1").withHomePhone("84951112231").withMobilePhone("89995554431").withWorkPhone("88009876541").withEmail("test1mail1@mail.com").withEmail2("test1mail2@email.com").withEmail3("test1mail3@mailtest.com");
        app.contact().modify(contact, modifiedId);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUi();
    }
}