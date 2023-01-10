package lesson.nine.task20.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("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");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("AllyamovIldar", "java_course_qa_b35")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}