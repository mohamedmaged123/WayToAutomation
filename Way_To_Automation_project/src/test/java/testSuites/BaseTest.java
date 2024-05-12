package testSuites;

import driver.DriverActions;
import driver.DriverManager;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    protected void setUp() throws Exception {
        DriverActions.initDriver();
    }
    @AfterClass
    protected void tearDown(){
        DriverActions.quitDriver();
    }

    @AfterMethod
    public void screenshotOnFailure(ITestResult result)
    {
        if (result.getStatus() == ITestResult.FAILURE)
        {
            System.out.println("Failed!");
            System.out.println("Taking Screenshot....");
           // ScreenshotHelper.captureScreenshot(DriverManager.getDriver(), result.getName());
        }
    }
}
