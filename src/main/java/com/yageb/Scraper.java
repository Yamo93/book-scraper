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
    public String getBaseUrl() {
        return baseUrl;
    }

    @Override
    public Document connect() throws IOException {
        final Response response = Jsoup.connect(baseUrl).execute();
        Document document = response.parse();
        return document;
    }

    @Override
    public Iterable<Resource> getStylesheets(Document document) throws IOException {
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
    public Iterable<Resource> getScripts(Document document) throws IOException {
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
    public Iterable<String> getImageSrcs(Document document) {
        ArrayList<String> imageSrcs = new ArrayList<String>();
        // Get all elements with img tag ,
        Elements img = document.getElementsByTag("img");

        for (Element el : img) {

            // for each element get the srs url
            String src = el.absUrl("src");

            System.out.println("Image Found!");
            System.out.println("src attribute is : " + src);

            // Exctract the name of the image from the src attribute
            int indexname = src.lastIndexOf("/");

            if (indexname == src.length()) {
                src = src.substring(1, indexname);
            }

            indexname = src.lastIndexOf("/");
            String name = src.substring(indexname, src.length());

            System.out.println(name);
            imageSrcs.add(src);
        }

        return imageSrcs;
    }

}
