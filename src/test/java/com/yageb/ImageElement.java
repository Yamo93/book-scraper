package com.yageb;

import com.yageb.elements.IElement;

/**
 * An image element.
 */
public class ImageElement implements IElement {
    private String src;

    public ImageElement(String src) {
        this.src = src;
    }

    @Override
    public String absUrl(String attr) {
        return src;
    }

    @Override
    public String attr(String attrName) {
        return null;
    }
}
