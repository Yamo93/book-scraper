package com.yageb;

import java.io.IOException;

public interface IHtmlParser {
    IDocument parse(String url, boolean recursive) throws IOException;
}