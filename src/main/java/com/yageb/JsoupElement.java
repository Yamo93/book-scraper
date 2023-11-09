package com.yageb;

import org.jsoup.nodes.Element;

public class JsoupElement implements IElement {
    private Element element;

    public JsoupElement(Element element) {
        this.element = element;
    }
    @Override
    public String absUrl(String attr) {
        return element.absUrl(attr);
    }

    @Override
    public String attr(String attrName) {
        return element.attr(attrName);
    }
    
}
