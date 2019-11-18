# StockDataCrawler
Implementation of the crawler4j [repo](https://github.com/yasserg/crawler4j). </br>
Extract stock exchange data from Wall Street Journal. </br>

## Getting Started
Current Implementation gathers data of Microsoft, Google and Amazon's minutely update quote. </br> 
The project included a executable [jar file](StockCrawler/out/artifacts/StockCrawler_jar/StockCrawler.jar). Once executed, it generates a csv file and records data from the website per minute for a consecutive six hours.</br>
Below is the sample output data format.</br>
```java
Date, Name, Value, ChangePer
4:15 PM EST 11/15/19, MSFT, 149.97, 1.29%
4:15 PM EST 11/15/19, AMZN, 1,739.49, -0.86%
4:15 PM EST 11/15/19, GOOGL, 1,333.54, 1.86%
```
