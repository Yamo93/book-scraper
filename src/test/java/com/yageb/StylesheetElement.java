package com.yageb;

/**
 * A stylesheet element.
 */
public class StylesheetElement implements IElement {
    private String href;

    public StylesheetElement(String href) {
        this.href = href;
    }

    @Override
    public String absUrl(String attr) {
        return href;
    }

    @Override
    public String attr(String attrName) {
        if ("rel".equals(attrName)) {
            return "stylesheet";
        }
        return null;
    }
}
