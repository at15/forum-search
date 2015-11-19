package cn.edu.sjtu.at15.forum.crawler.discuz;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 * Created by at15 on 15-11-19.
 */
public class ThreadParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadParser.class);
    private static final Pattern maxPagePattern = Pattern.compile("(.*)\\s(\\d+)\\s(.*)");
    private Document doc;
    private String title;
    private String author;
    private Integer viewCount;
    private Integer replyCount;
    private String authorPost;


    public ThreadParser(String html) {
        doc = Jsoup.parse(html);
    }

    public String getTitle() {
        if (title != null) {
            return title;
        }
        title = doc.select("span#thread_subject").text();
        return title;
    }

    public String getAuthor() {
        if (author != null) {
            return author;
        }
        author = doc.select("#postlist > div").first()
                .select("div.authi > a.xi2").text();
        return author;
    }

    public Integer getViewCount() {
        if (viewCount != null) {
            return viewCount;
        }
        String count = doc.select("#postlist > table").first()
                .select("span.xi1").first().text();
        viewCount = Integer.valueOf(count);
        return viewCount;
    }

    public Integer getReplyCount() {
        if (replyCount != null) {
            return replyCount;
        }
        String count = doc.select("#postlist > table").first()
                .select("span.xi1").get(1).text();
        replyCount = Integer.valueOf(count);
        return replyCount;
    }


    public String getAuthorPost() {
        if (authorPost != null) {
            return authorPost;
        }
        // TODO: clear the ads using http://jsoup.org/cookbook/modifying-data/set-text
        authorPost = doc.select("#postlist > div").first()
                .select("td.t_f").text();
        return authorPost;
    }

}
