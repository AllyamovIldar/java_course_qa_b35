package lesson.two.task4.appmanager;

import lesson.two.task4.model.ContactData;
import lesson.two.task4.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHome() {
        click(By.linkText("home"));
    }


    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("email"), contactData.getEmail());
        /*
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("company"), contactData.getCompany());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        attach(By.name("photo"), contactData.getPhoto());
        */
    }

    public void gotoContactPage() {
        click(By.linkText("add new"));
    }

    public void initContactModification(int id) {
        wd.findElement(By.xpath("//tr[@name='entry']/td[8]/a[@href='edit.php?id=" + id + "']")).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initContactDeletion() {
        this.click(By.xpath("//input[@value='Delete']"));
    }

    public void dialogAccept() {
        this.wd.switchTo().alert().accept();
    }

    public void create(ContactData contact) {
        gotoContactPage();
        fillContactForm(contact);
        submitContactCreation();
        contactCache = null;
        returnToHome();
    }

    public void modify(ContactData contact, int id) {
        initContactModification(id);
        fillContactForm(contact);
        submitContactModification();
        contactCache = null;
        returnToHome();
    }

    public void delete(ContactData group) {
        selectContactById(group.getId());
        initContactDeletion();
        dialogAccept();
        contactCache = null;
        returnToHome();
    }

    public boolean isThereAnContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        Contacts contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String address = element.findElement(By.xpath(".//td[4]")).getText();
            String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
            String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3).withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone);
    }

    private void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }
}
