package base;

import driver.DriverManager;
import enums.WaitStrategy;
import factories.ExplicitWaitFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UIActions {

    public WebElement findElement(By locator) {
        return ExplicitWaitFactory.performExplicitWait(WaitStrategy.VISIBLE, locator);
    }

    public void clickOn(By locator) {
        WebElement element = ExplicitWaitFactory.performExplicitWait(WaitStrategy.ClICKABLE, locator);
        element.click();
    }
    public void clickOn(By locator, WaitStrategy waitStrategy) {
        WebElement element = ExplicitWaitFactory.performExplicitWait(waitStrategy, locator);
        element.click();
    }
    public String getTextOfElement(By locator)
    {
        return findElement(locator).getText();
    }
    public void scrollToElement(By locator){
        Actions actions = new Actions(DriverManager.getDriver());
        WebElement element = findElement(locator);
        actions.moveToElement(element).build().perform();
    }
    public void writeIn(String text, By locator) {
        WebElement element = findElement(locator);
        element.sendKeys(text);
    }
    public void writeIn(By locator, String text) {
        WebElement element = findElement(locator);
        element.sendKeys(text);
    }
    public static void switchToNewTab() {
        WebDriver driver = DriverManager.getDriver();
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }
    public String GetPartialTextFromAlert() {
        WebDriver driver = DriverManager.getDriver();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        String number = extractNumber(alertText);
        alert.accept();
        return number;
    }
    // Method to extract number using regex
    private String extractNumber(String text) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }
    public int GetListSize(By locator) {
        WebDriver driver = DriverManager.getDriver();
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.VISIBLE, locator);
        List<WebElement> itemsInList = driver.findElements(locator);
        return itemsInList.size();
    }

    public void DynamicXpath(String value) {

        WebDriver driver = DriverManager.getDriver();
        String dynamicXpath = String.format("//td[normalize-space(text()) = '%s']", value);
        By dynamicLocator = By.xpath(dynamicXpath);
        WebElement dynamicElement = driver.findElement(dynamicLocator);

    }

    public void DynamicXpathWithContainstextAndClick(String value) {

        WebDriver driver = DriverManager.getDriver();
        String dynamicXpath = String.format("//option[contains(normalize-space(),'%s')] ", value);
        By dynamicLocator = By.xpath(dynamicXpath);
        WebElement dynamicElement = driver.findElement(dynamicLocator);
        clickOn(dynamicLocator);
    }
    public void DynamicXpathToDeleteUser( String lastNameValue,String firstNameValue ) {

        WebDriver driver = DriverManager.getDriver();
        String dynamicXpath = String.format("//td[text() = '%s']/preceding-sibling::td[text() = '%s']//parent::tr//descendant::button ",lastNameValue, firstNameValue);
        By dynamicLocator = By.xpath(dynamicXpath);
        WebElement dynamicElement = driver.findElement(dynamicLocator);
        scrollToElement(dynamicLocator);
        clickOn(dynamicLocator);
    }

    public String GetAccountNumber( String lastNameValue,String firstNameValue ) {

        WebDriver driver = DriverManager.getDriver();
        String dynamicXpath = String.format("//td[text() = '%s']/preceding-sibling::td[text() = '%s']//parent::tr//descendant::span",lastNameValue, firstNameValue);
        By dynamicLocator = By.xpath(dynamicXpath);
        WebElement dynamicElement = driver.findElement(dynamicLocator);
        scrollToElement(dynamicLocator);
        return getTextOfElement(dynamicLocator);
    }


}


