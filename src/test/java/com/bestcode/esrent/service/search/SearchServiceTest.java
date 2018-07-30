package com.bestcode.esrent.service.search;

import static org.junit.Assert.*;

import com.bestcode.esrent.ApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchServiceTest extends ApplicationTests {

    @Autowired
    private SearchService searchService;

    @Test
    public void testIndex() {
        boolean success = searchService.index(15L);
        Assert.assertTrue(success);
    }
}