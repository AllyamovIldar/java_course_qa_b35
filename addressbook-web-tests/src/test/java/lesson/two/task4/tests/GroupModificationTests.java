package lesson.two.task4.tests;

import lesson.two.task4.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("some test group from recorder", "some header from recorder (test existing)", "some footer from recorder"));
        }
    }

    @Test
    public void testGroupModification() {
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        GroupData group = new GroupData(before.get(index).getId(), "Modified group name", "some header from recorder (test existing)", "Modified group footer");
        app.group().modify(index, group);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
