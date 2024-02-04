package com.example.seleniumtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    @FindBy(xpath = "/html/body//div[4]/a")
    public WebElement loadMoreButton;

    @FindBy(xpath ="/html/body//form/input")
    public WebElement acceptCookies;

    @FindBy(className ="input-search")
    public WebElement getSearchButton;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
