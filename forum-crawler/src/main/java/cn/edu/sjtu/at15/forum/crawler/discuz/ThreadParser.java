package cn.edu.sjtu.at15.forum.crawler.discuz;

import cn.edu.sjtu.at15.forum.crawler.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by at15 on 15-11-19.
 */
public class ThreadParser extends Parser {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadParser.class);
    private String title;
    private String author;
    private Integer viewCount;
    private Integer replyCount;
    private String authorPost;

    public ThreadParser(String html) {
        super(html);
        String count;
        title = document.select("span#thread_subject").text();
        author = document.select("#postlist > div").first()
                .select("div.authi > a.xi2").text();
        count = document.select("#postlist > table").first()
                .select("span.xi1").first().text();
        viewCount = Integer.valueOf(count);
        count = document.select("#postlist > table").first()
                .select("span.xi1").get(1).text();
        replyCount = Integer.valueOf(count);
        // TODO: clear the ads using http://jsoup.org/cookbook/modifying-data/set-text
        authorPost = document.select("#postlist > div").first()
                .select("td.t_f").text();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public String getAuthorPost() {
        return authorPost;
    }
}
