package lesson.two.task4.tests;

import com.google.common.base.Strings;
import lesson.two.task4.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressEmailsPhonesTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().returnToHome();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("FirstNameExample").withMiddleName("MiddleNameExample").withLastName("LastNameExample").withCompany("TestCompany").withAddress("TestAddress").withHomePhone("8 495 111 22 33").withMobilePhone("+79995554433").withWorkPhone("8-800-987-65-43").withEmail("testmail1@mail.com").withEmail2("testmail2@email.com").withEmail3("testmail3@mailtest.com"));
        }
        app.contact().returnToHome();
    }

    @Test
    public void testContactAddressEmailsPhones() {
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(Strings.nullToEmpty(contactInfoFromEditForm.getAddress())));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactAddressEmailsPhonesTests::cleanedEmails)
                .collect(Collectors.joining("\n"));
    }


    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactAddressEmailsPhonesTests::cleanedPhones)
                .collect(Collectors.joining("\n"));
    }

    public static String cleanedPhones(String s) {
        return s.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

    public static String cleanedEmails(String s) {
        return s.replaceAll("^\\s", "").replaceAll("\\s$", "");
    }
}
