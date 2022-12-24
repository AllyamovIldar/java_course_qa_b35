package lesson.two.task4.tests;

import lesson.two.task4.model.ContactData;
import lesson.two.task4.model.GroupData;
import lesson.two.task4.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactsDeletingFromGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.contact().returnToHome();
        Groups groups = app.db().groups();
        String groupName = "test_group_to_deleting_binding_contact";
        if (groups.size() == 0 || getFalseGroupExists(groups, groupName)) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(groupName));
            groups = app.db().groups();
        }
        if (getFalseContactExists(groups, groupName)) {
            app.contact().create(new ContactData().withFirstName("FirstName_DeletingFromGroup").withLastName("LastName_DeletingFromGroup").withAddress("Address_DeletingFromGroup").withEmail("email_deletingfromgroup@gmail.com").withHomePhone("89157774411").addedToGroup(getGroupByName(groups, groupName)));
        }
    }

    @Test
    public void testContactDeletingToGroup() {
        app.contact().returnToHome();
        Groups groups = app.db().groups();
        String groupName = "test_group_to_deleting_binding_contact";
        GroupData groupData = getGroupByName(groups, groupName);
        assert groupData != null;
        ContactData contact = groupData.getContacts().stream().findFirst().orElse(null);
        assert contact != null;
        app.contact().deleteContactFromGroup(contact, groupData);
        app.contact().returnToHome();
        GroupData group = app.db().groupById(groupData.getId());
        ContactData contactWithoutGroup = app.db().contactsById(contact.getId());
        Assert.assertFalse(contactWithoutGroup.getGroups().contains(group) && group.getContacts().contains(contactWithoutGroup));
    }

    private boolean getFalseContactExists(Groups groups, String name) {
        for (GroupData group : groups) {
            if (group.getName().equalsIgnoreCase(name) && group.getContacts().size() > 0) {
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
