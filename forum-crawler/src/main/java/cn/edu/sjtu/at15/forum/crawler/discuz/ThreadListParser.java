package cn.edu.sjtu.at15.forum.crawler.discuz;

import cn.edu.sjtu.at15.forum.crawler.Parser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by at15 on 15-11-21.
 */
public class ThreadListParser extends Parser {
    private List<String> threadLinks = new ArrayList<String>();
    private DiscuzUrl discuzUrl;

    public ThreadListParser(String html, String baseUrl) {
        super(html);
        discuzUrl = new DiscuzUrl(baseUrl);
        parse();
    }

    public ThreadListParser(Document document, String baseUrl) {
        super(document);
        discuzUrl = new DiscuzUrl(baseUrl);
        parse();
    }

    public void parse() {
        ListIterator<Element> linksIterator = document.select("div#threadlist a").listIterator();
        String link;
        while (linksIterator.hasNext()) {
            link = linksIterator.next().attr("href");
            if (discuzUrl.isThread(link)) {
                threadLinks.add(link);
            }
        }
    }

    public boolean hasThreads() {
        return threadLinks.size() != 0;
    }

    public List<String> getThreadLinks() {
        return threadLinks;
    }
}
