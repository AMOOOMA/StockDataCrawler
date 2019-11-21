# StockDataCrawler
Implementation of the [crawler4j repo](https://github.com/yasserg/crawler4j). </br>
Extract stock exchange data from Wall Street Journal. Archived Data is in [here](StockCrawler/out/artifacts/StockCrawler_jar). </br>

## Getting Started
Current Implementation gathers data of Microsoft, Google and Amazon's minutely update quote. </br> 
The project included a executable [jar file](StockCrawler/out/artifacts/StockCrawler_jar/StockCrawler.jar). Once executed, it generates a csv file and records data from the website per minute for a consecutive six hours.</br>
</br>
Below is the sample output data format.</br>
```java
Date, Name, Value, ChangePer
4:15 PM EST 11/15/19, MSFT, 149.97, 1.29%
4:15 PM EST 11/15/19, AMZN, 1,739.49, -0.86%
4:15 PM EST 11/15/19, GOOGL, 1,333.54, 1.86%
```

## Documentation 
If you wish to modified the program, here is the explanation. The class main initialized a controller object in method crawl() and the controller object takes seeds, which is the url which you wish to crawl.
```java
public class main {
    public static void main(String[] args) throws Exception{
        System.out.println("The Stock Crawler has started. Will ended in six hours.");
        int sleepTime = 55;
        int count = 0;
        while (count < 60 * 6) {
            count++;
            crawl();
            Thread.sleep(sleepTime * 1000);
        }
    }

    public static void crawl() throws Exception{
        MyController c = new MyController();
        c.addSeed("https://quotes.wsj.com/US/MSFT");
        c.addSeed("https://quotes.wsj.com/US/AMZN");
        c.addSeed("https://quotes.wsj.com/US/GOOGL");
        c.run(3);
    }
}
```

### Crawler class
This part of the visit method in Mycrawler class finds the data we need in the page. You need to customize it for different html pages.
```java
  String date = html.substring(html.indexOf("quote_dateTime") + 16, html.indexOf("</span>", html.indexOf("quote_dateTime") + 16));
  String val = html.substring(html.indexOf("quote_val") + 11, html.indexOf("</span>", html.indexOf("quote_val") + 11));
  String changePer = html.substring(html.indexOf("quote_changePer") + 17, html.indexOf("</span>", html.indexOf("quote_changePer") + 17));
```
And the writeCSV() method append the information we want into the csv file. </br>
Note: the shouldVisit method contains a String representing the domain that you wish to crawl on. 

### Controller class
The controller controls the crawler, mainly using config to make the crawler behave. </br>
Go to the [crawler4j repo](https://github.com/yasserg/crawler4j) for more explanation.

## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
