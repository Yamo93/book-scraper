package com.yageb.scrapers;

import com.yageb.documents.IDocument;
import com.yageb.file.IFileManager;
import java.io.IOException;

/**
 * Scrapes images.
 */
public class ImageScraper implements Runnable {
    private IScraper scraper;
    private IDocument document;
    private IFileManager fileManager;

    /**
     * Constructs an image scraper.
     * @param scraper A scraper.
     * @param document The document to scrape.
     * @param fileManager A file manager.
     */
    public ImageScraper(IScraper scraper, IDocument document, IFileManager fileManager) {
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
