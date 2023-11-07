package com.yageb;

import org.jsoup.nodes.*;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     *
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        IScraper scraper = new Scraper();
        IFileManager fileManager = new FileManager();

        try {
            Document document = scraper.connect();
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
                fileManager.saveImage(imageSrc, scraper.getBaseUrl());
            }

            fileManager.saveText(new Resource(document.outerHtml(), "index.html"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
