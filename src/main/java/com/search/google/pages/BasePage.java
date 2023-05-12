package com.search.google.pages;

import com.search.google.config.BrowserFactory;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;

public abstract class BasePage {

    @Value("${browser:CHROME}")
    public String browserName;

    protected WebDriver driver;

    protected WebDriverWait wait;

    @PostConstruct
    public void init() {
        driver = BrowserFactory.getDriver(browserName);
        wait = BrowserFactory.WAIT.apply(driver);
        PageFactory.initElements(driver, this);
    }

    @PreDestroy
    public void tearDown() {
        driver.quit();
    }

    public abstract boolean isAtThePage();
}
