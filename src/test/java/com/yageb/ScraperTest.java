package com.yageb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class ScraperTest {
    @Test
    public void testFilePath() throws IOException {

        Scraper scraper = new Scraper();
        String filePath = scraper.getFilePath(scraper.getRootUrl() + "media/something/script.js");
        assertEquals("media/something/script.js", filePath);
    }
}
