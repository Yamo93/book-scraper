package com.yageb;

import java.io.IOException;

public interface IFileManager {
    public void saveText(Resource resource) throws IOException;

    public void saveImage(String src, String baseUrl) throws IOException;
}
