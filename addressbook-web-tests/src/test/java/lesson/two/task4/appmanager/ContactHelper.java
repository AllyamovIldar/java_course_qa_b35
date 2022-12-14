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
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
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
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return new Contacts(contactCache);
    }
}
