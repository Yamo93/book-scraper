package com.yageb;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
            for(Element s : scripts) {
                String url = s.absUrl("src");
                if(!url.isEmpty() && url.contains(baseUrl)) {
                    System.out.println(url);
                    String filePath = url.split(baseUrl)[1];
                    Document docScript = Jsoup
                                            .connect(url)
                                            .ignoreContentType(true)
                                            .get();

                    String data = docScript.select("body").text();
                    final File file = new File("data/" + filePath);
                    FileUtils.writeStringToFile(file, data.toString());
                }
            }

            Elements css = doc.getElementsByTag("link");
            for (Element c : css) {
                String url = c.absUrl("href");
                String rel = c.attr("rel") == null ? "" : c.attr("rel");
                if (!url.isEmpty() && rel.equals("stylesheet")) {
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

            // Get all elements with img tag ,
            Elements img = doc.getElementsByTag("img");

            for (Element el : img) {

                // for each element get the srs url
                String src = el.absUrl("src");

                System.out.println("Image Found!");
                System.out.println("src attribute is : " + src);

                // Exctract the name of the image from the src attribute
                int indexname = src.lastIndexOf("/");

                if (indexname == src.length()) {
                    src = src.substring(1, indexname);
                }

                indexname = src.lastIndexOf("/");
                String name = src.substring(indexname, src.length());

                System.out.println(name);

                URL url = new URL(src);
                InputStream in = url.openStream();

                String filePath = src.split(baseUrl)[1];
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
            FileUtils.writeStringToFile(file, doc.outerHtml(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
