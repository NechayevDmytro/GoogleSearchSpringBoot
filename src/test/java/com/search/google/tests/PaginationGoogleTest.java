package com.search.google.tests;

import com.search.google.BaseTestNGTest;
import com.search.google.pages.google.ResultPage;
import com.search.google.pages.google.SearchPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PaginationGoogleTest extends BaseTestNGTest {

    @Autowired
    private SearchPage searchPage;

    @Autowired
    private ResultPage resultPage;

    @Value("${search.for:Java}")
    private String searchPhrase;

    @Value("${pages.amount:2}")
    private int pagesAmount;

    @Value("${min.results.amount}")
    private int minResultsAmount;

    @Test
    public void paginationGoogleTest() {
        searchPage.getGoTo().accept(searchPage.getUrl());
        Assert.assertTrue(searchPage.isAtThePage(), "Search page wasn't loaded correctly.");

        searchPage.getSearch().accept(searchPhrase);

        for (int i = 1; i < pagesAmount; i++) {
            Assert.assertTrue(resultPage.isAtThePage(), "Result page wasn't loaded correctly.");
            Assert.assertTrue(resultPage.getCheckResults().apply(minResultsAmount));
            if (pagesAmount == 1) break;
            else resultPage.getGoToNextPage().accept(true);
        }
    }
}
