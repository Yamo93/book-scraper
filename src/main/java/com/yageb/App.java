package com.yageb;

import java.io.File;
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
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        Document doc;

        try {
            final Response response = Jsoup.connect("http://books.toscrape.com/").execute();
            doc = response.parse();

            final File file = new File("data/index.html");
            FileUtils.writeStringToFile(file, doc.outerHtml(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
