package lesson.two.task4.tests;

import lesson.two.task4.model.GroupData;
import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {
    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupDeletion();
        app.getGroupHelper().returnToGroupPage();
    }
}
