package com.yageb;

public interface IDocument {
    Iterable<ILink> getLinks();
    String outerHtml();
    String title();
    Iterable<IElement> getElementsByTag(String tag);
}
