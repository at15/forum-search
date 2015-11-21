package cn.edu.sjtu.at15.forum.crawler.discuz;

/**
 * Created by at15 on 15-11-21.
 */
public class MainThreadParser extends ThreadParser {
    private Integer viewCount;
    private Integer replyCount;

    public MainThreadParser(String html) {
        super(html);
        // parse view and replyCount
        String count;
        count = document.select("#postlist > table").first()
                .select("span.xi1").first().text();
        viewCount = Integer.valueOf(count);
        count = document.select("#postlist > table").first()
                .select("span.xi1").get(1).text();
        replyCount = Integer.valueOf(count);
    }
}
