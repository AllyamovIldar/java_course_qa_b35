package lesson.two.task4.tests;

import lesson.two.task4.model.AddressData;
import org.testng.annotations.Test;

public class AddressDeletionTests extends TestBase {
    @Test
    public void testAddressDeletion() {
        app.getContactHelper().selectAddress();
        app.getContactHelper().initAddressDeletion();
        app.getNavigationHelper().gotoHomePage();
    }
}
