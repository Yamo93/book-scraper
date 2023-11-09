package com.yageb;

import java.io.IOException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Tests the scraper.
 */
public class ScraperTest {
    @Test
    public void testRootUrl() throws IOException {
        IHtmlParser parser = new MockParser();
        Scraper scraper = new Scraper(parser);
        String rootUrl = scraper.getRootUrl();
        assertEquals("http://books.toscrape.com/", rootUrl);
    }

    @Test void testParseShouldReturnNullIfAlreadyParsed() throws IOException {
        IHtmlParser parser = new MockParser();
        Scraper scraper = new Scraper(parser);
        IDocument document1 = scraper.parse("http://books.toscrape.com/cats.html");
        assertNotNull(document1);
        IDocument document2 = scraper.parse("http://books.toscrape.com/cats.html");
        assertNull(document2);
    }

    @Test
    public void testFilePath() throws IOException {
        IHtmlParser parser = new MockParser();
        Scraper scraper = new Scraper(parser);
        String filePath = scraper.getFilePath(scraper.getRootUrl() + "media/something/script.js");
        assertEquals("media/something/script.js", filePath);
    }
}
