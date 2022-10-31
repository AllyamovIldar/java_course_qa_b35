package lesson.two.task4.tests;

import lesson.two.task4.model.AddressData;
import org.testng.annotations.Test;

public class AddressDeletionTests extends TestBase {
    @Test
    public void testAddressDeletion() {
        if (!app.getContactHelper().isThereAnAddress()) {
            app.getContactHelper().createAddress(new AddressData("FirstNameExample", "MiddleNameExample", "LastNameExample", "TestCompany", "TestAddress", "84951112233", "89995554433", "88009876543", "testmail1@mail.com", "testmail2@email.com", "testmail3@mailtest.com"));
        }
        app.getContactHelper().selectAddress();
        app.getContactHelper().initAddressDeletion();
        app.getNavigationHelper().gotoHomePage();
    }
}
