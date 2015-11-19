package cn.edu.sjtu.at15.forum.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by at15 on 15-11-19.
 */
public class Util {
    public static String stripTags(String html) {
        Document doc = Jsoup.parse(html);
        return doc.body().text();
    }

    public static String stripTags(Document document) {
        return document.body().text();
    }
}
