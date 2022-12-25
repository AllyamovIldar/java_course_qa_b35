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
        Contacts contacts = app.db().contacts();
        String groupName = "test_group_with_adding_contact";
        if (groups.size() == 0 || getFalseGroupExists(groups, groupName)) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(groupName));
        }
        if (contacts.size() == 0) {
            contactCreateTest();
        } else {
            int countWithoutLinks = 0;
            for (ContactData contact : contacts) {
                boolean contactWithoutLinkedGroups = contact.getGroups().size() == 0;
                if (contactWithoutLinkedGroups) {
                    countWithoutLinks++;
                }
            }
            if (countWithoutLinks == 0) {
                contactCreateTest();
            }
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
            boolean contactSizeLessOne = contact.getGroups().size() < 1;
            if (getFalseGroupExists(contactGroups, groupName) && contactSizeLessOne) {
                assert groupData != null;
                app.contact().addContactToGroup(contact, groupData);
                app.contact().returnToHome();
                GroupData group = app.db().groupById(groupData.getId());
                ContactData contactWithGroup = app.db().contactsById(contact.getId());
                Assert.assertTrue(contactWithGroup.getGroups().contains(group) && group.getContacts().contains(contactWithGroup));
                break;
            }
        }
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

    private static void contactCreateTest() {
        app.contact().create(new ContactData().withFirstName("FirstName_AddingToGroup").withLastName("LastName_AddingToGroup").withAddress("Address_AddingToGroup").withEmail("email_addingtogroup@gmail.com").withHomePhone("89995554488"));
    }
}
