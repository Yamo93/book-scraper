package com.yageb;

import java.io.IOException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the scraper.
 */
public class ScraperTest {
    @Test
    public void testFilePath() throws IOException {
        IHtmlParser parser = new MockParser();
        Scraper scraper = new Scraper(parser);
        String filePath = scraper.getFilePath(scraper.getRootUrl() + "media/something/script.js");
        assertEquals("media/something/script.js", filePath);
    }
}
