package cn.edu.sjtu.at15.forum.crawler.discuz;

import cn.edu.sjtu.at15.forum.common.entity.ForumMainThread;
import org.jsoup.nodes.Document;

/**
 * Created by at15 on 15-11-21.
 */
public class MainThreadParser extends ThreadParser {
    private Integer viewCount;
    private Integer replyCount;

    public MainThreadParser(String html) {
        super(html);
        parseMainThreadExtra();
    }

    public MainThreadParser(Document document){
        super(document);
        parseMainThreadExtra();
    }


    public ForumMainThread getThread(String url) {
        ForumMainThread thread = new ForumMainThread(super.getThread(url, url));
        thread.setViewCount(getViewCount());
        thread.setReplyCount(getReplyCount());
        return thread;
    }

    public void parseMainThreadExtra() {
        // parse view and replyCount
        String count;
        count = document.select("#postlist > table").first()
                .select("span.xi1").first().text();
        viewCount = Integer.valueOf(count);
        count = document.select("#postlist > table").first()
                .select("span.xi1").get(1).text();
        replyCount = Integer.valueOf(count);
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }
}
