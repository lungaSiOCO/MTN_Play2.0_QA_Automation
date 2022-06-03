package com.mtnplay.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Full_SearchFunctionalityPage extends BasePage{

    @FindBy(xpath = "//span[contains(text(), 'my play')]")
    public WebElement btnMyPlay;

    @FindBy(xpath = "//div[@class='icon search']")
    public WebElement btnSearch;

    @FindBy(xpath = "//input[@class='search-input']")
    public WebElement txtSearchInputField;

    public Full_SearchFunctionalityPage(WebDriver newDriver, WebDriverWait newWait) {
        super(newDriver, newWait);
    }
}
