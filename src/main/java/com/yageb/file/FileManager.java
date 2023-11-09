package com.yageb.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;

/**
 * Saves files to disk.
 */
public class FileManager implements IFileManager {
    @Override
    public void saveText(Resource resource) throws IOException {
        final File file = new File("data/" + resource.getFilePath());
        FileUtils.writeStringToFile(file, resource.getData(), StandardCharsets.UTF_8);
    }

    @Override
    public void saveImage(String src, String baseUrl) throws IOException {
        URL url = new URL(src);
        InputStream in = url.openStream();

        String filePath = src.split(baseUrl)[1];
        OutputStream out = new BufferedOutputStream(FileUtils.openOutputStream(new File("data/" + filePath)));

        for (int b; (b = in.read()) != -1;) {
            out.write(b);
        }
        out.close();
        in.close();
    }

}
