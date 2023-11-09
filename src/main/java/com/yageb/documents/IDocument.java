package com.yageb.documents;

import com.yageb.elements.IElement;

/**
 * A document interface.
 */
public interface IDocument {
    String outerHtml();

    String title();

    Iterable<IElement> getElementsByTag(String tag);
}
