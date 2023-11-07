package com.yageb;

public class Resource {
    private String data;
    public String getData() {
        return data;
    }

    private String filePath;
    public String getFilePath() {
        return filePath;
    }

    public Resource(String data, String filePath) {
        this.data = data;
        this.filePath = filePath;
    }
}
