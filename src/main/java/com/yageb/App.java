package com.yageb;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

import org.apache.commons.io.FileUtils;
import org.jsoup.*;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

/**
 * Hello world!
 */
public final class App {
    private static final String baseUrl = "http://books.toscrape.com/";

    private App() {
    }

    /**
     * Says hello to the world.
     *
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        Document doc;

        try {
            final Response response = Jsoup.connect(baseUrl).execute();
            doc = response.parse();

            Elements scripts = doc.getElementsByTag("script");
            Elements css = doc.getElementsByTag("link");
            for(Element c : css) {
                String url = c.absUrl("href");
                String rel = c.attr("rel") == null ? "" : c.attr("rel");
                if(!url.isEmpty() && rel.equals("stylesheet")) {
                    String filePath = url.split(baseUrl)[1];
                    Document docScript = Jsoup
                                            .connect(url) // add user agent
                                            .ignoreContentType(true)
                                            .get();

                    String data = docScript.select("body").text();
                    final File file = new File("data/" + filePath);
                    FileUtils.writeStringToFile(file, data.toString());
                }
            }

            // get js files

            // get images
            // Elements imgElements = doc.select("img[src]");
            // for (Element imgElement : imgElements) {
            //     String imgUrlString = imgElement.attr("abs:src");
            //     URL imgURL = new URL(imgUrlString);
            //     HttpURLConnection httpConnection = (HttpURLConnection) imgURL.openConnection();
            // }
            final File file = new File("data/index.html");
            FileUtils.writeStringToFile(file, doc.outerHtml(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
