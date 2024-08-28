package com.demoqa;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Tests{
    private By btnElements = By.xpath("//*[@id='app']/div/div/div[1]/div");
    private By textBox = By.xpath("//div[@class='element-list collapse show']//li[@id='item-0']");
    private By inputFullName = By.xpath("//div[@class='col-md-9 col-sm-12']//input[@id='userName']");
    private By inputEmail = By.xpath("//div[@class='col-md-9 col-sm-12']//input[@id='userEmail']");
    private By inputCurrentAddress = By.xpath("//div[@class='col-md-9 col-sm-12']//textarea[@id='currentAddress']");
    private By inputPermanentAddress = By.xpath("//div[@class='col-md-9 col-sm-12']//textarea[@id='permanentAddress']");
    private By btnSubmit = By.xpath("//button[@class='btn btn-primary']");
    private By checkName = By.xpath("//div[@class='border col-md-12 col-sm-12']//p[@id='name']");
    private By checkEmail = By.xpath("//div[@class='border col-md-12 col-sm-12']//p[@id='email']");
    private By checkCurrentAddress = By.xpath("//div[@class='border col-md-12 col-sm-12']//p[@id='currentAddress']");
    private By checkPermanentAddress = By.xpath("//div[@class='border col-md-12 col-sm-12']//p[@id='permanentAddress']");
    private By bottons = By.xpath("//div[@class='element-list collapse show']//li[@id='item-4']");
    private By btnClickMe = By.xpath("//div[@class='col-12 mt-4 col-md-6']//button[text() = 'Click Me']");
    private By btnRightClickMe = By.xpath("//div[@class='col-12 mt-4 col-md-6']//button[@id='rightClickBtn']");
    private By btnDoubleClickMe = By.xpath("//div[@class='col-12 mt-4 col-md-6']//button[@id='doubleClickBtn']");
    private By checkDinamicClick = By.xpath("//div[@class='col-12 mt-4 col-md-6']//p[@id='dynamicClickMessage']");
    private By checkRightClick = By.xpath("//div[@class='col-12 mt-4 col-md-6']//p[@id='rightClickMessage']");
    private By checkDoubleClick = By.xpath("//div[@class='col-12 mt-4 col-md-6']//p[@id='doubleClickMessage']");
    private By alertsFrameWindows = By.xpath("//div[text() = 'Alerts, Frame & Windows']");
    private By browserWindows = By.xpath("//div[@class='element-list collapse show']//li[@id='item-0']");
    private By btnTab = By.id("tabButton");
    private By btnWindow = By.id("windowButton");
    private By alerts = By.xpath("//div[@class='element-list collapse show']//li[@id='item-1']");
    private By btnAlert = By.id("alertButton");
    private By btnTimerAlert = By.id("timerAlertButton");
    private By btnConfirm = By.id("confirmButton");
    private By checkConfirmClickMe = By.id("confirmResult");
    private By btnPromt = By.id("promtButton");
    private By checkPromtClickMe = By.id("promptResult");

    WebDriver driver = new ChromeDriver();
    Data getDate = new Data();
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Test
    public void registration() throws InterruptedException {

        driver.get(getDate.getUrl);
        driver.manage().window().maximize();
        driver.findElement(btnElements).click();

        driver.findElement(textBox).click();
        driver.findElement(inputFullName).sendKeys(getDate.fullName);
        driver.findElement(inputEmail).sendKeys(getDate.email);
        driver.findElement(inputCurrentAddress).sendKeys(getDate.currentAddress);
        driver.findElement(inputPermanentAddress).sendKeys(getDate.permanentAddress);
        driver.findElement(btnSubmit).click();

        String actualName = driver.findElement(checkName).getText();
        String actualEmail = driver.findElement(checkEmail).getText();
        String actualCurrentAddress = driver.findElement(checkCurrentAddress).getText();
        String actualPermanentAddress = driver.findElement(checkPermanentAddress).getText();

        Assert.assertEquals("Name:" + getDate.fullName, actualName);
        Assert.assertEquals("Email:" + getDate.email, actualEmail);
        Assert.assertEquals("Current Address :" + getDate.currentAddress, actualCurrentAddress);
        Assert.assertEquals("Permananet Address :" + getDate.permanentAddress, actualPermanentAddress);

        driver.findElement(bottons).click();
        driver.findElement(btnClickMe).click();

        String actualDinamicClick = driver.findElement(checkDinamicClick).getText();
        Assert.assertEquals(getDate.dinamicClick, actualDinamicClick);

        WebElement RightClick = driver.findElement(btnRightClickMe);
        Actions actions = new Actions(driver);
        actions.contextClick(RightClick).perform();

        String actualRightClick = driver.findElement(checkRightClick).getText();
        Assert.assertEquals(getDate.rightClick, actualRightClick);

        WebElement DoubleClick = driver.findElement(btnDoubleClickMe);
        actions.doubleClick(DoubleClick).perform();

        String actualDoubleClick = driver.findElement(checkDoubleClick).getText();
        Assert.assertEquals(getDate.doubleClick, actualDoubleClick);

        WebElement AlertsFrameWindows = driver.findElement(alertsFrameWindows);
        jse.executeScript("arguments[0].scrollIntoView(true);", AlertsFrameWindows);
        AlertsFrameWindows.click();
        wait.until(ExpectedConditions.elementToBeClickable(browserWindows));
        driver.findElement(browserWindows).click();

        String winHandleBefore = driver.getWindowHandle();
        driver.findElement(btnTab).click();
        for (String winHandle : driver.getWindowHandles()) {
            if (!winHandleBefore.contentEquals(winHandle)) {
                driver.switchTo().window(winHandle);
                break;
            }
        }

        driver.close();
        driver.switchTo().window(winHandleBefore);
        driver.findElement(btnWindow).click();
        for (String winHandle : driver.getWindowHandles()) {
            if (!winHandleBefore.contentEquals(winHandle)) {
                driver.switchTo().window(winHandle);
                break;
            }
        }
        driver.close();
        driver.switchTo().window(winHandleBefore);

        driver.findElement(alerts).click();
        driver.findElement(btnAlert).click();
        driver.switchTo().alert().accept();
        driver.findElement(btnTimerAlert).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        driver.findElement(btnConfirm).click();
        driver.switchTo().alert().accept();

        String actualClickMeMessage = driver.findElement(checkConfirmClickMe).getText();
        Assert.assertEquals(getDate.confirmClickMeMessage, actualClickMeMessage);

        driver.findElement(btnPromt).click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(getDate.username);
        alert.accept();

        String actualPromtClickMeMessage = driver.findElement(checkPromtClickMe).getText();
        Assert.assertEquals(getDate.promtClickMeMessage, actualPromtClickMeMessage);

        driver.quit();
    }
}

