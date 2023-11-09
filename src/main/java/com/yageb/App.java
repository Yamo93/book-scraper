package com.yageb;

import com.yageb.controllers.ScraperController;
import com.yageb.file.FileManager;
import com.yageb.parsers.IHtmlParser;
import com.yageb.parsers.JsoupHtmlParser;
import com.yageb.scrapers.Scraper;
import java.io.IOException;

/**
 * The book scraper app.
 */
public final class App {
    private App() {
    }

    /**
     * Bootstraps the book scraper application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            IHtmlParser parser = new JsoupHtmlParser();
            ScraperController controller = new ScraperController(new Scraper(parser), new FileManager());
            Thread thread = new Thread(controller);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
