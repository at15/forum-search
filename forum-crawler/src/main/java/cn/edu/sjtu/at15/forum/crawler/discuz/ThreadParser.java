package cn.edu.sjtu.at15.forum.crawler.discuz;

import cn.edu.sjtu.at15.forum.common.entity.ForumPost;
import cn.edu.sjtu.at15.forum.common.entity.ForumThread;
import cn.edu.sjtu.at15.forum.crawler.Parser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by at15 on 15-11-19.
 */
public class ThreadParser extends Parser {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadParser.class);
    private String title;
    private List<ForumPost> posts;

    public ThreadParser(String html) {
        super(html);
        parse();
    }

    public ThreadParser(Document document) {
        super(document);
        parse();
    }

    protected void parse() {
        title = document.select("span#thread_subject").text();

        // parse all the posts
        posts = new ArrayList<ForumPost>();
        String author;
        String content;
        Element postElement;

        ListIterator<Element> postsIterator = document.select("#postlist > div").listIterator();
        while (postsIterator.hasNext()) {
            postElement = postsIterator.next();
            author = postElement.select("div.authi > a.xi2").text();
            // TODO: clear the ads using http://jsoup.org/cookbook/modifying-data/set-text
            content = postElement.select("td.t_f").text();
            // filter the reply box
            if (author == null || author.equals("")) {
                continue;
            }
            posts.add(new ForumPost(author, content));
//            LOGGER.debug(author);
//            LOGGER.debug(content);
        }
    }

    public String getTitle() {
        return title;
    }

    public List<ForumPost> getPosts() {
        return posts;
    }

    public ForumThread getThread(String url, String mainThreadUrl) {
        ForumThread thread = new ForumThread();
        thread.setUrl(url);
        thread.setMainThreadUrl(mainThreadUrl);
        thread.setTitle(getTitle());
        thread.setPosts(getPosts());
        return thread;
    }
}
