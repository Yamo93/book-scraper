# Book Scraper

## Description

This is a Java console application that performs web scraping on [http://books.toscrape.com](http://books.toscrape.com). It uses Jsoup for parsing of HTML and other static resources. The program scrapes all the web pages recursively using multithreading and saves them to the disk so that they can be accessed locally just like on the Web.

## Usage

1. Make sure that you have JDK and Maven installed by running `java -version` and `mvn -v`.
2. Run `mvn package` to build the project.
3. Execute the compiled JAR with `mvn exec:java -Dexec.mainClass="com.yageb.App"`.
4. When the scraping is finished, navigate to the newly generated "data" folder and open index.html in your browser.
5. Navigate between pages and verify that HTML pages, styles and images are displayed properly.

## Design decisions

A primary concern of the project was to build a scraper that enables parsing of HTML documents and other resources without creating a tight coupling to those parsing dependencies. Since the scraper uses Jsoup as a parsing tool, it was necessary to create an abstraction layer on top of it to make it replacable but also to enable testing the scraping logic and mock the parser following the dependency inversion principle. 

To handle multithreading, separate threads were created for parsing scripts, stylesheets and images. The scraping was also implemented in a recursive manner.

To enable testing the business logic, interfaces were created and implemented so that only the core scraping logic is isolated under test, while injecting other dependencies. 

The project was also separated into different folders where each folder owns a certain responsibility, such as handling documents, parsing, scraping different resources and so on.

Testing was a primary objective in this project. The testing was focused on the core scraping logic and ensuring that each page and resource is only scraped once, and that the data is correctly scraped containing the expected data from the HTML documents.