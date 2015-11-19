package cn.edu.sjtu.at15.forum.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by at15 on 15-11-20.
 */
public class Parser {
    protected Document document;

    public Parser(String html) {
        document = Jsoup.parse(html);
    }

    public Parser(Document document) {
        document = document;
    }
}
