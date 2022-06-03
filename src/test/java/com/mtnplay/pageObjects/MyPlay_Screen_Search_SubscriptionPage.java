package com.mtnplay.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyPlay_Screen_Search_SubscriptionPage extends BasePage{

    @FindBy(xpath = "//span[contains(text(), 'my play')]")
    public WebElement btnMyPlay;

    @FindBy(xpath = "//div[@class='icon search']")
    public WebElement btnSearch;

    @FindBy(xpath = "//input[@class='search-input']")
    public WebElement txtSearchInputField;

    public MyPlay_Screen_Search_SubscriptionPage(WebDriver newDriver, WebDriverWait newWait) {
        super(newDriver, newWait);
    }
}
