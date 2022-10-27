package lesson.two.task4.appmanager;

import lesson.two.task4.model.AddressData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitAddressCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillAddressForm(AddressData addressData) {
        type(By.name("firstname"), addressData.getFirstname());
        type(By.name("middlename"), addressData.getMiddlename());
        type(By.name("lastname"), addressData.getLastname());
        type(By.name("company"), addressData.getCompany());
        type(By.name("address"), addressData.getAddress());
        type(By.name("home"), addressData.getHomePhone());
        type(By.name("mobile"), addressData.getMobilePhone());
        type(By.name("work"), addressData.getWorkPhone());
        type(By.name("email"), addressData.getEmail());
        type(By.name("email2"), addressData.getEmail2());
        type(By.name("email3"), addressData.getEmail3());
    }

    public void gotoAddressPage() {
        click(By.linkText("add new"));
    }

    public void initAddressModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitAddressModification() {
        click(By.name("update"));
    }

    public void selectAddress() {
        click(By.name("selected[]"));
    }

    public void initAddressDeletion() {
        click(By.xpath("//img[@alt='Details']"));
        click(By.name("modifiy"));
        click(By.xpath("//*[@id=\"content\"]/form[2]/input[2]"));
    }
}
