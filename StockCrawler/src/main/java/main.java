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
