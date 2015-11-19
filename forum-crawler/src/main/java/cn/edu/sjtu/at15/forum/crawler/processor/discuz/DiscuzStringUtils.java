package cn.edu.sjtu.at15.forum.crawler.processor.discuz;

import cn.edu.sjtu.at15.forum.crawler.discuz.ThreadParser;
import org.apache.commons.codec.binary.Base64;
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

    // TODO: should get all the comments as well, but we don't have much time, so.
    public static DiscuzThread parseThread(Page page) {
        DiscuzThread thread = new DiscuzThread();
        ThreadParser threadParser = new ThreadParser(page.getHtml().toString());
        thread.setUrl(page.getUrl().toString());
        // moved
        String title = threadParser.getTitle();
        LOGGER.debug("thread title : " + title);
        thread.setTitle(title);
        String author = threadParser.getAuthor();
        thread.setAuthor(author);
        LOGGER.debug("thread author : " + author);
        Integer viewCount = threadParser.getViewCount();
        thread.setViewCount(viewCount);
        LOGGER.debug("thread view count : " + viewCount);
        Integer replyCount = threadParser.getViewCount();
        thread.setReplyCount(replyCount);
        LOGGER.debug("thread reply count : " + replyCount);
        String authorPost = threadParser.getAuthorPost();
        thread.setAuthorPost(authorPost);
        LOGGER.debug(authorPost);
        return thread;
    }
}
