package com.yageb;

import com.yageb.documents.IDocument;
import com.yageb.elements.IElement;
import java.util.ArrayList;

/**
 * A mock implementation of Document.
 */
public class MockHtmlWithImgDocument implements IDocument {
    @Override
    public String outerHtml() {
        return "<html><img src=\"cats.jpg\" /></html>";
    }

    @Override
    public String title() {
        throw new UnsupportedOperationException("Unimplemented method 'title'");
    }

    @Override
    public Iterable<IElement> getElementsByTag(String tag) {
        ArrayList<IElement> imgElements = new ArrayList<IElement>();
        if ("img".equals(tag)) {
            imgElements.add(new ImageElement("http://books.toscrape.com/cats.jpg"));
        }
        return imgElements;
    }
}
