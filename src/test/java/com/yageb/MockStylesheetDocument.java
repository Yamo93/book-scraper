package com.yageb;

import com.yageb.documents.IDocument;
import com.yageb.elements.IElement;
import java.util.ArrayList;

/**
 * A mock implementation of Document.
 */
public class MockStylesheetDocument implements IDocument {
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
        if ("link".equals(tag)) {
            elements.add(new StylesheetElement("http://books.toscrape.com/cats.css"));
        }
        return elements;
    }
}
