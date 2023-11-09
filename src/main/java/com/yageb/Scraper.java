package com.yageb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Scrapes resources from web pages.
 */
public class Scraper implements IScraper {
    private final String baseUrl = "http://books.toscrape.com/";
    private IDocument rootDocument;
    private Set<String> scrapedPages;
    private IHtmlParser parser;

    public Scraper(IHtmlParser parser) throws IOException {
        this.parser = parser;
        this.scrapedPages = new HashSet<String>();
        this.rootDocument = this.parse(baseUrl);
    }

    public String getRootUrl() {
        return baseUrl;
    }

    public boolean isAlreadyScraped(String url) {
        return scrapedPages.contains(url);
    }

    @Override
    public IDocument parse(String url) throws IOException {
        System.out.println("connecting to " + url);
        if (isAlreadyScraped(url)) {
            System.out.println("url " + url + " already exists");
            return null;
        }
        IDocument document = parser.parse(url, false);
        scrapedPages.add(url);
        return document;
    }

    @Override
    public Iterable<Resource> getStylesheets(IDocument document) throws IOException {
        ArrayList<Resource> resources = new ArrayList<Resource>();
        Iterable<IElement> css = document.getElementsByTag("link");
        for (IElement c : css) {
            String url = c.absUrl("href");
            String rel = c.attr("rel") == null ? "" : c.attr("rel");
            if ("stylesheet".equals(rel) && !url.isEmpty()) {
                String filePath = url.split(baseUrl)[1];
                IDocument data = parse(url);
                if (data != null) {
                    Resource resource = new Resource(data.outerHtml(), filePath);
                    resources.add(resource);
                }
            }
        }

        return resources;
    }

    @Override
    public Iterable<Resource> getScripts(IDocument document) throws IOException {
        ArrayList<Resource> resources = new ArrayList<Resource>();
        Iterable<IElement> scripts = document.getElementsByTag("script");
        for (IElement s : scripts) {
            String url = s.absUrl("src");
            if (!url.isEmpty() && url.contains(baseUrl)) {
                String filePath = url.split(baseUrl)[1];
                IDocument doc = parse(url);
                if (doc != null) {
                    Resource resource = new Resource(doc.outerHtml(), filePath);
                    resources.add(resource);
                }
            }
        }
        return resources;
    }

    @Override
    public Iterable<String> getImageSrcs(IDocument document) {
        ArrayList<String> imageSrcs = new ArrayList<String>();
        Iterable<IElement> images = document.getElementsByTag("img");

        for (IElement imageElement : images) {
            String src = imageElement.absUrl("src");
            int indexName = src.lastIndexOf("/");
            if (indexName == src.length()) {
                src = src.substring(1, indexName);
            }

            indexName = src.lastIndexOf("/");
            imageSrcs.add(src);
        }

        return imageSrcs;
    }

    @Override
    public IDocument getRootDocument() {
        return rootDocument;
    }

    /**
     * Gets the document of a link.
     */
    public IDocument getLinkDocument(IElement link) throws IOException {
        String nextUrl = link.attr("abs:href");
        IDocument childDocument = parse(nextUrl);
        return childDocument;
    }

    public String getFilePath(String url) {
        return url.split(baseUrl)[1];
    }

    public IHtmlParser getParser() {
        return parser;
    }
}
