package com.yageb;

import java.util.ArrayList;

/**
 * A mock implementation of Document.
 */
public class MockDocument implements IDocument {
    @Override
    public String outerHtml() {
        return "<body>{ .color: red; }</body>";
    }

    @Override
    public String title() {
        throw new UnsupportedOperationException("Unimplemented method 'title'");
    }

    @Override
    public Iterable<IElement> getElementsByTag(String tag) {
        ArrayList<IElement> elements = new ArrayList<IElement>();
        if (tag == "link") {
            elements.add(new StylesheetElement("http://books.toscrape.com/cats.css"));
        }
        return elements;
    }
}
