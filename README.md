# Book Scraper

## Description

This is a Java console application that performs web scraping on [http://books.toscrape.com](http://books.toscrape.com). It uses Jsoup for parsing of HTML and other static resources. The program scrapes all the web pages recursively using multithreading and saves them to the disk so that they can be accessed locally just like on the Web.

## Usage

### Run in development mode

1. Make sure that you have JDK and Maven installed by running `java -version` and `mvn -v`.
2. Run `mvn package` to build the project.