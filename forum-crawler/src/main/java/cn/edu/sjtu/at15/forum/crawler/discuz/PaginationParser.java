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
public class PaginationParser extends Parser {
    private List<String> pageLinks = new ArrayList<String>();

    public PaginationParser(String html) {
        super(html);
        parse();
    }

    public PaginationParser(Document document) {
        super(document);
        parse();
    }

    protected void parse() {
        ListIterator<Element> linksIterator = document.select("div.pg>a").listIterator();
        while (linksIterator.hasNext()) {
            pageLinks.add(linksIterator.next().attr("href"));
        }
    }

    public boolean hasPagination() {
        return pageLinks.size() != 0;
    }

    public List<String> getPageLinks() {
        return pageLinks;
    }
}
