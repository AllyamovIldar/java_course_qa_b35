package lesson.two.task4.tests;

import lesson.two.task4.model.AddressData;
import lesson.two.task4.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

public class GroupAndAddressCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().createGroup(new GroupData("some test group from recorder", "some header from recorder (test existing)", "some footer from recorder"));
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before + 1);
    }

    @Test
    public void testAddressCreation() throws Exception {
        app.getContactHelper().createAddress(new AddressData("FirstNameExample", "MiddleNameExample", "LastNameExample", "TestCompany", "TestAddress", "84951112233", "89995554433", "88009876543", "testmail1@mail.com", "testmail2@email.com", "testmail3@mailtest.com"));
    }

}
