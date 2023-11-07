package com.yageb;

import java.io.IOException;

import org.jsoup.nodes.Document;

public class ImageScraper implements Runnable {
    private IScraper scraper;
    private Document document;
    private IFileManager fileManager;
    public ImageScraper(IScraper scraper, Document document, IFileManager fileManager) {
        this.scraper = scraper;
        this.document = document;
        this.fileManager = fileManager;
    }
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
