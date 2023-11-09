package com.yageb;

/**
 * A document interface.
 */
public interface IDocument {
    String outerHtml();

    String title();

    Iterable<IElement> getElementsByTag(String tag);
}
