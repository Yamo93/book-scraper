package com.yageb;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        try {
            ScraperController controller = new ScraperController(new Scraper(), new FileManager());
            controller.run();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
