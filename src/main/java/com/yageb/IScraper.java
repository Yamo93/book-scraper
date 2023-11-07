package com.yageb;

import java.io.IOException;

import org.jsoup.nodes.Document;

public interface IScraper {
    public Document connect() throws IOException;

    public Iterable<Resource> getStylesheets() throws IOException;

    public Iterable<Resource> getScripts() throws IOException;

    public Iterable<String> getImageSrcs();

    public String getBaseUrl();

    public String getOuterHtml();

    public Iterable<Resource> scrapeLinks() throws IOException;
}
