package lesson.two.task4.tests;

import lesson.two.task4.model.GroupData;
import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {
    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("some test group from recorder", "some header from recorder (test existing)", "some footer from recorder"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupDeletion();
        app.getGroupHelper().returnToGroupPage();
    }
}
