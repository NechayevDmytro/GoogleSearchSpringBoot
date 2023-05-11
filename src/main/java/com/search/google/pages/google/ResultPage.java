package com.search.google.pages.google;

import com.search.google.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Getter
@Component
public class ResultPage extends BasePage {

    @FindBy(xpath = "//img[@style='height:18px;width:18px']")
    private List<WebElement> icons;

    @FindBy(xpath = "//img[@style='height:18px;width:18px']/ancestor::span/../preceding-sibling::h3")
    private List<WebElement> topics;

    @FindBy(id = "pnnext")
    private WebElement nextPage;

    Function<Integer, Boolean> checkResults = amount -> icons.size() >= amount;

    Consumer<Boolean> goToNextPage = doGo -> {
        if(doGo) nextPage.click();
    };

    Supplier<List<String>> topicsStr = () -> topics
            .stream()
            .filter(WebElement::isDisplayed)
            .map(WebElement::getText)
            .collect(Collectors.toList());

    @Override
    public boolean isAtThePage() {return wait.until(driver -> !icons.isEmpty());
    }
}
