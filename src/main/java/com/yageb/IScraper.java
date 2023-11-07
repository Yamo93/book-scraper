package com.yageb;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public interface IScraper {
    public Document connect(String url) throws IOException;

    public Iterable<Resource> getStylesheets(Document document) throws IOException;

    public Iterable<Resource> getScripts(Document document) throws IOException;

    public Iterable<String> getImageSrcs(Document document);

    public String getRootUrl();

    public String getOuterHtml();

    public Document getRootDocument();

    public Document getLinkDocument(Element link) throws IOException;

    public String getFilePath(String url);
}
