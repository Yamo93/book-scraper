package com.yageb.documents;

import com.yageb.elements.IElement;
import com.yageb.elements.JsoupElement;
import java.util.ArrayList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * A Jsoup document.
 */
public class JsoupDocument implements IDocument {
    private Document document;

    public JsoupDocument(Document document) {
        this.document = document;
    }

    @Override
    public String outerHtml() {
        return document.outerHtml();
    }

    @Override
    public String title() {
        return document.title();
    }

    @Override
    public Iterable<IElement> getElementsByTag(String tag) {
        Elements elements = document.getElementsByTag(tag);
        ArrayList<IElement> jsoupElements = new ArrayList<IElement>();
        for (Element element : elements) {
            jsoupElements.add(new JsoupElement(element));
        }
        return jsoupElements;
    }
}
