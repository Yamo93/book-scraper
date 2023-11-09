package com.yageb;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupHtmlParser implements IHtmlParser {

    @Override
    public JsoupDocument parse(String url, boolean recursive) throws IOException {
        Document document = Jsoup.connect(url).ignoreContentType(true).get();
        // add with data
        return new JsoupDocument();
    }
    
}
