package cn.edu.sjtu.at15.forum.crawler.discuz;

import cn.edu.sjtu.at15.forum.common.StringUtils;

/**
 * Created by gpl on 15/11/14.
 */
public class Url extends cn.edu.sjtu.at15.forum.crawler.Url {
    public Url(String baseUrl) {
        super(baseUrl);
    }

    // FIXME: http://www.1point3acres.com/bbs/thread-146437-5-1.html is not the first page for thread.
    // it's the comment page, so the parser will got error.
    public boolean isThread(String url) {
        return url.startsWith(baseUrl + "/thread-");
    }

    // FIXME: this only works for one url
    public boolean isList(String url) {
        return url.startsWith(baseUrl + "/forum.php?mod=guide");
    }
}
