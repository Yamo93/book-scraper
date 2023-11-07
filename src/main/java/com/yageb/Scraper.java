package com.yageb;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraper implements IScraper {
    private final String baseUrl = "http://books.toscrape.com/";
    private Document rootDocument;

    public String getBaseUrl() {
        return baseUrl;
    }

    public Scraper() throws IOException {
        this.rootDocument = this.connect();
    }

    @Override
    public Document connect() throws IOException {
        final Response response = Jsoup.connect(baseUrl).execute();
        Document document = response.parse();
        return document;
    }

    @Override
    public Iterable<Resource> getStylesheets() throws IOException {
        ArrayList<Resource> resources = new ArrayList<Resource>();
        Elements css = rootDocument.getElementsByTag("link");
        for (Element c : css) {
            String url = c.absUrl("href");
            String rel = c.attr("rel") == null ? "" : c.attr("rel");
            if (!url.isEmpty() && rel.equals("stylesheet")) {
                String filePath = url.split(baseUrl)[1];
                Document docScript = Jsoup
                        .connect(url) // todo: add user agent
                        .ignoreContentType(true)
                        .get();

                String data = docScript.select("body").text();
                Resource resource = new Resource(data, filePath);
                resources.add(resource);
            }
        }

        return resources;
    }

    @Override
    public Iterable<Resource> getScripts() throws IOException {
        ArrayList<Resource> resources = new ArrayList<Resource>();
        Elements scripts = rootDocument.getElementsByTag("script");
        for (Element s : scripts) {
            String url = s.absUrl("src");
            if (!url.isEmpty() && url.contains(baseUrl)) {
                String filePath = url.split(baseUrl)[1];
                Document docScript = Jsoup
                        .connect(url)
                        .ignoreContentType(true)
                        .get();

                String data = docScript.select("body").text();
                Resource resource = new Resource(data, filePath);
                resources.add(resource);
            }
        }
        return resources;
    }

    @Override
    public Iterable<String> getImageSrcs() {
        ArrayList<String> imageSrcs = new ArrayList<String>();
        Elements images = rootDocument.getElementsByTag("img");

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
    public Iterable<Resource> scrapeLinks() throws IOException {
        ArrayList<Resource> resources = new ArrayList<Resource>();
        Elements links = rootDocument.select("a");
        for (Element link : links) {
            String nextUrl = link.attr("abs:href");
            String filePath = nextUrl.split(baseUrl)[1];
            Document docScript = Jsoup
                    .connect(nextUrl) // todo: add user agent
                    .ignoreContentType(true)
                    .get();
                    // check for links in the child document as well, recursively
            // get images as well
            Resource resource = new Resource(docScript.outerHtml(), filePath);
            resources.add(resource);
        }
        return resources;
    }
}
