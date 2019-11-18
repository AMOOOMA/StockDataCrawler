import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class MyController {

    public CrawlController controller;
    private CrawlConfig config;

    public MyController() throws Exception{
        String crawlStorageFolder = "/data/test";
        config  = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        config.setMaxDepthOfCrawling(1);
        config.setPolitenessDelay(1000);
        config.setThreadShutdownDelaySeconds(1);
        config.setCleanupDelaySeconds(1);
        config.setConnectionTimeout(2);
        controller = new CrawlController(config, pageFetcher, robotstxtServer);
    }

    public void addSeed(String url) {
        controller.addSeed(url);
    }

    public void run(int numberOfCrawlers) {
        controller.start(MyCrawler.class, numberOfCrawlers);
    }


}