package pages;

import base.UIActions;
import org.openqa.selenium.By;



public class AngularJSHomeScreen {

    UIActions uiActions = new UIActions();
    private final By bankingLink = By.xpath("//a[contains(@href, 'banking')]/h2[contains(text(),'Banking')]");


    public void SelectAngularJsElement()
    {
        uiActions.scrollToElement(bankingLink);
        uiActions.clickOn(bankingLink);
         uiActions.switchToNewTab();
    }



}
