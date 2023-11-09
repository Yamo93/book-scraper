package com.yageb;

import java.io.IOException;

/**
 * An interface for an HTML parser.
 */
public interface IHtmlParser {
    IDocument parse(String url) throws IOException;
}
