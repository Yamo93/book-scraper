package com.yageb;

import java.io.IOException;

/**
 * Scrapes scripts from the web.
 */
public class ScriptScraper implements Runnable {
    private IScraper scraper;
    private IDocument document;
    private IFileManager fileManager;

    /**
     * Constructs a script scraper.
     * @param scraper The scraper.
     * @param document The document to scrape.
     * @param fileManager The file manager.
     */
    public ScriptScraper(IScraper scraper, IDocument document, IFileManager fileManager) {
        this.scraper = scraper;
        this.document = document;
        this.fileManager = fileManager;
    }

    /**
     * Scrapes scripts in a thread.
     */
    public void run() {
        Iterable<Resource> scripts;
        try {
            scripts = scraper.getScripts(document);
            for (Resource script : scripts) {
                fileManager.saveText(script);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
