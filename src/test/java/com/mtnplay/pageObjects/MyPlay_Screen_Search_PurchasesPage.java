package com.mtnplay.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyPlay_Screen_Search_PurchasesPage extends BasePage{

    @FindBy(xpath = "//div[contains(text(), 'PURCHASES')]")
    public WebElement btnPurchases;

    public MyPlay_Screen_Search_PurchasesPage(WebDriver newDriver, WebDriverWait newWait) {
        super(newDriver, newWait);
    }
}
