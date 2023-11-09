package com.yageb;

import java.io.IOException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the scraper.
 */
public class ScraperTest {
    @Test
    void testRootUrl() throws IOException {
        IHtmlParser parser = new MockParser();
        Scraper scraper = new Scraper(parser);
        String rootUrl = scraper.getRootUrl();
        assertEquals("http://books.toscrape.com/", rootUrl);
    }

    @Test
    void testParseShouldReturnNullIfAlreadyParsed() throws IOException {
        IHtmlParser parser = new MockParser();
        Scraper scraper = new Scraper(parser);
        IDocument document1 = scraper.parse("http://books.toscrape.com/cats.html");
        assertNotNull(document1);
        IDocument document2 = scraper.parse("http://books.toscrape.com/cats.html");
        assertNull(document2);
    }

    @Test
    void testGetStylesheets() throws IOException {
        IHtmlParser parser = new MockParser();
        Scraper scraper = new Scraper(parser);
        IDocument stylesheetDocument = new MockStylesheetDocument();
        Iterable<Resource> stylesheets = scraper.getStylesheets(stylesheetDocument);
        int count = 0;
        String data = "";

        for (Resource stylesheet : stylesheets) {
            count += 1;
            data += stylesheet.getData();
        }
        assertEquals(1, count);
        assertTrue(data.contains("body"));
        assertTrue(data.contains("red"));
    }

    @Test
    void testGetScripts() throws IOException {
        IHtmlParser parser = new MockParser();
        Scraper scraper = new Scraper(parser);
        IDocument scriptDocument = new MockScriptDocument();
        Iterable<Resource> scripts = scraper.getScripts(scriptDocument);
        int count = 0;
        String data = "";

        for (Resource script : scripts) {
            count += 1;
            data += script.getData();
        }
        assertEquals(1, count);
        assertTrue(data.contains("console.log"));
        assertTrue(data.contains("hello world"));
    }

    @Test
    public void testFilePath() throws IOException {
        IHtmlParser parser = new MockParser();
        Scraper scraper = new Scraper(parser);
        String filePath = scraper.getFilePath(scraper.getRootUrl() + "media/something/script.js");
        assertEquals("media/something/script.js", filePath);
    }
}
