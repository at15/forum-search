package cn.edu.sjtu.at15.forum.crawler.processor.discuz;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.codecraft.webmagic.Page;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gpl on 15/11/14.
 */
public class DiscuzStringUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscuzStringUtils.class);
    private static final Pattern maxPagePattern = Pattern.compile("(.*)\\s(\\d+)\\s(.*)");

    // TODO: this is quite overhead
    public static String stripTags(String html) {
        Document doc = Jsoup.parse(html);
        return doc.body().text();
    }

    public static Integer getMaxPage(String pagination) {
        Matcher m = maxPagePattern.matcher(pagination);
        if (m.find()) {
            return Integer.valueOf(m.group(2));
        }
        return 0;
    }

    public static DiscuzThread parseThread(Page page) {
        DiscuzThread thread = new DiscuzThread();
        // TODO: the parse logic here
        String title = stripTags(page.getHtml().css("span#thread_subject").toString());
        LOGGER.debug("thread title : " + title);
        thread.setTitle(title);
        return thread;
    }
}
