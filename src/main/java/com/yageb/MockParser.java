package com.yageb;

import java.io.IOException;

public class MockParser implements IHtmlParser {

    @Override
    public IDocument parse(String url, boolean recursive) throws IOException {
        return new MockDocument();
    }
    
}
