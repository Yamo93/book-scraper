package com.yageb;

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
            ScraperController controller = new ScraperController(new Scraper(), new FileManager());
            Thread thread = new Thread(controller);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
