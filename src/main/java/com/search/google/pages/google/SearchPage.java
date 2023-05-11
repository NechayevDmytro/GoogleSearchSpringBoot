package com.search.google.pages.google;

import com.search.google.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Getter
@Component
public class SearchPage extends BasePage {

    @FindBy(name = "q")
    private WebElement searchField;

    @Value("${url}")
    private String url;

    Consumer<String> goTo = url -> driver.get(url);

    Consumer<String> search = searchPhrase -> searchField.sendKeys(searchPhrase, Keys.ENTER);

    @Override
    public boolean isAtThePage() {
        return wait.until(driver -> searchField.isDisplayed());
    }
}
