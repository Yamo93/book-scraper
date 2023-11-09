package com.yageb.scrapers;

import com.yageb.documents.IDocument;
import com.yageb.elements.IElement;
import com.yageb.file.Resource;
import java.io.IOException;

/**
 * Interface for the scraper.
 */
public interface IScraper {
    IDocument parse(String url) throws IOException;

    Iterable<Resource> getStylesheets(IDocument document) throws IOException;

    Iterable<Resource> getScripts(IDocument document) throws IOException;

    Iterable<String> getImageSrcs(IDocument document);

    String getRootUrl();

    IDocument getRootDocument();

    IDocument getLinkDocument(IElement link) throws IOException;

    String getFilePath(String url);
}
