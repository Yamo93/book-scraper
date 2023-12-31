package com.yageb;

import com.yageb.documents.IDocument;
import com.yageb.elements.IElement;
import com.yageb.file.Resource;
import com.yageb.parsers.IHtmlParser;
import com.yageb.scrapers.Scraper;
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
    void testGetImageSrcs() throws IOException {
        IHtmlParser parser = new MockParser();
        Scraper scraper = new Scraper(parser);
        IDocument htmlWithImgDocument = new MockHtmlWithImgDocument();
        Iterable<String> imageSrcs = scraper.getImageSrcs(htmlWithImgDocument);
        int count = 0;
        String srcs = "";

        for (String imageSrc : imageSrcs) {
            count += 1;
            srcs += imageSrc;
        }
        assertEquals(1, count);
        assertTrue(srcs.contains("cats.jpg"));
    }

    @Test
    void testGetLinkDocument() throws IOException {
        IHtmlParser parser = new MockParser();
        Scraper scraper = new Scraper(parser);
        IElement link = new LinkElement("http://books.toscrape.com/dogs.html");
        IDocument document1 = scraper.getLinkDocument(link);
        assertNotNull(document1);
        IDocument document2 = scraper.getLinkDocument(link);
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
