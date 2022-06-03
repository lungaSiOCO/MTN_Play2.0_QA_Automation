package com.mtnplay.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Unsubscribe_ContentPage extends BasePage{

    @FindBy(xpath = "//span[contains(text(), 'manage')]")
    public WebElement btnManage;

    @FindBy(xpath = "//div[contains(text(), 'subscription')]")
    public WebElement lnkSubscription;

    @FindBy(xpath = "//div[contains(text(), 'ETV')]")
    public WebElement getLnkSubscriptionContent;

    @FindBy(xpath = "//div[contains(text(), 'unsubscribe')]")
    public WebElement btnUnsubscribe;

    @FindBy(xpath = "//span[contains(text(), 'confirm')]")
    public WebElement btnConfirm;

    public Unsubscribe_ContentPage(WebDriver newDriver, WebDriverWait newWait) {
        super(newDriver, newWait);
    }
}
