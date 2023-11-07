package com.yageb;

import java.io.IOException;

import org.jsoup.nodes.Document;

public interface IScraper {
    public Document connect() throws IOException;

    public Iterable<Resource> getStylesheets(Document document) throws IOException;

    public Iterable<Resource> getScripts(Document document) throws IOException;

    public Iterable<String> getImageSrcs(Document document);

    public String getBaseUrl();
}
