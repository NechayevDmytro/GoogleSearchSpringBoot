package com.search.google.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class BrowserFactory {

    private static WebDriver driver;

    private static final Map<String, Supplier<WebDriver>> browserMap = new HashMap<>();

    private static final Supplier<WebDriver> CHROME = () -> {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable_notifications", "start-maximized", "incognito");
        options.setExperimentalOption("excludeSwitches", Collections.singleton("enable-automation"));
        return new ChromeDriver(options);
    };

    private static final Supplier<WebDriver> FIREFOX = () -> {
        WebDriverManager.firefoxdriver().setup();
        WebDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.manage().window().maximize();
        return firefoxDriver;
    };

    static {
        browserMap.put("CHROME", CHROME);
        browserMap.put("FIREFOX", FIREFOX);
    }

    public static WebDriver getDriver(String browserName) {
        if (Objects.isNull(driver)) {
            driver = browserMap.get(browserName).get();
        }
        return driver;
    }

    public static final Function<WebDriver, WebDriverWait> WAIT =
            driver -> new WebDriverWait(driver, Duration.ofSeconds(30));
}
