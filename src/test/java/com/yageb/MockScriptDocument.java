package com.yageb;

import com.yageb.documents.IDocument;
import com.yageb.elements.IElement;
import java.util.ArrayList;

/**
 * A mock implementation of Document.
 */
public class MockScriptDocument implements IDocument {
    @Override
    public String outerHtml() {
        return "<script>console.log(\"hello world\")</script>";
    }

    @Override
    public String title() {
        throw new UnsupportedOperationException("Unimplemented method 'title'");
    }

    @Override
    public Iterable<IElement> getElementsByTag(String tag) {
        ArrayList<IElement> elements = new ArrayList<IElement>();
        if ("script".equals(tag)) {
            elements.add(new ScriptElement("http://books.toscrape.com/cats.js"));
        }
        return elements;
    }
}
