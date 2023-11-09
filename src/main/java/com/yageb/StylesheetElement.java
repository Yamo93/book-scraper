package com.yageb;

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
        if (attrName == "rel") return "stylesheet";
        return null;
    }
    
}
