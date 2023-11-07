package com.yageb;

import java.io.IOException;

import org.jsoup.nodes.Document;

public class StylesheetScraper implements Runnable {
    private IScraper scraper;
    private Document document;
    private IFileManager fileManager;

    public StylesheetScraper(IScraper scraper, Document document, IFileManager fileManager) {
        this.scraper = scraper;
        this.document = document;
        this.fileManager = fileManager;
    }

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
