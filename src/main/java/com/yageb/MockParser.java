package com.yageb;

import java.io.IOException;

/**
 * A mock implementation of a parser.
 */
public class MockParser implements IHtmlParser {
    @Override
    public IDocument parse(String url) throws IOException {
        return new MockDocument();
    }
}
