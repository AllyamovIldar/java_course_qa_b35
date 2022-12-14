package lesson.two.task4.appmanager;

import lesson.two.task4.model.ContactData;
import lesson.two.task4.model.Contacts;
import lesson.two.task4.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

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

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("email"), contactData.getEmail());
        /*
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("company"), contactData.getCompany());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        attach(By.name("photo"), contactData.getPhoto());
        */
        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertEquals(contactData.getGroups().size(), 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
        click(By.xpath("//div[@id='content']/form/input[21]"));

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
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void initContactDeletion() {
        this.click(By.xpath("//input[@value='Delete']"));
    }

    public void dialogAccept() {
        this.wd.switchTo().alert().accept();
    }

    public void create(ContactData contact) {
        gotoContactPage();
        fillContactForm(contact, true);
        // submitContactCreation();
        contactCache = null;
        returnToHome();
    }

    public void modify(ContactData contact, int id) {
        initContactModification(id);
        fillContactForm(contact, false);
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

    public void addContactToGroup(ContactData toBeAddContact, GroupData group) {
        selectContactById(toBeAddContact.getId());
        wd.findElement(By.name("to_group")).click();
        new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(group.getId()));
        submitAddContactToGroup();
    }

    public void deleteContactFromGroup(ContactData contact, GroupData groupDeletion) {
        selectGroupById(groupDeletion.getId());
        selectContactById(contact.getId());
        submitContactRemoveFromGroup();
    }

    private void submitContactRemoveFromGroup() {
        click(By.xpath("//input[@name='remove']"));
    }

    private void selectGroupById(int id) {
        wd.findElement(By.name("group")).click();
        new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(id));
    }

    private void submitAddContactToGroup() {
        click(By.xpath("//input[@value='Add to']"));
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
            contactCache.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname).withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones));
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
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname).withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3).withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone);
    }

    private void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }
}
