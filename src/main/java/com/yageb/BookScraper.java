package com.yageb;

public final class BookScraper {
    private BookScraper() {
    }

    public static void main(String[] args) {
        try {
            IScraper scraper = new Scraper();
            IFileManager fileManager = new FileManager();

            Iterable<Resource> scripts = scraper.getScripts();
            for (Resource script : scripts) {
                fileManager.saveText(script);
            }

            Iterable<Resource> stylesheets = scraper.getStylesheets();
            for (Resource stylesheet : stylesheets) {
                fileManager.saveText(stylesheet);
            }

            Iterable<String> imageSrcs = scraper.getImageSrcs();
            for (String imageSrc : imageSrcs) {
                fileManager.saveImage(imageSrc, scraper.getBaseUrl());
            }

            fileManager.saveText(new Resource(scraper.getOuterHtml(), "index.html"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
