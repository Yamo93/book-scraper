package com.yageb;

import com.yageb.elements.IElement;

/**
 * A link element.
 */
public class LinkElement implements IElement {
    private String url;

    public LinkElement(String url) {
        this.url = url;
    }

    @Override
    public String absUrl(String attr) {
        return url;
    }

    @Override
    public String attr(String attrName) {
        return url;
    }

}
