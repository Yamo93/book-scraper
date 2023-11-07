package com.yageb;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        try {
            ScraperController controller = new ScraperController(new Scraper(), new FileManager());
            Thread thread = new Thread(controller);
            thread.start();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
