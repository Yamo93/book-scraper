package com.yageb.parsers;

import com.yageb.documents.IDocument;
import java.io.IOException;

/**
 * An interface for an HTML parser.
 */
public interface IHtmlParser {
    IDocument parse(String url) throws IOException;
}
