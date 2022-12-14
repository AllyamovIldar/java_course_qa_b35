package lesson.two.task4.tests;

import lesson.two.task4.model.ContactData;
import lesson.two.task4.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class GroupAndContactCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.goTo().groupPage();
        Set<GroupData> before = app.group().all();
        GroupData group = new GroupData().withName("another test group from recorder").withHeader("some header from recorder (test existing)").withFooter("some footer from recorder");
        app.group().create(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);
        Assert.assertEquals(before, after);
    }

    @Test
    public void testContactCreation() throws Exception {
        app.contact().returnToHome();
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("FirstNameExample").withMiddlename("MiddleNameExample").withLastname("LastNameExample").withCompany("TestCompany").withAddress("TestAddress").withHomePhone("84951112233").withMobilePhone("89995554433").withWorkPhone("88009876543").withEmail("testmail1@mail.com").withEmail2("testmail2@email.com").withEmail3("testmail3@mailtest.com");
        app.contact().create(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }

}
