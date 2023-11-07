package com.yageb;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.*;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     *
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        IScraper resourceManager = new Scraper();

        try {
            Document document = resourceManager.connect();
            Iterable<Resource> scripts = resourceManager.getScripts(document);
            for (Resource script : scripts) {
                // add file manager
                final File file = new File("data/" + script.getFilePath());
                FileUtils.writeStringToFile(file, script.getData());
            }

            Iterable<Resource> stylesheets = resourceManager.getStylesheets(document);
            for (Resource stylesheet : stylesheets) {
                // add file manager
                final File file = new File("data/" + stylesheet.getFilePath());
                FileUtils.writeStringToFile(file, stylesheet.getData());
            }


            Iterable<String> imageSrcs = resourceManager.getImageSrcs(document);
            for (String imageSrc : imageSrcs) {
                URL url = new URL(imageSrc);
                InputStream in = url.openStream();

                String filePath = imageSrc.split(resourceManager.getBaseUrl())[1];
                System.out.println("filePath");
                System.out.println(filePath);
                OutputStream out = new BufferedOutputStream(FileUtils.openOutputStream(new File("data/" + filePath)));

                for (int b; (b = in.read()) != -1;) {
                    out.write(b);
                }
                out.close();
                in.close();
            }

            final File file = new File("data/index.html");
            FileUtils.writeStringToFile(file, document.outerHtml(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
