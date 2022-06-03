package com.mtnplay.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyPlay_Screen_View_SubscriptionPage extends BasePage {

    @FindBy(xpath = "//div[contains(text(), 'confirm')]")
    public WebElement btnConfirm;

    @FindBy(xpath = "//div[contains(text(), 'watch now')]")
    public WebElement btnWatchNow;

    public MyPlay_Screen_View_SubscriptionPage(WebDriver newDriver, WebDriverWait newWait) {
        super(newDriver, newWait);}
}
