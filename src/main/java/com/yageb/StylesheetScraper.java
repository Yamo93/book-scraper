package com.yageb;

import java.io.IOException;
import org.jsoup.nodes.Document;

/**
 * Scrapes stylesheets from the web.
 */
public class StylesheetScraper implements Runnable {
    private IScraper scraper;
    private Document document;
    private IFileManager fileManager;

    /**
     * Constructs a stylesheet scraper runnable as a thread.
     * @param scraper The scraper.
     * @param document The document to scrape.
     * @param fileManager The file manager.
     */
    public StylesheetScraper(IScraper scraper, Document document, IFileManager fileManager) {
        this.scraper = scraper;
        this.document = document;
        this.fileManager = fileManager;
    }

    /**
     * Scrapes stylesheets in a thread.
     */
    public void run() {
        Iterable<Resource> stylesheets;
        try {
            stylesheets = scraper.getStylesheets(document);
            for (Resource stylesheet : stylesheets) {
                fileManager.saveText(stylesheet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
