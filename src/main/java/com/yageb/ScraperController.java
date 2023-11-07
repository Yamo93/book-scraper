package com.yageb;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScraperController {
    private IScraper scraper;
    private IFileManager fileManager;

    public ScraperController(IScraper scraper,
            IFileManager fileManager) {
        this.scraper = scraper;
        this.fileManager = fileManager;
    }

    public void run() {
        try {
            scrape(scraper.getRootDocument(), "http://books.toscrape.com/index.html");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void scrape(Document document, String url) throws IOException {
        System.out.println(document.title());
        fileManager.saveText(new Resource(document.outerHtml(), scraper.getFilePath(url)));

        Iterable<Resource> scripts = scraper.getScripts(document);
        for (Resource script : scripts) {
            fileManager.saveText(script);
        }

        Iterable<Resource> stylesheets = scraper.getStylesheets(document);
        for (Resource stylesheet : stylesheets) {
            fileManager.saveText(stylesheet);
        }

        Iterable<String> imageSrcs = scraper.getImageSrcs(document);
        for (String imageSrc : imageSrcs) {
            fileManager.saveImage(imageSrc, scraper.getRootUrl());
        }

        Elements links = document.select("a");
        for (Element link : links) {
            String nextUrl = link.attr("abs:href");
            System.out.println(nextUrl);
            Document linkDocument = scraper.getLinkDocument(link);
            if (linkDocument != null) {
                Resource resource = new Resource(linkDocument.outerHtml(), scraper.getFilePath(nextUrl));
                fileManager.saveText(resource);
                scrape(linkDocument, nextUrl);
            }
        }
    }
}
