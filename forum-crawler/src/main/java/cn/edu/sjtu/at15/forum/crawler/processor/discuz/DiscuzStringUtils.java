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

    public static String getAuthor(String thread) {
        Document doc = Jsoup.parse(thread);
        String author = doc.select("#postlist > div").first()
                .select("div.authi > a.xi2").text();
        return author;
    }

    public static Integer getViewCount(String thread) {
        Document doc = Jsoup.parse(thread);
        String count = doc.select("#postlist > table").first()
                .select("span.xi1").first().text();
        Integer viewCount = Integer.valueOf(count);
        return viewCount;
    }

    public static Integer getReplyCount(String thread) {
        Document doc = Jsoup.parse(thread);
        String count = doc.select("#postlist > table").first()
                .select("span.xi1").get(1).text();
        Integer replyCount = Integer.valueOf(count);
        return replyCount;
    }

    public static DiscuzThread parseThread(Page page) {
        DiscuzThread thread = new DiscuzThread();
        // TODO: parse author, view count, reply count, content
        String title = stripTags(page.getHtml().css("span#thread_subject").toString());
        LOGGER.debug("thread title : " + title);
        thread.setTitle(title);
        String author = getAuthor(page.getHtml().toString());
        thread.setAuthor(author);
        LOGGER.debug("thread author : " + author);
        Integer viewCount = getViewCount(page.getHtml().toString());
        thread.setViewCount(viewCount);
        LOGGER.debug("thread view count : " + viewCount);
        Integer replyCount = getReplyCount(page.getHtml().toString());
        thread.setReplyCount(replyCount);
        LOGGER.debug("thread reply count : " + replyCount);
        return thread;
    }
}
