package lesson.eight.task17.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import lesson.eight.task17.mantis.appmanager.ApplicationManager;
import org.openqa.selenium.remote.Browser;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(Browser.CHROME);

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    boolean isIssueOpen(int issueId) throws IOException, ServiceException {
        MantisConnectPortType mc = app.soap().getMantisConnect();
        IssueData issue = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId));
        String statusName = issue.getStatus().getName();
        return !statusName.matches("resolved|closed");
    }

    public void skipIfNotFixed(int issueId) throws ServiceException, IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
    }
}