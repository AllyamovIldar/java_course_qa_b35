package lesson.nine.task20.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import lesson.nine.task20.rest.appmanager.TestBase;
import lesson.nine.task20.rest.model.Issue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests extends TestBase {

    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured.basic("02276e82280489b4fa0cd56b59abad4c", "");
    }

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


    public Set<Issue> getIssues() {
        String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json?limit=100").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }


    public int createIssue(Issue newIssue) {
        String json = RestAssured.given().parameter("subject", newIssue.getSubject()).parameter("description", newIssue.getDescription()).post("https://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }
}