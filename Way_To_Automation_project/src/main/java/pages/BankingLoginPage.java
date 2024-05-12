package pages;

import base.UIActions;
import enums.Currencies;
import helpers.EnumHelper;
import org.openqa.selenium.By;

import java.util.Random;

public class BankingLoginPage {
    UIActions uiActions = new UIActions();
    private final By bankingLoginBTN = By.xpath("//button[normalize-space(text())='Bank Manager Login']");
     private final By addCustomerBTN = By.xpath("//button[@class='btn btn-lg tab' and contains(normalize-space(),'Add Customer')]");
    private final By CustomersBTN = By.xpath("//button[@class='btn btn-lg tab' and contains(normalize-space(),'Customers')]");
    private final By openAccountBTN = By.xpath(" //button[@class='btn btn-lg tab' and contains(normalize-space(),'Open Account')]");
    private final By firstNameTXTField = By.xpath("//input[@placeholder='First Name']");
    private final By lastNameTXTField = By.xpath("//input[@placeholder='Last Name']");
    private final By postalCodeTXTField = By.xpath("//input[@placeholder='Post Code']");
    private final By addCustomerSubmitBTN = By.xpath("//button[@type='submit' and contains(normalize-space(),'Add Customer')]");
    private final By noOfCustomersInTable = By.xpath("//tr[@ng-repeat='cust in Customers | orderBy:sortType:sortReverse | filter:searchCustomer']");

    private final By customerNameDropDown = By.id("userSelect");
    private final By currencyDropDown = By.id("currency");
    private final By processBTN =  By.xpath("//button[@type='submit' and contains(normalize-space(),'Process')]");




public void ClickOnBankManagerLoginBTN()
{
 uiActions.clickOn(bankingLoginBTN);
}
    public void ClickOnAddCustomerBTN()
    {
        uiActions.clickOn(addCustomerBTN);
    }
    public void FillInCustomerData (String firstName ,String lastName, String postCode)
    {
        uiActions.writeIn(firstNameTXTField,firstName);
        uiActions.writeIn(lastNameTXTField,lastName);
        uiActions.writeIn(postalCodeTXTField,postCode);
    }

    public void ClickOnAddCustomerSubmitBTN()
    {
        uiActions.clickOn(addCustomerSubmitBTN);
    }

public String AddNewCustomer(String firstName ,String lastName, String postCode)
{
    ClickOnBankManagerLoginBTN();
    ClickOnAddCustomerBTN();
    FillInCustomerData(firstName,lastName,postCode);
    ClickOnAddCustomerSubmitBTN();
     String idValue = uiActions.GetPartialTextFromAlert();
     return idValue;
}

    public void ClickOnCustomersBTN()
    {
        uiActions.clickOn(CustomersBTN);
    }

    public int GetNumberOfCustomersInTable() {
        ClickOnCustomersBTN();
        return uiActions.GetListSize(noOfCustomersInTable);
    }

    public void ClickOnOpenAccountBTN()
    {
        uiActions.clickOn(openAccountBTN);
    }

    public void ClickOnCustomerNameDD()
    {
        uiActions.clickOn(customerNameDropDown);
    }
    public void ClickOnCUrrencyDD()
    {
        uiActions.clickOn(currencyDropDown);
    }


    public void ClickOnPrcessBTN()
    {
        uiActions.clickOn(processBTN);
    }


    public String ProcessOpenAccount(String customerValue ){
    ClickOnOpenAccountBTN();
    ClickOnCustomerNameDD();
    uiActions.DynamicXpathWithContainstextAndClick(customerValue);
    selectRandomCurrency();
    ClickOnPrcessBTN();
    return uiActions.GetPartialTextFromAlert();


    }
    public String GetAccNumberForUser(String lastNameValue,String firstNameValue)
    {
        ClickOnCustomersBTN();
        return uiActions.GetAccountNumber( lastNameValue, firstNameValue);
    }


    public void DeleteCustomer(String lastNameValue,String firstNameValue)
    {

        uiActions.DynamicXpathToDeleteUser( lastNameValue, firstNameValue);
    }
    public void selectRandomCurrency() {
        Random random = new Random();
        int index = random.nextInt(Currencies.values().length);


        Currencies randomCurrency = Currencies.values()[index];


        String description = EnumHelper.getDescription(randomCurrency);

        uiActions.DynamicXpathWithContainstextAndClick(description);
    }
}
