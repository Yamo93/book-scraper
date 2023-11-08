package com.yageb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Scrapes resources from web pages.
 */
public class Scraper implements IScraper {
    private final String baseUrl = "http://books.toscrape.com/";
    private Document rootDocument;
    private Set<String> scrapedPages;

    public Scraper() throws IOException {
        this.scrapedPages = new HashSet<String>();
        this.rootDocument = this.connect(baseUrl);
    }

    public String getRootUrl() {
        return baseUrl;
    }

    @Override
    public Document connect(String url) throws IOException {
        System.out.println("connecting to " + url);
        if (scrapedPages.contains(url)) {
            System.out.println("url " + url + " already exists");
            return null;
        }
        Document document = Jsoup.connect(url).ignoreContentType(true).get();
        scrapedPages.add(url);
        return document;
    }

    @Override
    public Iterable<Resource> getStylesheets(Document document) throws IOException {
        ArrayList<Resource> resources = new ArrayList<Resource>();
        Elements css = document.getElementsByTag("link");
        for (Element c : css) {
            String url = c.absUrl("href");
            String rel = c.attr("rel") == null ? "" : c.attr("rel");
            if ("stylesheet".equals(rel) && !url.isEmpty()) {
                String filePath = url.split(baseUrl)[1];
                Document docScript = connect(url);
                if (docScript != null) {
                    String data = docScript.select("body").text();
                    Resource resource = new Resource(data, filePath);
                    resources.add(resource);
                }
            }
        }

        return resources;
    }

    @Override
    public Iterable<Resource> getScripts(Document document) throws IOException {
        ArrayList<Resource> resources = new ArrayList<Resource>();
        Elements scripts = document.getElementsByTag("script");
        for (Element s : scripts) {
            String url = s.absUrl("src");
            if (!url.isEmpty() && url.contains(baseUrl)) {
                String filePath = url.split(baseUrl)[1];
                Document docScript = connect(url);
                if (docScript != null) {
                    String data = docScript.select("body").text();
                    Resource resource = new Resource(data, filePath);
                    resources.add(resource);
                }
            }
        }
        return resources;
    }

    @Override
    public Iterable<String> getImageSrcs(Document document) {
        ArrayList<String> imageSrcs = new ArrayList<String>();
        Elements images = document.getElementsByTag("img");

        for (Element imageElement : images) {
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
    public String getOuterHtml() {
        return rootDocument.outerHtml();
    }

    @Override
    public Document getRootDocument() {
        return rootDocument;
    }

    /**
     * Gets the document of a link.
     */
    public Document getLinkDocument(Element link) throws IOException {
        String nextUrl = link.attr("abs:href");
        Document childDocument = connect(nextUrl);
        return childDocument;
    }

    public String getFilePath(String url) {
        return url.split(baseUrl)[1];
    }
}
