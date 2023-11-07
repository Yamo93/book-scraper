package com.yageb;

import java.io.IOException;

import org.jsoup.nodes.Document;

public class ScriptScraper implements Runnable {
    private IScraper scraper;
    private Document document;
    private IFileManager fileManager;
    public ScriptScraper(IScraper scraper, Document document, IFileManager fileManager) {
        this.scraper = scraper;
        this.document = document;
        this.fileManager = fileManager;
    }
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
