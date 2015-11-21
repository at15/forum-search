package cn.edu.sjtu.at15.forum.crawler.discuz;

import cn.edu.sjtu.at15.forum.common.entity.ForumMainThread;
import cn.edu.sjtu.at15.forum.common.entity.ForumThread;
import cn.edu.sjtu.at15.forum.crawler.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by at15 on 15-11-19.
 */
public class ThreadParser extends Parser {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadParser.class);
    private String title;

    public ThreadParser(String html) {
        super(html);
        title = document.select("span#thread_subject").text();
        // parse all the posts

//        author = document.select("#postlist > div").first()
//                .select("div.authi > a.xi2").text();
//        // TODO: clear the ads using http://jsoup.org/cookbook/modifying-data/set-text
//        authorPost = document.select("#postlist > div").first()
//                .select("td.t_f").text();
    }

    public String getTitle() {
        return title;
    }

//    public String getAuthor() {
//        return author;
//    }
//
//    public Integer getViewCount() {
//        return viewCount;
//    }
//
//    public Integer getReplyCount() {
//        return replyCount;
//    }
//
//    public String getAuthorPost() {
//        return authorPost;
//    }

//    protected void parseThread() {
//
//    }
//
//    protected void parseMainThread() {
//        parseThread();

//    }
//
//    public static ForumMainThread parseAsMainThread(String html) {
//        ThreadParser parser = new ThreadParser(html);
//        parser.parseMainThread();
//        // TODO: assign all the attributes, or have a better way to share data
//        return new ForumMainThread();
//    }
//
//    public static ForumThread parseAsThread(String html) {
//        return new ForumThread();
//    }
}
