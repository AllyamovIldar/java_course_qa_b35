package lesson.nine.task20.rest.tests;

import lesson.nine.task20.rest.appmanager.TestBase;
import lesson.nine.task20.rest.model.Issue;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {
    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(59);
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("Test issue 123").withDescription("New test issue 123");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }
}
