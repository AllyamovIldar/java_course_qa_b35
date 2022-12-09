package lesson.two.task4.tests;

import lesson.two.task4.model.AddressData;
import lesson.two.task4.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashSet;
import java.util.List;

public class GroupAndAddressCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("another test group from recorder", "some header from recorder (test existing)", "some footer from recorder");
        app.getGroupHelper().createGroup(group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);

        group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

    @Test
    public void testAddressCreation() throws Exception {
        //List<AddressData> before = app.getContactHelper().getContactList();
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().createAddress(new AddressData("FirstNameExample", "MiddleNameExample", "LastNameExample", "TestCompany", "TestAddress", "84951112233", "89995554433", "88009876543", "testmail1@mail.com", "testmail2@email.com", "testmail3@mailtest.com"));
        //List<AddressData> after = app.getContactHelper().getContactList();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before + 1);
        // Assert.assertEquals(after.size(), before.size() + 1);
    }

}
