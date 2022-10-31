package lesson.two.task4.tests;

import lesson.two.task4.model.AddressData;
import lesson.two.task4.model.GroupData;
import org.testng.annotations.Test;

public class AddressModificationTests extends TestBase {
    @Test
    public void testAddressModification() {
        if (!app.getContactHelper().isThereAnAddress()) {
            app.getContactHelper().createAddress(new AddressData("FirstNameExample", "MiddleNameExample", "LastNameExample", "TestCompany", "TestAddress", "84951112233", "89995554433", "88009876543", "testmail1@mail.com", "testmail2@email.com", "testmail3@mailtest.com"));
        }
        app.getContactHelper().selectAddress();
        app.getContactHelper().initAddressModification();
        app.getContactHelper().fillAddressForm(new AddressData("FirstNameExample1", "MiddleNameExample1", "LastNameExample1", "TestCompany1", "TestAddress1", "84951112231", "89995554431", "88009876541", "test1mail1@mail.com", "test1mail2@email.com", "test1mail3@mailtest.com"));
        app.getContactHelper().submitAddressModification();
        app.getContactHelper().returnToHomePage();
    }
}
