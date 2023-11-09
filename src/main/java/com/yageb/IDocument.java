package com.yageb;

public interface IDocument {
    String outerHtml();
    String title();
    Iterable<IElement> getElementsByTag(String tag);
}
