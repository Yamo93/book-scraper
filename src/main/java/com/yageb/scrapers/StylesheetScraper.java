package com.yageb.scrapers;

import com.yageb.documents.IDocument;
import com.yageb.file.IFileManager;
import com.yageb.file.Resource;
import java.io.IOException;

/**
 * Scrapes stylesheets from the web.
 */
public class StylesheetScraper implements Runnable {
    private IScraper scraper;
    private IDocument document;
    private IFileManager fileManager;

    /**
     * Constructs a stylesheet scraper runnable as a thread.
     * @param scraper The scraper.
     * @param document The document to scrape.
     * @param fileManager The file manager.
     */
    public StylesheetScraper(IScraper scraper, IDocument document, IFileManager fileManager) {
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
