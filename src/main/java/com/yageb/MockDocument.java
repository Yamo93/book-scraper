package com.yageb;

/**
 * A mock implementation of Document.
 */
public class MockDocument implements IDocument {
    @Override
    public String outerHtml() {
        throw new UnsupportedOperationException("Unimplemented method 'outerHtml'");
    }

    @Override
    public String title() {
        throw new UnsupportedOperationException("Unimplemented method 'title'");
    }

    @Override
    public Iterable<IElement> getElementsByTag(String tag) {
        throw new UnsupportedOperationException("Unimplemented method 'getElementsByTag'");
    }
}
