package com.yageb;

import java.io.IOException;

/**
 * A mock implementation of a parser.
 */
public class MockParser implements IHtmlParser {
    @Override
    public IDocument parse(String url) throws IOException {
        if (url.contains(".html") || "http://books.toscrape.com/".equals(url)) {
            return new MockHtmlDocument();
        }
        if (url.contains(".css")) {
            return new MockStylesheetDocument();
        }
        if (url.contains(".js")) {
            return new MockScriptDocument();
        }
        throw new RuntimeException("Can not parse mock document with url " + url);
    }
}
