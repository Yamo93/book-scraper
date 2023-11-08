package com.yageb;

import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Interface for the scraper.
 */
public interface IScraper {
    Document connect(String url) throws IOException;

    Iterable<Resource> getStylesheets(Document document) throws IOException;

    Iterable<Resource> getScripts(Document document) throws IOException;

    Iterable<String> getImageSrcs(Document document);

    String getRootUrl();

    String getOuterHtml();

    Document getRootDocument();

    Document getLinkDocument(Element link) throws IOException;

    String getFilePath(String url);
}
