package com.yageb;

import java.io.IOException;
import org.jsoup.nodes.Document;

/**
 * Scrapes images.
 */
public class ImageScraper implements Runnable {
    private IScraper scraper;
    private Document document;
    private IFileManager fileManager;

    /**
     * Constructs an image scraper.
     * @param scraper A scraper.
     * @param document The document to scrape.
     * @param fileManager A file manager.
     */
    public ImageScraper(IScraper scraper, Document document, IFileManager fileManager) {
        this.scraper = scraper;
        this.document = document;
        this.fileManager = fileManager;
    }

    /**
     * Runs the image scraping in a thread.
     */
    public void run() {
        Iterable<String> imageSrcs = scraper.getImageSrcs(document);
        for (String imageSrc : imageSrcs) {
            try {
                fileManager.saveImage(imageSrc, scraper.getRootUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
