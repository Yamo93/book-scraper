package com.yageb;

/**
 * A script element.
 */
public class ScriptElement implements IElement {
    private String src;

    public ScriptElement(String src) {
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
