package testSuites.wayToAutomationTests;

import Entities.UserData;
import base.UIActions;
import helpers.JsonHelper;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AngularJSHomeScreen;
import pages.BankingLoginPage;
import testSuites.BaseTest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class UITests extends BaseTest {
    BankingLoginPage bankingLoginPage;
    AngularJSHomeScreen angularJSHomeScreen;
    UIActions uiActions = new UIActions();






    @Test
    public void AssertUserDetails() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        UserData userData = JsonHelper.hydrateEntity(UserData.class, "src/test/java", "userData");

        bankingLoginPage = new BankingLoginPage();
        angularJSHomeScreen = new AngularJSHomeScreen();
        angularJSHomeScreen.SelectAngularJsElement();



        String firstName = userData.getFirstName();
        String lastName = userData.getLastName();
        String postCode = userData.getPostCode();
        String customerID =  bankingLoginPage.AddNewCustomer(firstName,lastName,postCode);
        int noOfCustomers =  bankingLoginPage.GetNumberOfCustomersInTable();
        Assert.assertEquals(customerID, String.valueOf(noOfCustomers));
        uiActions.DynamicXpath(firstName);
        uiActions.DynamicXpath(lastName);
        uiActions.DynamicXpath(postCode);



        //scenario2
         String actualAccountNumber = bankingLoginPage.ProcessOpenAccount(firstName);
         String expectedAccountNumber = bankingLoginPage.GetAccNumberForUser(lastName,firstName);
         Assert.assertEquals(actualAccountNumber,expectedAccountNumber);
        //scenario3
        bankingLoginPage.DeleteCustomer(lastName,firstName);

    }

}
