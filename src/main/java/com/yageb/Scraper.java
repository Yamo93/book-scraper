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
    private Document document;

    public String getBaseUrl() {
        return baseUrl;
    }

    public Scraper() throws IOException {
        this.document = this.connect();
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
        Elements css = document.getElementsByTag("link");
        for (Element c : css) {
            String url = c.absUrl("href");
            String rel = c.attr("rel") == null ? "" : c.attr("rel");
            if (!url.isEmpty() && rel.equals("stylesheet")) {
                String filePath = url.split(baseUrl)[1];
                Document docScript = Jsoup
                        .connect(url) // add user agent
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
        Elements scripts = document.getElementsByTag("script");
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

    public String getOuterHtml() {
        return document.outerHtml();
    }
}
