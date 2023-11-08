package com.yageb;

/**
 * A scraped resource containing data and a file path.
 */
public class Resource {
    private String data;
    private String filePath;

    public Resource(String data, String filePath) {
        this.data = data;
        this.filePath = filePath;
    }

    public String getData() {
        return data;
    }

    public String getFilePath() {
        return filePath;
    }
}
