package lesson.two.task4.tests;

import lesson.two.task4.model.ContactData;
import lesson.two.task4.model.Contacts;
import lesson.two.task4.model.GroupData;
import lesson.two.task4.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactsAddingToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.contact().returnToHome();
        Groups groups = app.db().groups();
        String groupName = "test_group_with_adding_contact";
        if (groups.size() == 0 || getFalseGroupExists(groups, groupName)) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(groupName));
        }
        if (getFalseContactWithoutGroupExists(app.db().contacts(), groupName)) {
            app.contact().create(new ContactData().withFirstName("FirstName_AddingToGroup").withLastName("LastName_AddingToGroup").withAddress("Address_AddingToGroup").withEmail("email_addingtogroup@gmail.com").withHomePhone("89995554488"));
        }
    }

    @Test
    public void testContactAddingToGroup() {
        app.contact().returnToHome();
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        String groupName = "test_group_with_adding_contact";
        GroupData groupData = getGroupByName(groups, groupName);
        for (ContactData contact : contacts) {
            Groups contactGroups = contact.getGroups();
            if (getFalseGroupExists(contactGroups, groupName)) {
                assert groupData != null;
                app.contact().addContactToGroup(contact, groupData);
                app.contact().returnToHome();
            }
            assert groupData != null;
            GroupData group = app.db().groupsById(groupData.getId());
            ContactData contactWithGroup = app.db().contactsById(contact.getId());
            Assert.assertTrue(contactWithGroup.getGroups().contains(group) && group.getContacts().contains(contactWithGroup));
        }
    }

    private boolean getFalseContactWithoutGroupExists(Contacts contacts, String name) {
        for (ContactData contact : contacts) {
            if (getFalseGroupExists(contact.getGroups(), name)) {
                return false;
            }
        }
        return true;
    }

    private GroupData getGroupByName(Groups groups, String name) {
        for (GroupData group : groups) {
            if (group.getName().equalsIgnoreCase(name)) {
                return group;
            }
        }
        return null;
    }

    private boolean getFalseGroupExists(Groups groups, String name) {
        for (GroupData group : groups) {
            if (group.getName().equalsIgnoreCase(name)) {
                return false;
            }
        }
        return true;
    }
}
