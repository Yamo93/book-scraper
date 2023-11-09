package com.yageb;

import com.yageb.documents.IDocument;
import com.yageb.elements.IElement;
import java.util.ArrayList;

/**
 * A mock implementation of Document.
 */
public class MockHtmlDocument implements IDocument {
    @Override
    public String outerHtml() {
        return "<html></html>";
    }

    @Override
    public String title() {
        throw new UnsupportedOperationException("Unimplemented method 'title'");
    }

    @Override
    public Iterable<IElement> getElementsByTag(String tag) {
        return new ArrayList<IElement>();
    }
}
