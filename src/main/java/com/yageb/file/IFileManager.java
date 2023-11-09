package com.yageb.file;

import java.io.IOException;

/**
 * Interface for the file manager.
 */
public interface IFileManager {
    void saveText(Resource resource) throws IOException;

    void saveImage(String src, String baseUrl) throws IOException;
}
