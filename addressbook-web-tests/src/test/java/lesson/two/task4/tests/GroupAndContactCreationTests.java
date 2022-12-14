package lesson.two.task4.tests;

import lesson.two.task4.model.ContactData;
import lesson.two.task4.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;

public class GroupAndContactCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.goTo().groupPage();
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData()
                .withName("another test group from recorder").withHeader("some header from recorder (test existing)").withFooter("some footer from recorder");
        app.group().create(group);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

    @Test
    public void testContactCreation() throws Exception {
        app.contact().returnToHome();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData()
                .withFirstname("FirstNameExample").withMiddlename("MiddleNameExample").withLastname("LastNameExample").withCompany("TestCompany").withAddress("TestAddress").withHomePhone("84951112233").withMobilePhone("89995554433").withWorkPhone("88009876543").withEmail("testmail1@mail.com").withEmail2("testmail2@email.com").withEmail3("testmail3@mailtest.com");
        app.contact().create(contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
