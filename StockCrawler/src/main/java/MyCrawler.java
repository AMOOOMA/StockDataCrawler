
import edu.uci.ics.crawler4j.crawler.*;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.io.*;
import java.util.Set;
import java.util.regex.Pattern;


public class MyCrawler extends WebCrawler {

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp4|zip|gz))$");

    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "https://www.ics.uci.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches()
                && href.startsWith("https://quotes.wsj.com/US/");
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
            try {
                System.out.println(html.length());
                //PrintStream out = new PrintStream(new FileOutputStream("test.txt"));

                String date = html.substring(html.indexOf("quote_dateTime") + 16, html.indexOf("</span>", html.indexOf("quote_dateTime") + 16));
                String val = html.substring(html.indexOf("quote_val") + 11, html.indexOf("</span>", html.indexOf("quote_val") + 11));
                String changePer = html.substring(html.indexOf("quote_changePer") + 17, html.indexOf("</span>", html.indexOf("quote_changePer") + 17));
                writeCSV(date, val, changePer, url.substring(url.indexOf("US") + 3, url.length()));

                //out.println(html);
            } catch (Exception e) {
                //Do Nothing
            }
            System.out.println("Text length: " + text.length());
            System.out.println("Html length: " + html.length());
            System.out.println("Number of outgoing links: " + links.size());
        }
    }

    /**
     * writes out the data to a csv file.
     * @param date
     * @param val
     * @param changePer
     * @param quoteName
     */
    private void writeCSV(String date, String val, String changePer, String quoteName) {
        int index = val.indexOf(",");
        while (index != -1) {
            val = val.substring(0, index) + "." + val.substring(index + 1, val.length());
            index = val.indexOf(",");
        }
        try {
            FileWriter file = new FileWriter("data-minutes.csv", true);
            file.append(date);
            file.append(", ");
            file.append(quoteName);
            file.append(", ");
            file.append(val);
            file.append(", ");
            file.append(changePer);
            file.append("\n");

            file.flush();
            file.close();
        } catch (Exception e) {
            //
        }
    }
}
